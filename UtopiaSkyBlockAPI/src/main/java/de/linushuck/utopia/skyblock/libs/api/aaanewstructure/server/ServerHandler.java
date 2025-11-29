package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.server;

import de.linushuck.utopia.skyblock.libs.essentials.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerHandler
{
    private final Map<String, SkyBlockServerType> serverTypes;
    private boolean firstAllLoadDone = false;

    public ServerHandler()
    {
        this.serverTypes = new LinkedHashMap<>();
    }

    public void addServerType(String name, SkyBlockServerType serverType)
    {
        if(serverTypes.containsKey(name))
        {
            Logger.warn("ServerType " + name + " is already registered!");
            return;
        }
        serverTypes.put(name, serverType);
    }

    public void addServerType(String name, SkyBlockServerType serverType, boolean load)
    {
        addServerType(name, serverType);
        if(load && firstAllLoadDone)
        {
            serverType.loadMaster();
            serverType.initMaster();
        }
        else if(load)
        {
            Logger.info(ServerHandler.class, "ServerType not loaded because first all ServerType load is not done yet!");
        }
    }

    public void loadServerTypes()
    {
        firstAllLoadDone = true;
        for(SkyBlockServerType serverType : serverTypes.values())
        {
            serverType.loadMaster();
        }
        for(SkyBlockServerType serverType : serverTypes.values())
        {
            serverType.initMaster();
        }
    }

    public void unloadServerTypes()
    {
        for(SkyBlockServerType serverType : serverTypes.values())
        {
            serverType.unloadMaster();
        }
    }

    public List<String> getNames()
    {
        return serverTypes.keySet().stream().toList();
    }

    public SkyBlockServerType getServerType(String name)
    {
        return serverTypes.get(name);
    }

    public boolean loadServerType(String name)
    {
        SkyBlockServerType serverType = serverTypes.get(name);
        if(serverType != null && !serverType.isLoaded())
        {
            serverType.loadMaster();
            serverType.initMaster();
            return true;
        }
        return false;
    }

    public boolean unloadServerType(String name)
    {
        SkyBlockServerType serverType = serverTypes.get(name);
        if(serverType != null && serverType.isLoaded())
        {
            serverType.unloadMaster();
            return true;
        }
        return false;
    }
}
