package de.linushuck.utopia.skyblock.libs.api.item;

import de.linushuck.utopia.skyblock.libs.api.aaanewstructure.SkyBlockItemBuilder;
import de.linushuck.utopia.skyblock.libs.api.enums.*;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ItemUtils
{
    public static boolean hasPlayerSpace(Player player, ItemStack itemStack)
    {
        int space = 0;
        ItemStack[] contents = player.getInventory().getStorageContents();
        String itemID1 = NewItemUtils.getItemID(itemStack);

        for(ItemStack item : contents)
        {
            if(item == null)
            {
                space += itemStack.getType().getMaxStackSize();
            }
            else if(item.getType() == itemStack.getType())
            {
                String itemID2 = NewItemUtils.getItemID(item);
                if(Objects.equals(itemID1, itemID2))
                {
                    space += itemStack.getType().getMaxStackSize() - item.getAmount();
                }
            }
        }

        return space >= itemStack.getAmount();
    }

    public static Component cooldown(int amount)
    {
        return Component.text("Cooldown: ")
                .color(NamedTextColor.GRAY)
                .append(Component.text(amount + "s").color(NamedTextColor.GREEN));
    }

    public static ItemType getItemType(ItemStack itemStack)
    {
        if(itemStack == null || itemStack.getType() == Material.AIR || itemStack.getAmount() == 0)
        {
            return ItemType.DONTHANDLE;
        }
        NBTItem nbtItem = new NBTItem(itemStack);
        if(nbtItem.hasKey("Type"))
        {
            return nbtItem.getEnum("Type", ItemType.class);
        }
        return ItemType.NONE;
    }


    public static SkyBlockItemBuilder getSkyBlockItemBuilder(Material material)
    {
        SkyBlockItemBuilder itemBuilder = new SkyBlockItemBuilder(material);
        String formattedName = Arrays.stream(material.name().split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
        itemBuilder.translationName("<white>" + formattedName + "</white>");

        itemBuilder.translationLoreSetSection(LoreSection.TYPEOFITEM, List.of(ItemRarity.COMMON.getAsString("material")));
        return itemBuilder;
    }

    public static String itemToBase64(ItemStack item)
    {
        try
        {
            if(item == null)
            {
                return null;
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(item);

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch(Exception ignored)
        {
        }
        return null;
    }

    public static ItemStack itemFromBase64(String data)
    {
        try
        {
            if(data == null || data.equals(""))
            {
                return new ItemStack(Material.AIR);
            }

            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Object object = dataInput.readObject();
            if(!(object instanceof ItemStack item))
            {
                return null;
            }

            dataInput.close();
            return item;
        }
        catch(Exception ignored)
        {
        }
        return null;
    }

    public static String getItemString(Player player)
    {
        return NewItemUtils.getItemID(player.getInventory().getItemInMainHand());
    }


    public static void setCounterOnItem(ItemStack item, int i)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setInteger("counter", i);
        });
    }

    public static int getCounterOnItem(ItemStack item)
    {
        return getNBTInt(item, "counter");
    }

    public static boolean hasSpecialAbility(ItemStack itemInMainHand, PlayerActionType actionType)
    {
        return !getSpecialAbility(itemInMainHand, actionType).equals("null");
    }

    public static void removeSpecialAbility(ItemStack item, PlayerActionType actionType)
    {
        if(hasSpecialAbility(item, actionType))
        {
            removeNBTKey(item, actionType.getActionName());
        }
    }

    public static String getSpecialAbility(ItemStack item, PlayerActionType actionType)
    {
        return getNBTString(item, actionType.getActionName());
    }


    public static int getNBTInt(ItemStack item, String key)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return -1;
        }
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey(key))
        {
            return nbtItem.getInteger(key);
        }
        return -1;
    }

    public static String getNBTString(ItemStack item, String key)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return "null";
        }
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey(key))
        {
            return nbtItem.getString(key);
        }
        return "null";
    }


    public static boolean getNBTBoolean(ItemStack item, String key)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return false;
        }
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey(key))
        {
            return nbtItem.getBoolean(key);
        }
        return false;
    }

    public static boolean hasKey(ItemStack item, String key)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return false;
        }
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.hasKey(key);
    }

    private static void removeNBTKey(ItemStack item, String key)
    {
        NBT.modify(item, nbt ->
        {
            nbt.removeKey(key);
        });
    }

    public static void setNBTInt(ItemStack item, String key, int value)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setInteger(key, value);
        });
    }

    public static void setNBTString(ItemStack item, String key, String value)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setString(key, value);
        });
    }

    public static void setNBTLong(ItemStack item, String key, long value)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setLong(key, value);
        });
    }

    public static void setNBTDouble(ItemStack item, String key, double value)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setDouble(key, value);
        });
    }

    public static double getNBTDouble(ItemStack item, String key)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return -1;
        }
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey(key))
        {
            return nbtItem.getDouble(key);
        }
        return -1;
    }

    public static long getNBTLong(ItemStack item, String key)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return -1;
        }
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey(key))
        {
            return nbtItem.getLong(key);
        }
        return -1;
    }


    public static void addUUID(ItemStack item)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setUUID("uuid", UUID.randomUUID());
        });
    }

    public static UUID getUUID(ItemStack item)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return null;
        }
        NBTItem nbtItem = new NBTItem(item);
        if(nbtItem.hasKey("uuid"))
        {
            return nbtItem.getUUID("uuid");
        }
        return null;
    }


    public static void setFuel(ItemStack item, String minionUpgradeFuel)
    {
        NBT.modify(item, nbt ->
        {
            nbt.setString("fuel", minionUpgradeFuel);
        });
    }

    public static boolean areItemsSimilar(ItemStack itemStack, ItemStack item)
    {
        Material material1 = itemStack.getType();
        Material material2 = item.getType();

        if(material1 != material2)
        {
            return false;
        }
        String id1 = NewItemUtils.getItemID(itemStack);
        String id2 = NewItemUtils.getItemID(item);
        return Objects.equals(id1, id2);
    }

    public static void printItems(ItemStack[] items)
    {
        for(ItemStack item : items)
        {
            if(item != null)
            {
                System.out.println(item.getType() + " " + item.getAmount());
            }
        }
    }

    public static ItemRarity getItemRarity(ItemStack itemStack)
    {
        try
        {
            String rarity = getNBTString(itemStack, "rarity");
            if(rarity.equals("null"))
            {
                if(itemStack.getItemMeta().hasRarity())
                {
                    return ItemRarity.valueOf(itemStack.getItemMeta().getRarity().name());
                }
            }
            return ItemRarity.valueOf(rarity);
        }
        catch(Exception e)
        {
            return ItemRarity.COMMON;
        }
    }

    public static String getItemCategory(ItemStack itemStack)
    {
        return getNBTString(itemStack, "rarity");
    }

    public static double getSellPrice(ItemStack item)
    {
        return getNBTDouble(item, "sell_price");
    }

    public static boolean isDroppedItemWithMaterialProgress(ItemStack item)
    {
        if(item == null || item.getType() == Material.AIR || item.getAmount() == 0)
        {
            return false;
        }
        return NBT.modify(item, nbt ->
        {
            return nbt.hasTag("skyblock_material");
        });
    }

    /**
     * Right click is 0
     * Shift right click is 1
     * Left click is 2
     * Shift left click is 3
     * Block break is 4
     * Block place is 5
     */
}