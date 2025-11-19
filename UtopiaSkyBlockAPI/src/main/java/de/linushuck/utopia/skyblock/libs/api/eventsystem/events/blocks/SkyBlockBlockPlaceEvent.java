package de.linushuck.utopia.skyblock.libs.api.eventsystem.events.blocks;

import de.linushuck.utopia.skyblock.libs.api.PublicSkyBlockAPI;
import de.linushuck.utopia.skyblock.libs.api.enums.PlayerActionType;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.FinalEventMethod;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventCancelable;
import de.linushuck.utopia.skyblock.libs.api.item.ItemUtils;
import de.linushuck.utopia.skyblock.libs.api.item.NewItemUtils;
import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockServerType;
import lombok.Data;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class SkyBlockBlockPlaceEvent extends SkyBlockEventCancelable implements FinalEventMethod
{
    private final Player player;
    private final Block placedBlock;
    private final ItemStack placedBlockItem;
    private final String specialItemID;
    private final String normalItemID;
    private final ArrayList<ItemStack> drops;
    private BlockPlaceEvent oldEvent;

    public SkyBlockBlockPlaceEvent(BlockPlaceEvent oldEvent)
    {
        super();
        this.oldEvent = oldEvent;

        this.player = oldEvent.getPlayer();
        this.placedBlock = oldEvent.getBlock();
        placedBlockItem = oldEvent.getItemInHand();
        specialItemID = ItemUtils.getSpecialAbility(placedBlockItem, getFilterType());
        normalItemID = NewItemUtils.getItemID(placedBlockItem);
        drops = new ArrayList<>();
    }


    @Override
    public void onEvent()
    {
        System.out.println("BetterBlockPlaceEvent.onEvent final method");
        if(player == null)
        {
            return;
        }
        SkyBlockServerType serverNow = PublicSkyBlockAPI.getInstance()
                .getRedisCommonRequests()
                .getServerNow(PublicSkyBlockAPI.getInstance().getSkyBlockProfileHelper().getActiveProfile(player));


        if(serverNow != SkyBlockServerType.PRIVATEISLAND)
        {
            oldEvent.setCancelled(true);
            //TODO Linus SoundDesign.ActionDeny.playSound(player, Sound.Emitter.self());
        }
        else
        {
            if(!Objects.equals(getNormalItemID(), "null"))
            {
                oldEvent.setCancelled(true);
                //TODO Linus SoundDesign.ActionDeny.playSound(player, Sound.Emitter.self());
            }
        }
    }


    public PlayerActionType getFilterType()
    {
        return PlayerActionType.BLOCK_PLACE_ACTION;
    }
}
