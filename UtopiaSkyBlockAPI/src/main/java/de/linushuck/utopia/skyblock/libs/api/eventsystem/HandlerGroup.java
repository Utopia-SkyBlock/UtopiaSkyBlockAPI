package de.linushuck.utopia.skyblock.libs.api.eventsystem;

/**
 * Defines handler groups for event priority organization.
 * SPECIAL handlers are executed before DEFAULT handlers.
 */
public enum HandlerGroup
{
    RUN_ALWAYS_START,
    SPECIAL,
    DEFAULT,
    RUN_ALWAYS_END
}