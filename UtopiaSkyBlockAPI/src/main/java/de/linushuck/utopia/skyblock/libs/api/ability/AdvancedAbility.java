package de.linushuck.utopia.skyblock.libs.api.ability;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import org.bukkit.entity.Player;

public abstract class AdvancedAbility<T extends SkyBlockEvent> extends BaseAbility
{
    public abstract State test(BaseAbility ability, Player player, T betterEvent);

    public abstract State accept(BaseAbility ability, Player player, T betterEvent);

}