package de.linushuck.utopia.skyblock.libs.api.aaanewstructure;

import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import de.linushuck.utopia.skyblock.libs.api.item.ItemTranslation;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanList;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomModelData;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.Nullable;
import xyz.xenondevs.invui.item.ItemProvider;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.jetbrains.annotations.ApiStatus.Experimental;

@SuppressWarnings("UnstableApiUsage")
public class SkyBlockItemBuilder implements ItemProvider
{

    private ItemStack buildCache;
    private ItemStack itemStack;
    @Getter
    private ItemTranslation translation;
    private ReadWriteNBT readWriteNBT;
    private @Nullable FloatList customModelDataFloats;
    private @Nullable BooleanList customModelDataBooleans;
    private @Nullable List<String> customModelDataStrings;
    private @Nullable IntList customModelDataColors;
    private @Nullable Map<String, TagResolver> placeholders;

    /**
     * Constructs a new {@link SkyBlockItemBuilder} based on the given {@link Material}.
     *
     * @param material The {@link Material}
     */
    public SkyBlockItemBuilder(Material material)
    {
        this(ItemStack.of(material));
    }

    /**
     * Constructs a new {@link SkyBlockItemBuilder} based on the given {@link Material} and amount.
     *
     * @param material The {@link Material}
     * @param amount   The amount
     */
    public SkyBlockItemBuilder(Material material, int amount)
    {
        this(ItemStack.of(material, amount));
    }

    /**
     * Constructs a new {@link SkyBlockItemBuilder} based on the give {@link ItemStack}.
     *
     * @param base The {@link ItemStack} to use as a base
     */
    public SkyBlockItemBuilder(ItemStack base)
    {
        this.itemStack = base.clone();
        readWriteNBT = NBT.createNBTObject();
        readWriteNBT.mergeCompound(NBT.readNbt(itemStack));

        CustomModelData cmd = CraftItemStack.unwrap(base).get(DataComponents.CUSTOM_MODEL_DATA);
        if(cmd != null)
        {
            customModelDataFloats = new FloatArrayList(cmd.floats());
            customModelDataBooleans = new BooleanArrayList(cmd.flags());
            customModelDataStrings = new ArrayList<>(cmd.strings());
            customModelDataColors = new IntArrayList(cmd.colors());
        }

        translation = ItemTranslation.from(readWriteNBT);
        applyDefaultDataComponents();
    }

    public static SkyBlockItemBuilder of(ItemStack item)
    {
        return new SkyBlockItemBuilder(item);
    }

    public static SkyBlockItemBuilder from(ItemStack item)
    {
        return new SkyBlockItemBuilder(item);
    }

    public static SkyBlockItemBuilder of(Material material)
    {
        return new SkyBlockItemBuilder(material);
    }

    public static SkyBlockItemBuilder from(Material material)
    {
        return new SkyBlockItemBuilder(material);
    }

    /**
     * {@link ItemStack} or return a cached version.
     *
     * @param locale The {@link Locale} to use for localization
     * @return The {@link ItemStack}
     */
    @Override
    public ItemStack get(Locale locale)
    {
        //locale will be ignored
        if(buildCache == null)
        {
            buildCache = build();
        }
        return buildCache;
    }

    /**
     * Builds the {@link ItemStack} in {@link Locale#US}.
     *
     * @return The {@link ItemStack}
     */
    @Override
    public ItemStack get()
    {
        return get(Locale.US);
    }

