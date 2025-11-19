package de.linushuck.utopia.skyblock.libs.api.ability;

import org.bukkit.entity.Player;

public abstract class ClickAbility extends BaseAbility
{
    public abstract boolean test(BaseAbility ability, Player player);

    public abstract boolean accept(BaseAbility ability, Player player);
}
