package de.linushuck.utopia.skyblock.libs.essentials;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.event.Level;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class Logger
{
    private static final HashMap<Level, Boolean> levelStates = new HashMap<>();
    @Getter
    public static HashMap<String, BiConsumer<Level, String>> customLoggers = new HashMap<>();
    private static JavaPlugin plugin;
    @Setter
    private static ComponentLogger componentLogger;
    @Setter
    @Getter
    private static boolean isDebugEnabled = false;

    static
    {
        levelStates.put(Level.INFO, true);
        levelStates.put(Level.WARN, true);
        levelStates.put(Level.ERROR, true);
        levelStates.put(Level.DEBUG, false);
        levelStates.put(Level.TRACE, true);
    }

    public static void log(String objClassName, Level level, Object message, String... args)
    {
        if(!(message instanceof String) && !(message instanceof Component))
        {
            throw new IllegalArgumentException("Message must be String or Component");
        }

        if(!levelStates.getOrDefault(level, true))
        {
            return;
        }

        String string = objClassName == null || objClassName.trim().isEmpty() ? "" : objClassName;
        if(componentLogger != null)
        {
            String prefix = string.isEmpty() ? "<white>" : "<white>[<green>" + string + "</green>]</white> ";

            Component finalMessage;
            if(message instanceof Component cmessage)
            {
                String backToString = BaseComponentHelper.miniReverse(cmessage);
                if(args != null && args.length > 0)
                {
                    backToString = String.format(backToString, (Object[]) args);
                }
                finalMessage = BaseComponentHelper.mini(prefix + backToString);
            }
            else
            {
                String msg = message.toString();
                if(args != null && args.length > 0)
                {
                    msg = String.format(msg, (Object[]) args);
                }
                finalMessage = BaseComponentHelper.mini(prefix + msg);
            }

            for(BiConsumer<Level, String> customLogger : getCustomLoggers().values())
            {
                customLogger.accept(level, BaseComponentHelper.miniReverse(finalMessage));
            }
            switch(level)
            {
                case WARN -> componentLogger.warn(finalMessage);
                case ERROR -> componentLogger.error(finalMessage);
                case DEBUG ->
                {
                    if(!isDebugEnabled && !levelStates.getOrDefault(Level.DEBUG, false))
                    {
                        return;
                    }
                    componentLogger
                            .info(BaseComponentHelper.mini("<yellow>DEBUG: </yellow>").append(finalMessage));
                }
                case TRACE -> componentLogger.trace(finalMessage);
                default -> componentLogger.info(finalMessage);
            }
        }
        else
        {
            String time = java.time.LocalDateTime.now().toString();
            String classNamePart = string.isEmpty() ? "" : "[" + string + "] ";

            String plainMessage;
            if(message instanceof Component cmessage)
            {
                plainMessage = BaseComponentHelper.miniReverse(cmessage);
            }
            else
            {
                plainMessage = message.toString();
            }
            if(args != null && args.length > 0)
            {
                plainMessage = String.format(plainMessage, (Object[]) args);
            }

            String finalMessage = String.format("[%s] [%s] %s%s", time, level.name(), classNamePart, plainMessage);
            for(BiConsumer<Level, String> customLogger : getCustomLoggers().values())
            {
                customLogger.accept(level, finalMessage);
            }
            if(level == Level.DEBUG && !isDebugEnabled && !levelStates.getOrDefault(Level.DEBUG, false))
            {
                return;
            }
            System.out.println(finalMessage);
        }
    }

    public static void info(Object caller, Object message, String... args)
    {
        log(getCallerName(caller), Level.INFO, message, args);
    }

    public static void info(Object message, String... args)
    {
        //check if message is maybe the caller and the message is at args[0]
        if(message instanceof Component || message instanceof String)
        {
            log("", Level.INFO, message, args);
        }
        else
        {
            if(args != null && args.length > 0)
            {
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                log(getCallerName(message), Level.INFO, args[0], newArgs);
            }
        }
    }

    public static void warn(Object caller, Object message, String... args)
    {
        log(getCallerName(caller), Level.WARN, message, args);
    }

    public static void warn(Object message, String... args)
    {
        //check if message is maybe the caller and the message is at args[0]
        if(message instanceof Component || message instanceof String)
        {
            log("", Level.WARN, message, args);
        }
        else
        {
            if(args != null && args.length > 0)
            {
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                log(getCallerName(message), Level.WARN, args[0], newArgs);
            }
        }
    }

    public static void error(Object caller, Object message, String... args)
    {
        log(getCallerName(caller), Level.ERROR, message, args);
    }

    public static void error(Object caller, Object message, Exception e, String... args)
    {
        log(getCallerName(caller), Level.ERROR, message, args);
        if(plugin != null)
        {
            plugin.getLogger().log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }
        else {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void error(Object caller, Exception e, String... args)
    {
        log(getCallerName(caller), Level.ERROR, "Error", args);
        if(plugin != null)
        {
            plugin.getLogger().log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }
        else {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void error(Object message, String... args)
    {
        //check if message is maybe the caller and the message is at args[0]
        if(message instanceof Component || message instanceof String)
        {
            log("", Level.ERROR, message, args);
        }
        else
        {
            if(args != null && args.length > 0)
            {
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                log(getCallerName(message), Level.ERROR, args[0], newArgs);
            }
        }
    }

    public static void debug(Object caller, Object message, String... args)
    {
        log(getCallerName(caller), Level.DEBUG, message, args);
    }

    public static void debug(Object message, String... args)
    {
        //check if message is maybe the caller and the message is at args[0]
        if(message instanceof Component || message instanceof String)
        {
            log("", Level.DEBUG, message, args);
        }
        else
        {
            if(args != null && args.length > 0)
            {
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                log(getCallerName(message), Level.DEBUG, args[0], newArgs);
            }
        }
    }

    public static String getCallerName(Object caller)
    {
        if(caller == null)
        {
            return "Unknown";
        }
        if(caller instanceof Class<?>)
        {
            return ((Class<?>) caller).getSimpleName();
        }
        return caller.getClass().getSimpleName();
    }

    public static void disable(Level level)
    {
        levelStates.put(level, false);
        if(level == Level.DEBUG)
        {
            isDebugEnabled = false;
        }
    }

    public static void enable(Level level)
    {
        levelStates.put(level, true);
        if(level == Level.DEBUG)
        {
            isDebugEnabled = true;
        }
    }

    public static void setPlugin(JavaPlugin plugin)
    {
        Logger.plugin = plugin;
        Logger.componentLogger = plugin.getComponentLogger();
    }
}