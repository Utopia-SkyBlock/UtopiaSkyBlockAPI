package de.linushuck.utopia.skyblock.libs.api.eventsystem;

public class SkyBlockEventCancelable extends SkyBlockEvent
{
    private boolean cancelled = false;

    public SkyBlockEventCancelable(String name, String description)
    {
        super(name, description);
    }

    public SkyBlockEventCancelable()
    {
        super();
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
}