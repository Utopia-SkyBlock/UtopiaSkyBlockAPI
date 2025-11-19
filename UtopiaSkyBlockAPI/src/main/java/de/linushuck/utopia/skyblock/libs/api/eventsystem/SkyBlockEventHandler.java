package de.linushuck.utopia.skyblock.libs.api.eventsystem;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkyBlockEventHandler
{
    int priority() default 500;

    HandlerGroup group() default HandlerGroup.RUN_ALWAYS_START;

    boolean callAlways() default false;

    boolean cancelOtherGroups() default false;
}