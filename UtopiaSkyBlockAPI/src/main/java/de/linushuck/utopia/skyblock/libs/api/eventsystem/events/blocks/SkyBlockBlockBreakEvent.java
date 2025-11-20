package de.linushuck.utopia.skyblock.libs.api.eventsystem.events.blocks;

import de.linushuck.utopia.skyblock.libs.api.PublicSkyBlockAPI;
import de.linushuck.utopia.skyblock.libs.api.SoundDesign;
import de.linushuck.utopia.skyblock.libs.api.enums.PlayerActionType;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.FinalEventMethod;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventCancelable;
import de.linushuck.utopia.skyblock.libs.api.item.ItemUtils;
import de.linushuck.utopia.skyblock.libs.api.item.NewItemUtils;
import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockServerType;
import lombok.Data;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@Data
public class SkyBlockBlockBreakEvent extends SkyBlockEventCancelable implements FinalEventMethod
{
    private final Player player;
    private final ItemStack tool;
    private final Block brokenBlock;
    private final String specialItemID;
    private final String normalItemID;
    private final ArrayList<ItemStack> drops;
    private BlockBreakEvent oldEvent;

    public SkyBlockBlockBreakEvent(BlockBreakEvent oldEvent, ItemStack tool)
    {
        super();
        this.oldEvent = oldEvent;

        this.player = oldEvent.getPlayer();
        this.tool = tool;
        this.brokenBlock = oldEvent.getBlock();
        specialItemID = ItemUtils.getSpecialAbility(tool, getFilterType());
        normalItemID = NewItemUtils.getItemID(tool);
        drops = new ArrayList<>();
    }

    public SkyBlockBlockBreakEvent(BlockBreakEvent oldEvent)
    {
        this(oldEvent, oldEvent.getPlayer().getInventory().getItemInMainHand());
    }


    @Override
    public void onEvent()
    {
        System.out.println("BetterBlockBreakEvent.onEvent final method");

        //TODO SkyBlockServerType serverNow = PublicSkyBlockAPI.getInstance()
        //TODO         .getRedisCommonRequests()
        //TODO         .getServerNow(PublicSkyBlockAPI.getInstance().getSkyBlockProfileHelper().getActiveProfile(player));
        //TODO if(player != null && serverNow != SkyBlockServerType.PRIVATEISLAND)
        //TODO {
        //TODO     oldEvent.setCancelled(true);
        //TODO     SoundDesign.ActionDeny.playSound(player);
        //TODO }
        //TODO else
        //TODO {
        //TODO     {
        //TODO         setValid(true);
        //TODO         drops.addAll(oldEvent.getBlock().getDrops());
        //TODO     }
        //TODO }
    }

    public ItemStack getToolCopy()
    {
        return tool.clone();
    }

    public PlayerActionType getFilterType()
    {
        return PlayerActionType.BLOCK_BREAK_ACTION;
    }
}
