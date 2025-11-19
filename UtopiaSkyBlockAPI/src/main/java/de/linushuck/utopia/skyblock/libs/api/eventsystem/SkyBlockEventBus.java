package de.linushuck.utopia.skyblock.libs.api.eventsystem;

import de.linushuck.utopia.skyblock.libs.essentials.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SkyBlockEventBus
{
    private static SkyBlockEventBus INSTANCE;
    private final Map<Class<?>, Map<Object, List<HandlerMethod>>> listeners;
    private final HashMap<Class<?>, Integer> eventCount = new HashMap<>();


    public SkyBlockEventBus()
    {
        INSTANCE = this;
        Logger.debug(this, "----------------------");
        Logger.debug(this, "Creating BetterEventBus This should only happen once");
        Logger.debug(this, "----------------------");

        listeners = new HashMap<>();
    }

    public static SkyBlockEventBus getInstance()
    {
        if(INSTANCE == null)
        {
            new SkyBlockEventBus();
        }
        return INSTANCE;
    }

    public static void registerListener(SkyBlockListener... listener)
    {
        getInstance().register(listener);
    }

    public static void unregisterListener(SkyBlockListener listener)
    {
        getInstance().unregister(listener);
    }

    public static void callEvents(SkyBlockEvent... events)
    {
        for(SkyBlockEvent event : events)
        {
            getInstance().callEvent(event);
        }
    }

    public void register(SkyBlockListener object)
    {
        if(isAlreadyRegistered(object))
        {
            Logger.debug(this, "Listener " + object.getClass().getSimpleName() + " is already registered, skipping.");
            return;
        }

        Logger.debug(this, "Registering listener " + object.getClass().getSimpleName());
        for(Method method : object.getClass().getMethods())
        {
            if(method.isAnnotationPresent(SkyBlockEventHandler.class))
            {
                SkyBlockEventHandler handlerAnnotation = method.getAnnotation(SkyBlockEventHandler.class);
                int priority = handlerAnnotation.priority();
                HandlerGroup group = handlerAnnotation.group();
                boolean callAlways = handlerAnnotation.callAlways();
                boolean cancelOtherGroups = handlerAnnotation.cancelOtherGroups();

                Class<?>[] parameters = method.getParameterTypes();
                if(parameters.length == 1 && SkyBlockEvent.class.isAssignableFrom(parameters[0]))
                {
                    if(!eventCount.containsKey(parameters[0]))
                    {
                        eventCount.put(parameters[0], 1);
                    }
                    else
                    {
                        eventCount.put(parameters[0], eventCount.get(parameters[0]) + 1);
                    }
                    Logger.debug(this, "Registered event " + parameters[0].getSimpleName() + " with " + eventCount.get(parameters[0]) + " listeners");
                    Map<Object, List<HandlerMethod>> map = listeners.computeIfAbsent(parameters[0], k -> new HashMap<>());
                    List<HandlerMethod> handlerMethods = map.computeIfAbsent(object, k -> new ArrayList<>());
                    handlerMethods.add(new HandlerMethod(method, priority, group, callAlways, cancelOtherGroups));
                }
            }
        }
    }

    private boolean isAlreadyRegistered(SkyBlockListener object)
    {
        return listeners.values().stream().anyMatch(eventMap -> eventMap.containsKey(object));
    }

    public void register(SkyBlockListener... objects)
    {
        for(SkyBlockListener object : objects)
        {
            register(object);
        }
    }

    public void unregister(SkyBlockListener object)
    {
        Logger.debug(this, "Unergistering listener " + object.getClass().getSimpleName());
        for(Map.Entry<Class<?>, Map<Object, List<HandlerMethod>>> entry : listeners.entrySet())
        {
            Map<Object, List<HandlerMethod>> map = entry.getValue();
            List<HandlerMethod> removedHandlers = map.remove(object);
            if(removedHandlers != null)
            {
                Class<?> eventClass = entry.getKey();
                int currentCount = eventCount.get(eventClass);
                eventCount.put(eventClass, currentCount - removedHandlers.size());
            }
        }
    }

    public void callEvent(SkyBlockEvent event)
    {
        event.clearExecutors();
        Map<Object, List<HandlerMethod>> handlers = listeners.get(event.getClass());
        if(handlers != null)
        {
            Map<HandlerGroup, List<HandlerMethod>> sortedHandlers = new EnumMap<>(HandlerGroup.class);

            for(List<HandlerMethod> handlerMethods : handlers.values())
            {
                for(HandlerMethod handlerMethod : handlerMethods)
                {
                    sortedHandlers.computeIfAbsent(handlerMethod.group(), k -> new ArrayList<>()).add(handlerMethod);
                }
            }
            Logger.debug(this, "Found " + handlers.size() + " handlers for event " + event.getClass().getSimpleName());

            boolean handlerReturnedTrue = false;
            boolean cancelOtherGroups = false;

            List<HandlerMethod> runAlwaysEndHandlers = null;

            for(HandlerGroup group : HandlerGroup.values())
            {
                if(group == HandlerGroup.RUN_ALWAYS_END)
                {
                    runAlwaysEndHandlers = sortedHandlers.get(group);
                    continue;
                }

                List<HandlerMethod> groupHandlers = sortedHandlers.get(group);
                if(groupHandlers != null)
                {
                    groupHandlers.sort(Comparator.comparingInt(HandlerMethod::priority).reversed());

                    if(group == HandlerGroup.RUN_ALWAYS_START)
                    {
                        for(HandlerMethod handlerMethod : groupHandlers)
                        {
                            try
                            {
                                Object listenerInstance = handlers.entrySet()
                                        .stream()
                                        .filter(entry -> entry.getValue().contains(handlerMethod))
                                        .map(Map.Entry::getKey)
                                        .findFirst()
                                        .orElse(null);

                                if(listenerInstance != null)
                                {
                                    long startTime = System.nanoTime();
                                    try
                                    {
                                        Object result = handlerMethod.method().invoke(listenerInstance, event);
                                        long executionTime = System.nanoTime() - startTime;
                                        boolean returnValue = result instanceof Boolean ? (Boolean) result : false;
                                        event.addExecutor(new SkyBlockEventExecutor(handlerMethod.method(), listenerInstance, returnValue, executionTime));
                                    }
                                    catch(Exception e)
                                    {
                                        long executionTime = System.nanoTime() - startTime;
                                        event.addExecutor(new SkyBlockEventExecutor(handlerMethod.method(), listenerInstance, e, executionTime));
                                        throw e;
                                    }
                                }
                            }
                            catch(IllegalAccessException | InvocationTargetException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else
                    {
                        boolean groupStop = false;
                        for(HandlerMethod handlerMethod : groupHandlers)
                        {
                            try
                            {
                                Object listenerInstance = handlers.entrySet()
                                        .stream()
                                        .filter(entry -> entry.getValue().contains(handlerMethod))
                                        .map(Map.Entry::getKey)
                                        .findFirst()
                                        .orElse(null);

                                if(listenerInstance != null)
                                {
                                    if((!cancelOtherGroups && !groupStop) || handlerMethod.callAlways())
                                    {
                                        long startTime = System.nanoTime();
                                        boolean result = false;
                                        try
                                        {
                                            result = (boolean) handlerMethod.method().invoke(listenerInstance, event);
                                            long executionTime = System.nanoTime() - startTime;
                                            event.addExecutor(new SkyBlockEventExecutor(handlerMethod.method(), listenerInstance, result, executionTime));

                                            if(result)
                                            {
                                                handlerReturnedTrue = true;
                                            }
                                            if(result && handlerMethod.cancelOtherGroups())
                                            {
                                                cancelOtherGroups = true;
                                            }
                                            if(result && !handlerMethod.callAlways())
                                            {
                                                groupStop = true;
                                            }
                                        }
                                        catch(Exception e)
                                        {
                                            long executionTime = System.nanoTime() - startTime;
                                            event.addExecutor(new SkyBlockEventExecutor(handlerMethod.method(), listenerInstance, e, executionTime));
                                            throw e;
                                        }
                                    }
                                }
                            }
                            catch(IllegalAccessException | InvocationTargetException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            if(!handlerReturnedTrue)
            {
                if(event instanceof FinalEventMethod)
                {
                    ((FinalEventMethod) event).onEvent();
                }
            }

            if(runAlwaysEndHandlers != null)
            {
                runAlwaysEndHandlers.sort(Comparator.comparingInt(HandlerMethod::priority).reversed());
                for(HandlerMethod handlerMethod : runAlwaysEndHandlers)
                {
                    try
                    {
                        Object listenerInstance = handlers.entrySet()
                                .stream()
                                .filter(entry -> entry.getValue().contains(handlerMethod))
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElse(null);

                        if(listenerInstance != null)
                        {
                            long startTime = System.nanoTime();
                            try
                            {
                                Object result = handlerMethod.method().invoke(listenerInstance, event);
                                long executionTime = System.nanoTime() - startTime;
                                boolean returnValue = result instanceof Boolean ? (Boolean) result : false;
                                event.addExecutor(new SkyBlockEventExecutor(handlerMethod.method(), listenerInstance, returnValue, executionTime));
                            }
                            catch(Exception e)
                            {
                                long executionTime = System.nanoTime() - startTime;
                                event.addExecutor(new SkyBlockEventExecutor(handlerMethod.method(), listenerInstance, e, executionTime));
                                throw e;
                            }
                        }
                    }
                    catch(IllegalAccessException | InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            if(event instanceof FinalEventMethod)
            {
                ((FinalEventMethod) event).onEvent();
            }
        }
    }
}