    /**
     * Builds the {@link ItemStack}.
     *
     * @return The {@link ItemStack}
     */
    public ItemStack build()
    {
        ItemStack itemStack = this.itemStack.clone();
        translation.apply(readWriteNBT);

        itemStack.unsetData(DataComponentTypes.ITEM_NAME);
        itemStack.unsetData(DataComponentTypes.CUSTOM_NAME);
        itemStack.unsetData(DataComponentTypes.LORE);


        NBT.modify(itemStack, readWriteItemNBT ->
        {
            readWriteItemNBT.mergeCompound(readWriteNBT);
        });

        if(customModelDataFloats != null || customModelDataBooleans != null || customModelDataStrings != null || customModelDataColors != null)
        {
            CraftItemStack.unwrap(itemStack)
                    .set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(customModelDataFloats != null ? new FloatArrayList(customModelDataFloats) : new FloatArrayList(), customModelDataBooleans != null ? new BooleanArrayList(customModelDataBooleans) : new BooleanArrayList(), customModelDataStrings != null ? new ArrayList<>(customModelDataStrings) : new ArrayList<>(), customModelDataColors != null ? new IntArrayList(customModelDataColors) : new IntArrayList()));
        }

        return itemStack;
    }

    //<editor-fold desc="base">

    public SkyBlockItemBuilder applyDefaultDataComponents()
    {
        buildCache = null;
        this.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().build());
        this.set(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplay.tooltipDisplay()
                .hiddenComponents(Registry.DATA_COMPONENT_TYPE.stream()
                        .filter(dataComponentType -> dataComponentType != DataComponentTypes.LORE)
                        .collect(Collectors.toSet()))
                .build());
        this.set(DataComponentTypes.UNBREAKABLE);
        return this;
    }

    public SkyBlockItemBuilder modifyNBT(Consumer<ReadWriteNBT> modifier)
    {
        buildCache = null;

        modifier.accept(readWriteNBT);
        return this;
    }

    /**
     * Sets the {@link Material} of this builder.
     *
     * @param material The {@link Material}
     * @return The builder instance
     */
    public SkyBlockItemBuilder setMaterial(Material material)
    {
        buildCache = null;

        itemStack = itemStack.withType(material);
        return this;
    }

    /**
     * Sets the amount.
     *
     * @param amount The amount
     * @return The builder instance
     */
    public SkyBlockItemBuilder setAmount(int amount)
    {
        buildCache = null;

        itemStack.setAmount(amount);
        return this;
    }

    //</editor-fold>


    public SkyBlockItemBuilder translationName(String name)
    {
        buildCache = null;
        translation.name(name);
        return this;
    }

    public SkyBlockItemBuilder translationLoreSetSection(LoreSection section, List<String> lines)
    {
        buildCache = null;
        translation.loreSetSection(section, lines);
        return this;
    }

    public SkyBlockItemBuilder translationLoreAppendToSection(LoreSection section, List<String> lines)
    {
        buildCache = null;
        translation.loreAppendToSection(section, lines);
        return this;
    }

    public SkyBlockItemBuilder translationLoreAppendToSection(LoreSection section, String... lines)
    {
        buildCache = null;
        translation.loreAppendToSection(section, Arrays.asList(lines));
        return this;
    }

    public SkyBlockItemBuilder translationLoreSet(Map<LoreSection, List<String>> lore)
    {
        buildCache = null;
        translation.loreSet(lore);
        return this;
    }

    public SkyBlockItemBuilder translationLoreMerge(Map<LoreSection, List<String>> lore)
    {
        buildCache = null;
        translation.loreMerge(lore);
        return this;
    }

    public SkyBlockItemBuilder translationLoreRemoveSection(LoreSection... sections)
    {
        buildCache = null;
        translation.loreRemoveSection(sections);
        return this;
    }

    public SkyBlockItemBuilder setTranslationReplacements(Map<String, String> replacements)
    {
        buildCache = null;
        translation.setTranslationReplacements(replacements);
        return this;
    }

    public SkyBlockItemBuilder addTranslationReplacement(String key, String value)
    {
        buildCache = null;
        translation.addTranslationReplacement(key, value);
        return this;
    }

    public SkyBlockItemBuilder addTranslationReplacement(String key, Object value)
    {
        buildCache = null;
        translation.addTranslationReplacement(key, value.toString());
        return this;
    }

    public SkyBlockItemBuilder removeTranslationReplacement(String key)
    {
        buildCache = null;
        translation.removeTranslationReplacement(key);
        return this;
    }

    public SkyBlockItemBuilder setDecrease(int decrease)
    {
        buildCache = null;
        translation.setDecrease(decrease);
        return this;
    }

    //<editor-fold desc="custom model data">

    /**
     * Adds the given custom model data entry to the `floats` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(float value)
    {
        buildCache = null;

        if(customModelDataFloats == null)
        {
            customModelDataFloats = new FloatArrayList();
        }

        customModelDataFloats.add(value);
        return this;
    }

    /**
     * Adds the given custom model data entry to the `floats` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(double value)
    {
        return addCustomModelData((float) value);
    }

    /**
     * Adds the given custom model data entry to the `floats` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(int value)
    {
        return addCustomModelData((float) value);
    }

    /**
     * Adds the given custom model data entry to the `flags` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(boolean value)
    {
        buildCache = null;

        if(customModelDataBooleans == null)
        {
            customModelDataBooleans = new BooleanArrayList();
        }

        customModelDataBooleans.add(value);
        return this;
    }

    /**
     * Adds the given custom model data entry to the `strings` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(String value)
    {
        buildCache = null;

        if(customModelDataStrings == null)
        {
            customModelDataStrings = new ArrayList<>();
        }

        customModelDataStrings.add(value);
        return this;
    }

    /**
     * Adds the given custom model data entry to the `colors` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(Color value)
    {
        buildCache = null;

        if(customModelDataColors == null)
        {
            customModelDataColors = new IntArrayList();
        }

        customModelDataColors.add(value.asARGB());
        return this;
    }

    /**
     * Adds the given custom model data entry to the `colors` section.
     *
     * @param value The value to add
     * @return The builder instance
     */
    public SkyBlockItemBuilder addCustomModelData(java.awt.Color value)
    {
        return addCustomModelData(Color.fromARGB(value.getRGB()));
    }

    /**
     * Sets the custom model data entry in `floats` at the given index to the given value,
     * filling smaller indices with zeros if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, float value)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException(index);
        }

        buildCache = null;

        if(customModelDataFloats == null)
        {
            customModelDataFloats = new FloatArrayList();
        }

        while(customModelDataFloats.size() <= index)
        {
            customModelDataFloats.add(0);
        }

        customModelDataFloats.set(index, value);
        return this;
    }

    /**
     * Sets the custom model data entry in `floats` at the given index to the given value,
     * filling smaller indices with zeros if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, double value)
    {
        return setCustomModelData(index, (float) value);
    }

    /**
     * Sets the custom model data entry in `floats` at the given index to the given value,
     * filling smaller indices with zeros if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, int value)
    {
        return setCustomModelData(index, (float) value);
    }

    /**
     * Sets the custom model data entry in `flags` at the given index to the given value,
     * filling smaller indices with `false` if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, boolean value)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException(index);
        }

        buildCache = null;

        if(customModelDataBooleans == null)
        {
            customModelDataBooleans = new BooleanArrayList();
        }

        while(customModelDataBooleans.size() <= index)
        {
            customModelDataBooleans.add(false);
        }

        customModelDataBooleans.set(index, value);
        return this;
    }

    /**
     * Sets the custom model data entry in `strings` at the given index to the given value,
     * filling smaller indices with empty strings if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, String value)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException(index);
        }

        buildCache = null;

        if(customModelDataStrings == null)
        {
            customModelDataStrings = new ArrayList<>();
        }

        while(customModelDataStrings.size() <= index)
        {
            customModelDataStrings.add("");
        }

        customModelDataStrings.set(index, value);
        return this;
    }

    /**
     * Sets the custom model data entry in `colors` at the given index to the given value,
     * filling smaller indices with white if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, Color value)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException(index);
        }

        buildCache = null;

        if(customModelDataColors == null)
        {
            customModelDataColors = new IntArrayList();
        }

        while(customModelDataColors.size() <= index)
        {
            customModelDataColors.add(0xFFFFFFFF);
        }

        customModelDataColors.set(index, value.asARGB());
        return this;
    }

    /**
     * Sets the custom model data entry in `colors` at the given index to the given value,
     * filling smaller indices with white if necessary.
     *
     * @param index The index
     * @param value The value
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(int index, java.awt.Color value)
    {
        return setCustomModelData(index, Color.fromARGB(value.getRGB()));
    }

    /**
     * Sets all custom model data entries.
     *
     * @param floats  The `floats` section
     * @param flags   The `flags` section
     * @param strings The `strings` section
     * @param colors  The `colors` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(float[] floats, boolean[] flags, String[] strings, Color[] colors)
    {
        buildCache = null;

        customModelDataFloats = new FloatArrayList(floats);
        customModelDataBooleans = new BooleanArrayList(flags);
        customModelDataStrings = new ArrayList<>(Arrays.asList(strings));
        customModelDataColors = Arrays.stream(colors)
                .map(Color::asARGB)
                .collect(Collectors.toCollection(IntArrayList::new));
        return this;
    }

    /**
     * Sets all custom model data entries in the `floats` section.
     *
     * @param floats The `floats` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(float[] floats)
    {
        buildCache = null;

        customModelDataFloats = new FloatArrayList(floats);
        return this;
    }

    /**
     * Sets all custom model data entries in the `flags` section.
     *
     * @param flags The `flags` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(boolean[] flags)
    {
        buildCache = null;

        customModelDataBooleans = new BooleanArrayList(flags);
        return this;
    }

    /**
     * Sets all custom model data entries in the `strings` section.
     *
     * @param strings The `strings` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(String[] strings)
    {
        buildCache = null;

        customModelDataStrings = new ArrayList<>(Arrays.asList(strings));
        return this;
    }

    /**
     * Sets all custom model data entries in the `colors` section.
     *
     * @param colors The `colors` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(Color[] colors)
    {
        buildCache = null;

        customModelDataColors = Arrays.stream(colors)
                .map(Color::asARGB)
                .collect(Collectors.toCollection(IntArrayList::new));
        return this;
    }

    /**
     * Clears all custom model data entries.
     *
     * @param floats  The `floats` section
     * @param flags   The `flags` section
     * @param strings The `strings` section
     * @param colors  The `colors` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelData(List<? extends Float> floats, List<? extends Boolean> flags, List<? extends String> strings, List<? extends Color> colors)
    {
        buildCache = null;

        customModelDataFloats = new FloatArrayList(floats);
        customModelDataBooleans = new BooleanArrayList(flags);
        customModelDataStrings = new ArrayList<>(strings);
        customModelDataColors = colors.stream().map(Color::asARGB).collect(Collectors.toCollection(IntArrayList::new));
        return this;
    }

    /**
     * Sets all custom model data entries in the `floats` section.
     *
     * @param floats The `floats` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelDataFloats(List<? extends Float> floats)
    {
        buildCache = null;

        customModelDataFloats = new FloatArrayList(floats);
        return this;
    }

    /**
     * Sets all custom model data entries in the `flags` section.
     *
     * @param flags The `flags` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelDataFlags(List<? extends Boolean> flags)
    {
        buildCache = null;

        customModelDataBooleans = new BooleanArrayList(flags);
        return this;
    }

    /**
     * Sets all custom model data entries in the `strings` section.
     *
     * @param strings The `strings` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelDataStrings(List<? extends String> strings)
    {
        buildCache = null;

        customModelDataStrings = new ArrayList<>(strings);
        return this;
    }

    /**
     * Sets all custom model data entries in the `colors` section.
     *
     * @param colors The `colors` section
     * @return The builder instance
     */
    public SkyBlockItemBuilder setCustomModelDataColors(List<? extends Color> colors)
    {
        buildCache = null;

        customModelDataColors = colors.stream().map(Color::asARGB).collect(Collectors.toCollection(IntArrayList::new));
        return this;
    }

    /**
     * Clears all custom model data entries.
     *
     * @return The builder instance
     */
    public SkyBlockItemBuilder clearCustomModelData()
    {
        buildCache = null;

        itemStack.unsetData(DataComponentTypes.CUSTOM_MODEL_DATA);

        customModelDataFloats = null;
        customModelDataBooleans = null;
        customModelDataStrings = null;
        customModelDataColors = null;

        return this;
    }

    //</editor-fold>

    //<editor-fold desc="data component">

    /**
     * Proxy method for {@link ItemStack#setData(DataComponentType.Valued, DataComponentBuilder)}
     * <p>
     * Sets the given data component to the specified value.
     *
     * @param type         the data component type
     * @param valueBuilder value builder
     * @param <T>          value type
     * @return The builder instance
     */
    @Experimental
    public <T> SkyBlockItemBuilder set(DataComponentType.Valued<T> type, DataComponentBuilder<T> valueBuilder)
    {
        buildCache = null;

        itemStack.setData(type, valueBuilder);
        return this;
    }

    /**
     * Proxy method for {@link ItemStack#setData(DataComponentType.Valued, Object)}
     * <p>
     * Sets the given data component to the specified value.
     *
     * @param type  the data component type
     * @param value value to set
     * @param <T>   value type
     * @return The builder instance
     */
    @Experimental
    public <T> SkyBlockItemBuilder set(final DataComponentType.Valued<T> type, T value)
    {
        buildCache = null;

        itemStack.setData(type, value);
        return this;
    }

    /**
     * Proxy method for {@link ItemStack#setData(DataComponentType.NonValued)}
     * <p>
     * Marks the given component as present in the item stack.
     *
     * @param type the data component type
     * @return The builder instance
     */
    @Experimental
    public SkyBlockItemBuilder set(DataComponentType.NonValued type)
    {
        buildCache = null;

        itemStack.setData(type);
        return this;
    }

    /**
     * Proxy method for {@link ItemStack#unsetData(DataComponentType)}
     * <p>
     * Marks the given component as removed from the item stack.
     *
     * @param type the data component type
     * @return The builder instance
     */
    @Experimental
    public SkyBlockItemBuilder unset(DataComponentType type)
    {
        buildCache = null;

        itemStack.unsetData(type);
        return this;
    }

    //</editor-fold>

    /**
     * Clones this builder.
     *
     * @return The cloned builder
     */
    @Override
    public SkyBlockItemBuilder clone()
    {
        try
        {
            SkyBlockItemBuilder clone = ((SkyBlockItemBuilder) super.clone());
            clone.itemStack = itemStack.clone();
            // CRITICAL FIX: Create new buildCache to prevent race condition between concurrent clones
            // HashMap is not thread-safe, and shared buildCache caused items to show wrong player data
            clone.buildCache = null;
            // CRITICAL FIX: Clone translation to prevent shared mutable state (lore, replacements maps)
            // Multiple concurrent players were sharing the same translation object causing data leakage
            clone.translation = translation.copy();
            // CRITICAL FIX: Clone ReadWriteNBT to prevent shared mutable NBT data
            // Even though not currently causing issues, this is a latent bug
            clone.readWriteNBT = NBT.createNBTObject();
            clone.readWriteNBT.mergeCompound(readWriteNBT);
            if(customModelDataFloats != null)
            {
                clone.customModelDataFloats = new FloatArrayList(customModelDataFloats);
            }
            if(customModelDataBooleans != null)
            {
                clone.customModelDataBooleans = new BooleanArrayList(customModelDataBooleans);
            }
            if(customModelDataStrings != null)
            {
                clone.customModelDataStrings = new ArrayList<>(customModelDataStrings);
            }
            if(customModelDataColors != null)
            {
                clone.customModelDataColors = new IntArrayList(customModelDataColors);
            }
            if(placeholders != null)
            {
                clone.placeholders = new HashMap<>(placeholders);
            }

            return clone;
        }
        catch(CloneNotSupportedException e)
        {
            throw new AssertionError(e);
        }
    }
}
