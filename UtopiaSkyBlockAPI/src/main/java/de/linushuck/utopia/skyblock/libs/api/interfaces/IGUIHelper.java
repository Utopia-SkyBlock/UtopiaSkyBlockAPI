package de.linushuck.utopia.skyblock.libs.api.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;

import java.util.concurrent.atomic.AtomicBoolean;

public interface IGUIHelper
{
    String[] getLayout(String guiIdentifier);

    ItemStack getMaterial(String guiIdentifier, char character);

    ItemProvider getGrayBackgroundItem();

    ItemProvider getWhiteBackgroundItem();

    ItemProvider getErrorBackgroundItem();

    ItemProvider getPlaceholderBackgroundItem();

    Item getCloseItem(Player player, AtomicBoolean clicked);

    Item getCloseItem(Player player);

    void addBackAndCloseButton(Player player, Object navBackGUI, Gui.Builder<?, ?> builder);

    void addBackAndCloseButton(Player player, Object navBackGUI, Gui.Builder<?, ?> builder, AtomicBoolean clicked);
}
