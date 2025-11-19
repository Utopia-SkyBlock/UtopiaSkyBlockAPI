package de.linushuck.utopia.skyblock.libs.api.item;


import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@UtilityClass
public class NewItemUtils
{
    public static final SkullItemUtils skullItemUtils = new SkullItemUtils();

    public String getItemID(ItemStack itemStack)
    {
        return getValue(itemStack, "item_string", String.class).toLowerCase();
    }

    public ItemStack setItemID(ItemStack itemStack, String itemID)
    {
        return setValue(itemStack, "item_string", itemID);
    }

    public static <T> T getValue(ItemStack itemStack, String key, Class<T> type)
    {
        if(itemStack == null || itemStack.getType() == Material.AIR || itemStack.getAmount() <= 0)
        {
            if(type == String.class)
            {
                return type.cast("null");
            }
            if(type == Integer.class)
            {
                return type.cast(-1);
            }
            if(type == Boolean.class)
            {
                return type.cast(false);
            }
            if(type == Double.class)
            {
                return type.cast(-1.0);
            }
            if(type == Long.class)
            {
                return type.cast(-1L);
            }
            if(type == UUID.class)
            {
                return null;
            }
            return null;
        }
        ReadWriteNBT nbtObject = NBT.createNBTObject();
        nbtObject.mergeCompound(NBT.readNbt(itemStack));
        return getValue(nbtObject, key, type);
    }

    public static <T> ItemStack setValue(ItemStack itemStack, String key, T value)
    {
        if(itemStack == null || itemStack.getType() == Material.AIR || itemStack.getAmount() <= 0)
        {
            return itemStack;
        }
        NBT.modify(itemStack, nbt ->
        {
            setValue(nbt, key, value);
        });
        return itemStack;
    }


    public String getItemID(ReadWriteNBT readWriteNBT)
    {

        String itemId = getValue(readWriteNBT, "item_string", String.class);
        if(itemId == null)
        {
            return "null";
        }
        return itemId.toLowerCase();
    }

    public void setItemID(ReadWriteNBT readWriteNBT, String itemID)
    {
        setValue(readWriteNBT, "item_string", itemID);
    }

    public static <T> T getValue(ReadWriteNBT readWriteNBT, String key, Class<T> type)
    {
        //This ensures that different types with the same key don't override each other
        key = key + ":" + type.getSimpleName();

        if(readWriteNBT == null || !readWriteNBT.hasTag(key))
        {
            if(type == String.class)
            {
                return type.cast("null");
            }
            if(type == Integer.class)
            {
                return type.cast(-1);
            }
            if(type == Boolean.class)
            {
                return type.cast(false);
            }
            if(type == Double.class)
            {
                return type.cast(-1.0);
            }
            if(type == Long.class)
            {
                return type.cast(-1L);
            }
            if(type == UUID.class)
            {
                return null;
            }
            return null;
        }

        if(type == String.class)
        {
            return type.cast(readWriteNBT.getString(key));
        }
        if(type == Integer.class)
        {
            return type.cast(readWriteNBT.getInteger(key));
        }
        if(type == Boolean.class)
        {
            return type.cast(readWriteNBT.getBoolean(key));
        }
        if(type == Double.class)
        {
            return type.cast(readWriteNBT.getDouble(key));
        }
        if(type == Long.class)
        {
            return type.cast(readWriteNBT.getLong(key));
        }
        if(type == UUID.class)
        {
            return type.cast(readWriteNBT.getUUID(key));
        }
        return null;
    }

    public static <T> void setValue(ReadWriteNBT readWriteNBT, String key, T value)
    {
        //This ensures that different types with the same key don't override each other
        key = key + ":" + value.getClass().getSimpleName();
        String finalKey = key;
        if(value instanceof String)
        {
            readWriteNBT.setString(finalKey, (String) value);
        }
        else if(value instanceof Integer)
        {
            readWriteNBT.setInteger(finalKey, (Integer) value);
        }
        else if(value instanceof Boolean)
        {
            readWriteNBT.setBoolean(finalKey, (Boolean) value);
        }
        else if(value instanceof Double)
        {
            readWriteNBT.setDouble(finalKey, (Double) value);
        }
        else if(value instanceof Long)
        {
            readWriteNBT.setLong(finalKey, (Long) value);
        }
        else if(value instanceof UUID)
        {
            readWriteNBT.setUUID(finalKey, (UUID) value);
        }
    }
}
