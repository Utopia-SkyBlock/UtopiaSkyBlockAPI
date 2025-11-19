package de.linushuck.utopia.skyblock.libs.api.item;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ItemStackWrapper
{
    private final ItemStack item;
    private final ReadWriteNBT workingNbt;
    private final ItemTranslation translation;

    private ItemStackWrapper(@NotNull ItemStack item)
    {
        this.item = item;
        this.workingNbt = NBT.createNBTObject();
        this.workingNbt.mergeCompound(NBT.readNbt(item));
        this.translation = ItemTranslation.from(workingNbt);
    }

    public static ItemStackWrapper of(ItemStack item)
    {
        return new ItemStackWrapper(item);
    }

    public ItemStackWrapper modify(Consumer<ItemTranslation> toModify)
    {
        toModify.accept(translation);
        return this;
    }

    public ItemStack apply()
    {
        translation.apply(workingNbt);

        NBT.modify(item, readWriteItemNBT ->
        {
            readWriteItemNBT.mergeCompound(workingNbt);
        });
        return item;
    }
}