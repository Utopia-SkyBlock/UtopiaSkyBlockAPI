package de.linushuck.utopia.skyblock.libs.api.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import de.linushuck.utopia.skyblock.libs.api.aaanewstructure.SkyBlockItemBuilder;
import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HeadCreator
{
    private static final HashMap<String, ItemStack> headsCache = new HashMap<>()
    {
        {
            put("", new ItemStack(Material.DIRT, 1));
        }
    };

    public static ItemStack createCustomHead(String textureValue)
    {
        if(headsCache.containsKey(textureValue))
        {
            return headsCache.get(textureValue).clone();
        }
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        head.editMeta(SkullMeta.class, skullMeta ->
        {
            final UUID uuid = UUID.randomUUID();
            final PlayerProfile playerProfile = Bukkit.createProfile(uuid, uuid.toString().substring(0, 16));
            playerProfile.setProperty(new ProfileProperty("textures", textureValue));

            skullMeta.setPlayerProfile(playerProfile);
        });
        headsCache.put(textureValue, head);
        return head.clone();
    }

    public static SkyBlockItemBuilder createCustomHeadSkyBlockItemBuilder(String textureValue)
    {
        return new SkyBlockItemBuilder(createCustomHead(textureValue));
    }

    public static ItemStack createPlayerHead(String playerName)
    {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwner(playerName);
        head.setItemMeta(skullMeta);
        return head;
    }


    public static ItemStack createCustomHeadSpecial(String value, String name, List<String> lore)
    {
        SkyBlockItemBuilder itemBuilder = new SkyBlockItemBuilder(createCustomHead(value));
        itemBuilder.translationLoreAppendToSection(LoreSection.DESCRIPTION_NO_LINE, lore);
        itemBuilder.translationName(name);
        return itemBuilder.build();
    }

    public static ItemStack createCustomHead(String value, String itemString)
    {
        ItemStack head = createCustomHead(value);
        NewItemUtils.setItemID(head, itemString);
        return head;
    }
}
