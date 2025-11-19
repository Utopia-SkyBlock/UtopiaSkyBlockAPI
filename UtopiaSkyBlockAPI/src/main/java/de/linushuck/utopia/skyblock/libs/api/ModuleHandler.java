package de.linushuck.utopia.skyblock.libs.api;


import de.linushuck.utopia.skyblock.libs.essentials.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModuleHandler
{
    private final Map<String, Module> modules;
    private final JavaPlugin plugin;
    private boolean firstAllLodeDone = false;

    public ModuleHandler(JavaPlugin plugin)
    {
        this.plugin = plugin;
        this.modules = new LinkedHashMap<>();
    }

    public void addModule(Class<? extends Module> module)
    {
        if(modules.containsKey(module.getSimpleName()))
        {
            Logger.warn("Module " + module.getSimpleName() + " is already registered!");
            return;
        }
        try
        {
            Module moduleInstance = module.getDeclaredConstructor(JavaPlugin.class).newInstance(plugin);
            modules.put(module.getSimpleName(), moduleInstance);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Failed to create module instance: " + module.getSimpleName(), e);
        }
    }

    public void addModule(Class<Module> module, boolean load)
    {
        addModule(module);
        Module module1 = modules.get(module.getSimpleName());
        if(load && firstAllLodeDone)
        {
            module1.loadMaster();
            module1.initMaster();
        }
        else if(load)
        {
            Logger.info(module1.getPlugin(), "Module not loaded because first all Module load is not done yet!");
        }
    }

    public void loadModules()
    {
        firstAllLodeDone = true;
        for(Module module : modules.values())
        {
            module.loadMaster();
        }
        for(Module module : modules.values())
        {
            module.initMaster();
        }
    }

    public void unloadModules()
    {
        for(Module module : modules.values())
        {
            module.unloadMaster();
        }
    }

    public List<String> getNames()
    {
        return modules.keySet().stream().toList();
    }

    public boolean loadModule(String moduleName)
    {
        Module module = modules.get(moduleName);
        if(!module.isLoaded())
        {
            module.loadMaster();
            module.initMaster();
            return true;
        }
        return false;
    }

    public boolean unloadModule(String moduleName)
    {
        Module module = modules.get(moduleName);
        if(module.isLoaded())
        {
            module.unloadMaster();
            return true;
        }
        return false;
    }
}