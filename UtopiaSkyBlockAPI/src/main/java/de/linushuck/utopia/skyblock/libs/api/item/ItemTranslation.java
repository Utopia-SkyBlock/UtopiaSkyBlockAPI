package de.linushuck.utopia.skyblock.libs.api.item;

import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.CustomHashMap;
import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types.CustomSection;
import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types.CustomString;
import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types.CustomStringArrayList;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import io.papermc.paper.datacomponent.item.ItemLore;
import lombok.Getter;
import net.kyori.adventure.text.Component;

import java.util.*;

@Getter
public class ItemTranslation
{
    public static final String NBT_TRANSLATION_KEY = "item-translation";
    public static final String NBT_TRANSLATION_REPLACEMENTS_KEY = "item-translation-replacements";
    public static final String NBT_NAME_KEY = "item-display-name";
    public static final String NBT_LORE_KEY = "item-lore";
    public static final String NBT_REPLACEMENTS_KEY = "item-translation-replacements-map";
    public static final String NBT_DECREASE_KEY = "item-translation-decrease";

    private static final CustomHashMap<LoreSection, List<String>, CustomSection, CustomStringArrayList> LORE_HANDLER = new CustomHashMap<>(CustomSection.class, CustomStringArrayList.class);
    private static final CustomHashMap<String, String, CustomString, CustomString> REPLACEMENTS_HANDLER = new CustomHashMap<>(CustomString.class, CustomString.class);

    private final ItemTranslationData data;

    public ItemTranslation()
    {
        this.data = new ItemTranslationData();
    }

    private ItemTranslation(ItemTranslationData data)
    {
        this.data = data;
    }

    public static ItemTranslation from(ReadWriteNBT readWriteNBT)
    {
        ItemTranslationData data = new ItemTranslationData();

        if(readWriteNBT.hasTag(NBT_NAME_KEY))
        {
            data.setName(readWriteNBT.getString(NBT_NAME_KEY));
        }

        if(readWriteNBT.hasTag(NBT_LORE_KEY))
        {
            Map<CustomSection, CustomStringArrayList> nbtLore = readWriteNBT.get(NBT_LORE_KEY, LORE_HANDLER);
            data.setLore(convertFromNBT(nbtLore));
        }

        if(readWriteNBT.hasTag(NBT_TRANSLATION_KEY))
        {
            data.setTranslationEnabled(readWriteNBT.getBoolean(NBT_TRANSLATION_KEY));
        }
        if(readWriteNBT.hasTag(NBT_TRANSLATION_REPLACEMENTS_KEY))
        {
            data.setReplacementsEnabled(readWriteNBT.getBoolean(NBT_TRANSLATION_REPLACEMENTS_KEY));
        }
        if(readWriteNBT.hasTag(NBT_REPLACEMENTS_KEY))
        {
            Map<CustomString, CustomString> nbtReplacements = readWriteNBT.get(NBT_REPLACEMENTS_KEY, REPLACEMENTS_HANDLER);
            data.setReplacements(convertReplacementsFromNBT(nbtReplacements));
        }
        if(readWriteNBT.hasTag(NBT_DECREASE_KEY))
        {
            data.setDecrease(readWriteNBT.getInteger(NBT_DECREASE_KEY));
        }

        return new ItemTranslation(data);
    }

    private static Map<LoreSection, List<String>> convertFromNBT(Map<CustomSection, CustomStringArrayList> nbtMap)
    {
        Map<LoreSection, List<String>> result = new HashMap<>();
        for(Map.Entry<CustomSection, CustomStringArrayList> entry : nbtMap.entrySet())
        {
            result.put(entry.getKey().getValue(), entry.getValue().getValue());
        }
        return result;
    }

    private static Map<CustomSection, CustomStringArrayList> convertToNBT(Map<LoreSection, List<String>> map)
    {
        Map<CustomSection, CustomStringArrayList> result = new HashMap<>();
        for(Map.Entry<LoreSection, List<String>> entry : map.entrySet())
        {
            result.put(new CustomSection(entry.getKey()), new CustomStringArrayList(entry.getValue()));
        }
        return result;
    }

