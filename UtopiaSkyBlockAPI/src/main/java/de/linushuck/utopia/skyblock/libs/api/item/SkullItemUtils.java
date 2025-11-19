package de.linushuck.utopia.skyblock.libs.api.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import de.linushuck.utopia.skyblock.libs.api.aaanewstructure.SkyBlockItemBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SkullItemUtils
{
    public void applyTexture(ItemStack itemStack, String texture)
    {
        itemStack.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile()
                .addProperties(List.of(new ProfileProperty("textures", texture))));
    }

    public void applyTexture(ItemStack itemStack, PlayerProfile playerProfile)
    {
        itemStack.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile(playerProfile));
    }

    public void applyTexture(SkyBlockItemBuilder item, PlayerProfile playerProfile)
    {
        item.set(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile(playerProfile));
    }

    public void applyTexture(SkyBlockItemBuilder item, String texture)
    {
        item.set(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile()
                .addProperties(List.of(new ProfileProperty("textures", texture))));
    }
}
