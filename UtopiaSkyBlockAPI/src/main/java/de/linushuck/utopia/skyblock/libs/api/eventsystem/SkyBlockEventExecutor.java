package de.linushuck.utopia.skyblock.libs.api.eventsystem;

import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class SkyBlockEventExecutor
{
    private final Method method;
    private final Object listenerInstance;
    private final boolean returnedTrue;
    private final long executionTimeNanos;
    private final Exception exception;

    public SkyBlockEventExecutor(Method method, Object listenerInstance, boolean returnedTrue, long executionTimeNanos)
    {
        this.method = method;
        this.listenerInstance = listenerInstance;
        this.returnedTrue = returnedTrue;
        this.executionTimeNanos = executionTimeNanos;
        this.exception = null;
    }

    public SkyBlockEventExecutor(Method method, Object listenerInstance, Exception exception, long executionTimeNanos)
    {
        this.method = method;
        this.listenerInstance = listenerInstance;
        this.returnedTrue = false;
        this.executionTimeNanos = executionTimeNanos;
        this.exception = exception;
    }

    public boolean hasException()
    {
        return exception != null;
    }

    public String getMethodName()
    {
        return method.getName();
    }

    public String getListenerClassName()
    {
        return listenerInstance.getClass().getSimpleName();
    }
}
