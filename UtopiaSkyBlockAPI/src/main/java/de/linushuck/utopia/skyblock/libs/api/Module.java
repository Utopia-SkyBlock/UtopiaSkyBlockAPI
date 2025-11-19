package de.linushuck.utopia.skyblock.libs.api;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventBus;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockListener;
import de.linushuck.utopia.skyblock.libs.essentials.Logger;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module implements SkyBlockListener
{
    @Getter
    private final JavaPlugin plugin;
    @Setter
    @Getter
    private boolean loaded = false;

    public Module(JavaPlugin plugin)
    {
        this.plugin = plugin;
        SkyBlockEventBus.registerListener(this);
    }

    /**
     * Loads the module
     * For stuff like precalculating values
     */
    public abstract void load();

    public void loadMaster()
    {
        Logger.debug(this, "Loading...");
        load();
        setLoaded(true);

        Logger.debug(this, "Loaded!");
    }

    /**
     * Initializes the module
     * For stuff like registering events
     */
    public abstract void init();

    public void initMaster()
    {

        Logger.debug(this, "Initializing...");
        init();

        Logger.debug(this, "Initialized!");
    }

    /**
     * Unloads the module
     * For stuff like saving values
     */
    public abstract void unload();

    public void unloadMaster()
    {

        Logger.debug(this, "Unloading...");
        unload();
        setLoaded(false);

        Logger.debug(this, "Unloaded!");
    }
}