package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.server;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventBus;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockListener;
import de.linushuck.utopia.skyblock.libs.essentials.Logger;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public abstract class SkyBlockServerType implements SkyBlockListener
{
    private String name;
    private UUID serverIdentifier;
    private String description;
    private String version;
    private String mainClass;
    private String apiVersion;
    private String[] authors;
    @Setter
    @Getter
    private boolean loaded = false;

    public SkyBlockServerType()
    {
        SkyBlockEventBus.registerListener(this);
    }

    public abstract void load();

    public void loadMaster()
    {
        Logger.debug(this, "Loading...");
        load();
        setLoaded(true);
        Logger.debug(this, "Loaded!");
    }

    public abstract void init();

    public void initMaster()
    {
        Logger.debug(this, "Initializing...");
        init();
        Logger.debug(this, "Initialized!");
    }

    public abstract void unload();

    public void unloadMaster()
    {
        Logger.debug(this, "Unloading...");
        unload();
        setLoaded(false);
        Logger.debug(this, "Unloaded!");
    }
}
