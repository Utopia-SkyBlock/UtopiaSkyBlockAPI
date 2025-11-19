package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui.items;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.xenondevs.invui.Click;

public interface HandleClick
{
    void accept(ClickType clickType, Player player, Click click, ClickableItem item);
}
