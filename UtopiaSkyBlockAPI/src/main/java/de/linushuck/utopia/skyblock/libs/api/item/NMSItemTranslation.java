package de.linushuck.utopia.skyblock.libs.api.item;

import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import lombok.Getter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import java.util.*;

@Getter
public class NMSItemTranslation
{
    private final ItemTranslationData data;

    public NMSItemTranslation()
    {
        this.data = new ItemTranslationData();
    }

    private NMSItemTranslation(ItemTranslationData data)
    {
        this.data = data;
    }

    public static NMSItemTranslation from(ItemStack item)
    {
        ItemTranslationData data = new ItemTranslationData();

        CustomData customData = item.get(DataComponents.CUSTOM_DATA);
        if(customData == null)
        {
            return new NMSItemTranslation(data);
        }

        CompoundTag nbt = customData.copyTag();

        if(nbt.contains(ItemTranslation.NBT_NAME_KEY))
        {
            data.setName(nbt.getString(ItemTranslation.NBT_NAME_KEY).orElse(""));
        }

        if(nbt.contains(ItemTranslation.NBT_LORE_KEY))
        {
            String rawLore = nbt.getString(ItemTranslation.NBT_LORE_KEY).orElse("");
            data.setLore(parseHashMap(rawLore));
        }

        if(nbt.contains(ItemTranslation.NBT_TRANSLATION_KEY))
        {
            data.setTranslationEnabled(nbt.getBoolean(ItemTranslation.NBT_TRANSLATION_KEY).orElse(false));
        }

        if(nbt.contains(ItemTranslation.NBT_TRANSLATION_REPLACEMENTS_KEY))
        {
            data.setReplacementsEnabled(nbt.getBoolean(ItemTranslation.NBT_TRANSLATION_REPLACEMENTS_KEY).orElse(false));
        }

        if(nbt.contains(ItemTranslation.NBT_REPLACEMENTS_KEY))
        {
            String rawReplacements = nbt.getString(ItemTranslation.NBT_REPLACEMENTS_KEY).orElse("");
            data.setReplacements(parseReplacementsMap(rawReplacements));
        }

        if(nbt.contains(ItemTranslation.NBT_DECREASE_KEY))
        {
            data.setDecrease(nbt.getInt(ItemTranslation.NBT_DECREASE_KEY).orElse(0));
        }

        return new NMSItemTranslation(data);
    }

    private static Map<LoreSection, List<String>> parseHashMap(String raw)
    {
        Map<LoreSection, List<String>> result = new HashMap<>();

        if(raw == null || raw.isEmpty())
        {
            return result;
        }

        String[] pairs = raw.split("\\|,HM,\\|", -1);

        for(String pair : pairs)
        {
            if(pair.isEmpty())
            {
                continue;
            }

            String[] keyValue = pair.split("\\|:HM:\\|", 2);
            if(keyValue.length != 2)
            {
                continue;
            }

            try
            {
                LoreSection section = LoreSection.valueOf(keyValue[0]);
                List<String> lines = parseArrayList(keyValue[1]);
                result.put(section, lines);
            }
            catch(IllegalArgumentException e)
            {
            }
        }

        return result;
    }

    private static List<String> parseArrayList(String raw)
    {
        if(raw == null || raw.isEmpty())
        {
            return new ArrayList<>();
        }

        String[] parts = raw.split("\\|,AL,\\|", -1);
        return new ArrayList<>(Arrays.asList(parts));
    }

    private static Map<String, String> parseReplacementsMap(String raw)
    {
        Map<String, String> result = new HashMap<>();

        if(raw == null || raw.isEmpty())
        {
            return result;
        }

        String[] pairs = raw.split("\\|,HM,\\|", -1);

        for(String pair : pairs)
        {
            if(pair.isEmpty())
            {
                continue;
            }

            String[] keyValue = pair.split("\\|:HM:\\|", 2);
            if(keyValue.length != 2)
            {
                continue;
            }

            result.put(keyValue[0], keyValue[1]);
        }

        return result;
    }

    public List<String> getSection(LoreSection section)
    {
        return data.getLore().getOrDefault(section, new ArrayList<>());
    }

    public boolean hasSection(LoreSection section)
    {
        return data.getLore().containsKey(section) && !data.getLore().get(section).isEmpty();
    }

    public boolean hasName()
    {
        return data.getName() != null && !data.getName().isEmpty();
    }

    public boolean isTranslationEnabled()
    {
        return data.isTranslationEnabled();
    }

    public boolean isReplacementsEnabled()
    {
        return data.isReplacementsEnabled();
    }

    public String getName()
    {
        return data.getName();
    }

    public Map<LoreSection, List<String>> getLore()
    {
        return data.getLore();
    }

    public Map<String, String> getReplacements()
    {
        return data.getReplacements();
    }

    public int getDecrease()
    {
        return data.getDecrease();
    }
}