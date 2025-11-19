package de.linushuck.utopia.skyblock.libs.api.skyblock.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkyBlockTempEvent extends Event
{
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public SkyBlockTempEvent()
    {

    }

    public static HandlerList getHandlerList()
    {
        return HANDLERS_LIST;
    }

    @Override
    public HandlerList getHandlers()
    {
        return HANDLERS_LIST;
    }
}