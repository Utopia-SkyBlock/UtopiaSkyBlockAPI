package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui.items.navigator;

import de.linushuck.utopia.skyblock.libs.api.PublicSkyBlockAPI;
import de.linushuck.utopia.skyblock.libs.api.aaanewstructure.SkyBlockItemBuilder;
import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import de.linushuck.utopia.skyblock.libs.essentials.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.xenondevs.invui.Click;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.ScrollGui;
import xyz.xenondevs.invui.item.AbstractBoundItem;
import xyz.xenondevs.invui.item.ItemProvider;

import java.util.List;

public class NavigationControlItem extends AbstractBoundItem
{
    private final NavigationControlType controlType;
    private final SkyBlockItemBuilder itemBuilder;
    private final int scrollSpeed;
    private NavigableGui navigableGui;

    // Constructor for general GUI (needs adapter setup later)
    public NavigationControlItem(NavigationControlType controlType, SkyBlockItemBuilder itemBuilder)
    {
        this(controlType, itemBuilder, 1);
    }

    public NavigationControlItem(NavigationControlType controlType, SkyBlockItemBuilder itemBuilder, int scrollSpeed)
    {
        this.controlType = controlType;
        this.itemBuilder = itemBuilder;
        this.scrollSpeed = scrollSpeed;
    }

    // Set up the adapter after the GUI is known
    public void setupNavigableGui(Gui gui)
    {
        if(gui instanceof PagedGui)
        {
            this.navigableGui = new PagedGuiAdapter<>((PagedGui<?>) gui);
        }
        else if(gui instanceof ScrollGui)
        {
            this.navigableGui = new ScrollGuiAdapter<>((ScrollGui<?>) gui);
        }
        else
        {
            throw new IllegalArgumentException("Unsupported GUI type: " + gui.getClass().getName());
        }
    }

    @Override
    public ItemProvider getItemProvider(Player viewer)
    {
        if(navigableGui == null)
        {
            setupNavigableGui(getGui());
        }

        int currentPosition = navigableGui.getCurrentPosition();
        int totalPositions = navigableGui.getTotalPositions();

        switch(controlType)
        {
            case FIRST_PAGE, PREVIOUS_PAGE, SCROLL_UP ->
            {
                if(currentPosition == 0)
                {
                    return PublicSkyBlockAPI.getInstance().getGuiHelper().getGrayBackgroundItem();
                }
            }
            case NEXT_PAGE, LAST_PAGE, SCROLL_DOWN ->
            {
                if(currentPosition == totalPositions - 1)
                {
                    return PublicSkyBlockAPI.getInstance().getGuiHelper().getGrayBackgroundItem();
                }
            }
        }

        SkyBlockItemBuilder clone = itemBuilder.clone();
        switch(controlType)
        {
            case NEXT_PAGE, PREVIOUS_PAGE, SCROLL_UP, SCROLL_DOWN ->
            {
                clone.translationLoreSetSection(LoreSection.DESCRIPTION_NO_LINE, List.of("<gray>Current Position: <gold>" + (currentPosition + 1) + "</gold>/<gold>" + totalPositions + "</gold></gray>"));
//                clone.addLoreLines(
//                        ComponentHelper.mini("<gray>Current Position: <gold>" + (currentPosition + 1) +
//                                "</gold>/<gold>" + totalPositions + "</gold></gray>")
//                );
            }
        }
        return clone;
    }

    @Override
    public void handleClick(ClickType clickType, Player player, Click click)
    {
        Logger.info(this, "Clicked navigation control: " + controlType + " with click type: " + clickType);
        if(navigableGui == null)
        {
            setupNavigableGui(getGui());
        }

        if(clickType == ClickType.LEFT)
        {
            switch(controlType)
            {
                case NEXT_PAGE, SCROLL_DOWN -> navigableGui.navigateForward();
                case PREVIOUS_PAGE, SCROLL_UP -> navigableGui.navigateBackward();
                case FIRST_PAGE -> navigableGui.navigateFirst();
                case LAST_PAGE -> navigableGui.navigateLast();
            }
        }
        else if(clickType == ClickType.RIGHT)
        {
            switch(controlType)
            {
                case SCROLL_DOWN ->
                {
                    for(int i = 0; i < scrollSpeed; i++)
                    {
                        navigableGui.navigateForward();
                    }
                }
                case SCROLL_UP ->
                {
                    for(int i = 0; i < scrollSpeed; i++)
                    {
                        navigableGui.navigateBackward();
                    }
                }
                case FIRST_PAGE -> navigableGui.navigateFirst();
                case LAST_PAGE -> navigableGui.navigateLast();
            }
        }
        Bukkit.getScheduler().runTaskAsynchronously(PublicSkyBlockAPI.getInstance().getPlugin(), () ->
        {
            getGui().notifyWindows('«', '<', '>', '»', '↓', '↑');
        });
    }
}
