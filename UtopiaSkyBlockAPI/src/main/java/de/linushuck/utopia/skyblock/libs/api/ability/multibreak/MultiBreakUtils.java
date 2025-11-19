package de.linushuck.utopia.skyblock.libs.api.ability.multibreak;

import de.linushuck.utopia.skyblock.libs.api.ComponentHelper;
import de.linushuck.utopia.skyblock.libs.api.PublicSkyBlockAPI;
import de.linushuck.utopia.skyblock.libs.api.ability.BaseAbility;
import de.linushuck.utopia.skyblock.libs.api.ability.State;
import de.linushuck.utopia.skyblock.libs.api.enums.PlayerActionType;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEventBus;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.events.blocks.SkyBlockBlockBreakEvent;
import de.linushuck.utopia.skyblock.libs.api.item.ItemUtils;
import net.minecraft.advancements.AdvancementType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiBreakUtils
{

    public static State accept(BaseAbility ability, Player player, SkyBlockBlockBreakEvent betterBlockBreakEvent)
    {
        int maxBlocks = ability.getAdditionalInfo();

        // Init vars
        Material initialMaterial = betterBlockBreakEvent.getBrokenBlock().getType();
        Set<Block> checked = new HashSet<>();
        List<Block> toCheck = new ArrayList<>(List.of(betterBlockBreakEvent.getBrokenBlock()));
        AtomicInteger blocksBroken = new AtomicInteger(0);
        ItemStack tool = betterBlockBreakEvent.getToolCopy();
        ItemUtils.removeSpecialAbility(tool, PlayerActionType.BLOCK_BREAK_ACTION);

        // Cancel original event
        betterBlockBreakEvent.getOldEvent().setCancelled(true);

        // Start block breaking timer
        Bukkit.getScheduler().runTaskTimer(PublicSkyBlockAPI.getInstance().getPlugin(), (task) ->
        {
            if(!toCheck.isEmpty() && blocksBroken.get() < maxBlocks)
            {
                Block currBlock = toCheck.remove(0);
                checked.add(currBlock);

                if(currBlock.getType() == initialMaterial && isLog(currBlock.getType()))
                {
                    SkyBlockBlockBreakEvent blockBreakEvent = new SkyBlockBlockBreakEvent(new BlockBreakEvent(currBlock, player), tool);

                    SkyBlockEventBus.getInstance().callEvent(blockBreakEvent);

                    // Process drops
                    for(ItemStack drop : blockBreakEvent.getDrops())
                    {
                        blockBreakEvent.getBrokenBlock()
                                .getWorld()
                                .dropItemNaturally(blockBreakEvent.getBrokenBlock().getLocation(), drop);
                    }

                    if(!blockBreakEvent.isCancelled())
                    {
                        // First block broken - add cooldown
                        if(blocksBroken.getAndIncrement() == 0)
                        {
                            PublicSkyBlockAPI.getInstance().getCooldownHelper().addPlayerToCooldown(player, ability);
                        }

                        // Break the block
                        blockBreakEvent.getBrokenBlock().setType(Material.AIR);

                        // Find connected blocks
                        for(Block face : getAdjacentBlocks(currBlock))
                        {
                            if(!checked.contains(face) && !toCheck.contains(face) && face.getType() == initialMaterial && isLog(face.getType()))
                            {
                                toCheck.add(face);
                            }
                        }
                    }
                }
            }
            else
            {
                // Task complete
                task.cancel();
                String intialMaterialName = initialMaterial.name();
                intialMaterialName = intialMaterialName.replace("WOOD", "LOG");
                PublicSkyBlockAPI.getInstance()
                        .getUiModule()
                        .sendToast(player, Material.valueOf(intialMaterialName), AdvancementType.GOAL, ComponentHelper.legacy(ComponentHelper.mini("You broke " + blocksBroken.get() + " blocks!")));
            }
        }, 0, 1);
        return State.STATE_1;
    }

    public static State test(BaseAbility ability, Player player, SkyBlockBlockBreakEvent betterBlockBreakEvent)
    {
        // Only apply to logs
        if(!isLog(betterBlockBreakEvent.getBrokenBlock().getType()))
        {
            return State.FALSE;
        }
        return State.TRUE;
    }

    private static boolean isLog(Material material)
    {
        String materialName = material.name();
        return materialName.contains("LOG") || materialName.contains("STEM") || (materialName.contains("WOOD") && !materialName.contains("WOODEN"));
    }

    public static List<Block> getAdjacentBlocks(Block block)
    {
        List<Block> blocks = new ArrayList<>();
        for(int x = -1; x <= 1; x++)
        {
            for(int y = -1; y <= 1; y++)
            {
                for(int z = -1; z <= 1; z++)
                {
                    // Exclude the central block (0, 0, 0) itself
                    if(x != 0 || y != 0 || z != 0)
                    {
                        blocks.add(block.getRelative(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }
}