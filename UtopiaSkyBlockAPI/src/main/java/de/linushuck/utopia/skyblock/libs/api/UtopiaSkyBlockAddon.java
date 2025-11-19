package de.linushuck.utopia.skyblock.libs.api;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventBus;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventHandler;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockListener;
import de.linushuck.utopia.skyblock.libs.api.skyblock.events.SkyBlockStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class UtopiaSkyBlockAddon implements SkyBlockListener
{
    public UtopiaSkyBlockAddon()
    {
        SkyBlockEventBus.registerListener(this);
    }

    @SkyBlockEventHandler
    public boolean onEvent(SkyBlockStatusEvent event)
    {
        switch(event.getNewstatus())
        {
            case READY ->
            {
                onEnable();
            }
            case UNLOADING ->
            {
                onDisable();
            }
        }
        return true;
    }

    public void onEnable()
    {

    }

    public void onDisable()
    {

    }

    public JavaPlugin getPlugin()
    {
        return PublicSkyBlockAPI.getInstance().getPlugin();
    }
}
