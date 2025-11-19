package de.linushuck.utopia.skyblock.libs.api.ability.teleport;

import de.linushuck.utopia.skyblock.libs.api.ComponentHelper;
import de.linushuck.utopia.skyblock.libs.api.PublicSkyBlockAPI;
import de.linushuck.utopia.skyblock.libs.api.ability.BaseAbility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;

public class TeleportUtils
{
    public static boolean accept(BaseAbility ability, Player player)
    {
        player.setFallDistance(0);
        Location loc = player.getLocation().clone();
        loc.add(loc.getDirection().multiply(ability.getAdditionalInfo()));
        loc.add(0, 1, 0);
        //TODO Linus SoundDesign.SkyBlock_ActionTeleport.playSound(player);
        player.teleport(loc);
        return true;
    }

    public static boolean test(BaseAbility ability, Player player)
    {
        // Teleport player 8 blocks forward
        player.setFallDistance(0);
        Location playerLoc = player.getLocation();
        RayTraceResult result = player.getWorld()
                .rayTraceBlocks(playerLoc.clone().add(0, player.getEyeHeight(), 0), playerLoc.getDirection(), 8.0);

        Location targetLoc;
        if(result != null && result.getHitBlock() != null)
        {
            targetLoc = result.getHitPosition().toLocation(player.getWorld()).subtract(playerLoc.getDirection());
            //TODO Linus SoundDesign.ActionDeny.playSound(player);
            PublicSkyBlockAPI.getInstance()
                    .getUiModule()
                    .getActionBar(player.getUniqueId())
                    .setNext(ComponentHelper.mini("<red>There are blocks in the way!</red>"), 3);
        }
        else
        {
            targetLoc = playerLoc.clone().add(playerLoc.getDirection().multiply(8));
        }

        targetLoc.setYaw(playerLoc.getYaw());
        targetLoc.setPitch(playerLoc.getPitch());
        player.teleport(targetLoc);

        // Add speed effect
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 2)); // 3 seconds, level 3

        return true;
    }
}