    private static Map<String, String> convertReplacementsFromNBT(Map<CustomString, CustomString> nbtMap)
    {
        Map<String, String> result = new HashMap<>();
        for(Map.Entry<CustomString, CustomString> entry : nbtMap.entrySet())
        {
            result.put(entry.getKey().getValue(), entry.getValue().getValue());
        }
        return result;
    }

    private static Map<CustomString, CustomString> convertReplacementsToNBT(Map<String, String> map)
    {
        Map<CustomString, CustomString> result = new HashMap<>();
        for(Map.Entry<String, String> entry : map.entrySet())
        {
            result.put(new CustomString(entry.getKey()), new CustomString(entry.getValue()));
        }
        return result;
    }

    public void apply(ReadWriteNBT readWriteNBT)
    {
        if(data.getName() != null)
        {
            readWriteNBT.setString(NBT_NAME_KEY, data.getName());
        }
        else
        {
            readWriteNBT.removeKey(NBT_NAME_KEY);
        }

        if(data.getLore() != null && !data.getLore().isEmpty())
        {
            Map<CustomSection, CustomStringArrayList> nbtLore = convertToNBT(data.getLore());
            readWriteNBT.set(NBT_LORE_KEY, nbtLore, LORE_HANDLER);
        }
        else
        {
            readWriteNBT.removeKey(NBT_LORE_KEY);
        }

        readWriteNBT.setBoolean(NBT_TRANSLATION_KEY, data.isTranslationEnabled());
        readWriteNBT.setBoolean(NBT_TRANSLATION_REPLACEMENTS_KEY, data.isReplacementsEnabled());

        if(data.getReplacements() != null && !data.getReplacements().isEmpty())
        {
            Map<CustomString, CustomString> nbtReplacements = convertReplacementsToNBT(data.getReplacements());
            readWriteNBT.set(NBT_REPLACEMENTS_KEY, nbtReplacements, REPLACEMENTS_HANDLER);
        }
        else
        {
            readWriteNBT.removeKey(NBT_REPLACEMENTS_KEY);
        }

        readWriteNBT.setInteger(NBT_DECREASE_KEY, data.getDecrease());

    }

    public ItemTranslation setEnabled(boolean enabled)
    {
        data.setTranslationEnabled(enabled);
        return this;
    }

    public ItemTranslation name(String name)
    {
        setEnabled(true);
        data.setName(name);
        return this;
    }

    public ItemTranslation loreSetSection(LoreSection section, List<String> lines)
    {
        setEnabled(true);
        if(data.getLore() == null)
        {
            data.setLore(new HashMap<>());
        }
        data.getLore().put(section, lines);
        return this;
    }

    public ItemTranslation loreAppendToSection(LoreSection section, List<String> lines)
    {
        setEnabled(true);
        if(data.getLore() == null)
        {
            data.setLore(new HashMap<>());
        }
        data.getLore().computeIfAbsent(section, k -> new ArrayList<>()).addAll(lines);
        return this;
    }

    public ItemTranslation loreAppendToSection(LoreSection section, String... lines)
    {
        setEnabled(true);
        if(data.getLore() == null)
        {
            data.setLore(new HashMap<>());
        }
        data.getLore().computeIfAbsent(section, k -> new ArrayList<>()).addAll(Arrays.asList(lines));
        return this;
    }

    public ItemTranslation loreSet(Map<LoreSection, List<String>> lore)
    {
        setEnabled(true);
        data.setLore(lore);
        return this;
    }

