package de.linushuck.utopia.skyblock.libs.api.interfaces;

import net.minecraft.advancements.AdvancementType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface IToastSystem
{

    void sendToast(Player player, Material icon, AdvancementType frameType, String text);
}
