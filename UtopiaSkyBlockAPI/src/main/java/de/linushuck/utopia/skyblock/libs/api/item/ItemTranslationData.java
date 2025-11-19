package de.linushuck.utopia.skyblock.libs.api.item;

import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ItemTranslationData
{
    private boolean translationEnabled = false;
    private boolean replacementsEnabled = false;
    private String name;
    private Map<LoreSection, List<String>> lore;
    private Map<String, String> replacements;
    private int decrease;

    public ItemTranslationData()
    {
        this.lore = new HashMap<>();
        this.replacements = new HashMap<>();
    }
}