    public ItemTranslation loreMerge(Map<LoreSection, List<String>> lore)
    {
        setEnabled(true);
        if(data.getLore() == null)
        {
            data.setLore(new HashMap<>(lore));
            return this;
        }
        for(var entry : lore.entrySet())
        {
            data.getLore().computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).addAll(entry.getValue());
        }
        return this;
    }

    public ItemTranslation loreRemoveSection(LoreSection... sections)
    {
        setEnabled(true);
        if(data.getLore() == null)
        {
            return this;
        }
        for(LoreSection section : sections)
        {
            data.getLore().remove(section);
        }
        return this;
    }

    public ItemTranslation setTranslationReplacements(Map<String, String> replacements)
    {
        data.setReplacementsEnabled(true);
        Map<String, String> lowerCaseMap = new HashMap<>();
        for(Map.Entry<String, String> entry : replacements.entrySet())
        {
            lowerCaseMap.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        data.setReplacements(lowerCaseMap);
        return this;
    }

    public ItemTranslation addTranslationReplacement(String key, String value)
    {
        data.setReplacementsEnabled(true);
        data.getReplacements().put(key.toLowerCase(), value);
        return this;
    }

    public ItemTranslation removeTranslationReplacement(String key)
    {
        data.setReplacementsEnabled(true);
        data.getReplacements().remove(key.toLowerCase());
        return this;
    }

    public ItemTranslation setDecrease(int decrease)
    {
        data.setDecrease(decrease);
        return this;
    }

    public Component getITEM_NAME()
    {
        if(this.getData().getName() == null)
        {
            return Component.text("Unnamed Item");
        }
        return Component.text(this.getData().getName());
    }

    public ItemLore getLORE()
    {
        List<Component> loreLines = new ArrayList<>();

        if(this.getData().getLore() != null)
        {
            boolean isFirst = true;
            boolean lastIsEmpty = false;
            for(LoreSection loreSection : LoreSection.values())
            {
                List<String> strings = data.getLore().get(loreSection);
                if(strings == null)
                {
                    continue;
                }
                if(loreSection.isNewLineBefore() && !isFirst)
                {
                    loreLines.add(Component.empty());
                }
                isFirst = false;
                lastIsEmpty = false;
                loreLines.addAll(strings.stream().map(Component::text).toList());
                if(loreSection.isNewLineAfter())
                {
                    lastIsEmpty = true;
                    loreLines.add(Component.empty());
                }
            }
            if(lastIsEmpty && !loreLines.isEmpty())
            {
                loreLines.removeLast();
            }
        }
        if(this.data.getReplacements() != null && !this.data.getReplacements().isEmpty())
        {
            loreLines.add(Component.text("--------------------"));
            for(Map.Entry<String, String> entry : this.data.getReplacements().entrySet())
            {
                loreLines.add(Component.text(entry.getKey() + ": " + entry.getValue()));
            }
        }
        return ItemLore.lore().lines(loreLines).build();
    }

    /**
     * Creates a deep copy of this ItemTranslation with all mutable data cloned.
     * This is critical for thread-safety when cloning SkyBlockItemBuilder instances.
     */
    public ItemTranslation copy()
    {
        ItemTranslationData copiedData = new ItemTranslationData();
        copiedData.setTranslationEnabled(this.data.isTranslationEnabled());
        copiedData.setReplacementsEnabled(this.data.isReplacementsEnabled());
        copiedData.setName(this.data.getName()); // String is immutable, safe to share
        copiedData.setDecrease(this.data.getDecrease());

        // Deep copy lore map
        if(this.data.getLore() != null)
        {
            Map<LoreSection, List<String>> copiedLore = new HashMap<>();
            for(Map.Entry<LoreSection, List<String>> entry : this.data.getLore().entrySet())
            {
                copiedLore.put(entry.getKey(), new ArrayList<>(entry.getValue()));
            }
            copiedData.setLore(copiedLore);
        }

        // Deep copy replacements map
        if(this.data.getReplacements() != null)
        {
            copiedData.setReplacements(new HashMap<>(this.data.getReplacements()));
        }

        return new ItemTranslation(copiedData);
    }
}