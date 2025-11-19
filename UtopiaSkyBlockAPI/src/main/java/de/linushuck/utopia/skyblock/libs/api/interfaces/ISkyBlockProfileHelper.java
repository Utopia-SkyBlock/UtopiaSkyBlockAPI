package de.linushuck.utopia.skyblock.libs.api.interfaces;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface ISkyBlockProfileHelper
{
    UUID getActiveProfile(Player player);
}
