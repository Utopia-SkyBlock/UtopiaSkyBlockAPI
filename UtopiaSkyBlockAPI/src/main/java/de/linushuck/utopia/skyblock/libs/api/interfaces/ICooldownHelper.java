package de.linushuck.utopia.skyblock.libs.api.interfaces;

import de.linushuck.utopia.skyblock.libs.api.ability.BaseAbility;
import org.bukkit.entity.Player;

public interface ICooldownHelper
{
    void showActionBarMessageAbility(Player player, BaseAbility ability);

    boolean isPlayerOnCooldown(Player player, BaseAbility ability);

    void addPlayerToCooldown(Player player, BaseAbility ability);

    long getRemainingCooldown(Player player, String itemId, long cooldownSeconds);

    void sendStillOnCoolDownMessage(Player player, BaseAbility ability);

}
