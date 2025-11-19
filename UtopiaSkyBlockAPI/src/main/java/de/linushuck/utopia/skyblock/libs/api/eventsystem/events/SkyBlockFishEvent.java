package de.linushuck.utopia.skyblock.libs.api.eventsystem.events;

import de.linushuck.utopia.skyblock.libs.api.enums.PlayerActionType;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.FinalEventMethod;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventCancelable;
import de.linushuck.utopia.skyblock.libs.api.item.ItemUtils;
import de.linushuck.utopia.skyblock.libs.api.item.NewItemUtils;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

@Data
public class SkyBlockFishEvent extends SkyBlockEventCancelable implements FinalEventMethod
{
    private final Player player;
    private final ItemStack tool;
    private final String specialItemID;
    private final String normalItemID;

    private final Entity caught;
    private final FishHook hookEntity;
    private final PlayerFishEvent.State state;
    private final Location pullLocation;

    public SkyBlockFishEvent(Player player, Entity caught, FishHook hookEntity, PlayerFishEvent.State state)
    {
        super();
        this.player = player;
        this.caught = caught;
        this.hookEntity = hookEntity;
        this.pullLocation = hookEntity.getLocation();
        this.state = state;
        this.tool = player.getInventory().getItemInMainHand();


        specialItemID = ItemUtils.getSpecialAbility(tool, getFilterType());
        normalItemID = NewItemUtils.getItemID(tool);
    }

    @Override
    public void onEvent()
    {

    }

    public ItemStack getToolCopy()
    {
        return tool.clone();
    }

    public PlayerActionType getFilterType()
    {
        return PlayerActionType.PLAYER_FISH;
    }
}
