package de.linushuck.utopia.skyblock.libs.api.interfaces;

import net.minecraft.advancements.AdvancementType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IUIModule
{

    IActionBar getActionBar(UUID uuid);

    void sendToast(Player player, Material icon, AdvancementType frameType, String text);

}
