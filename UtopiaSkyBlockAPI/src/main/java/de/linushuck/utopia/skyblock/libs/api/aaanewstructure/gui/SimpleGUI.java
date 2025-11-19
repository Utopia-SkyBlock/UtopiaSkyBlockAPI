package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui;

import de.linushuck.utopia.skyblock.libs.api.PublicSkyBlockAPI;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.window.Window;

import java.util.HashMap;

public abstract class SimpleGUI<T>
{
    protected final String[] structure;
    @Getter
    protected final Component title;
    private final HashMap<Player, T> playerOptions = new HashMap<>();
    private final HashMap<Player, Object> playerNavBack = new HashMap<>();

    public SimpleGUI(String guiIdentifier, Component title)
    {
        this.title = title;
        structure = PublicSkyBlockAPI.getInstance().getGuiHelper().getLayout(guiIdentifier);
    }

    protected abstract Window openGUIInternally(Player player, T options, Object navBackGUI);

    private void saveOptions(Player player, T options)
    {
        playerOptions.put(player, options);
    }

    private void saveNavBack(Player player, Object navBackGUI)
    {
        if(navBackGUI != null)
        {
            playerNavBack.put(player, navBackGUI);
        }
    }

    private Object getNavBack(Player player)
    {
        return playerNavBack.get(player);
    }

    private T getOptions(Player player)
    {
        return playerOptions.getOrDefault(player, getDefaultOptions(player));
    }

    public abstract T getDefaultOptions(Player player);

    public void openGUI(Player player, T options, Object navBackGUI)
    {
        saveNavBack(player, navBackGUI);
        Window window = openGUIInternally(player, options, navBackGUI);
        Bukkit.getScheduler().runTaskAsynchronously(PublicSkyBlockAPI.getInstance().getPlugin(), () ->
        {
            saveOptions(player, options);
            Bukkit.getScheduler().runTask(PublicSkyBlockAPI.getInstance().getPlugin(), window::open);
        });
    }

    public void openGUI(Player player, T options)
    {
        openGUI(player, options, null);
    }

    public void openLastGUI(Player player, Object navBackGUI)
    {
        openGUI(player, getOptions(player), navBackGUI);
    }

    public void openLastGUI(Player player)
    {
        openGUI(player, getOptions(player), getNavBack(player));
    }
}
