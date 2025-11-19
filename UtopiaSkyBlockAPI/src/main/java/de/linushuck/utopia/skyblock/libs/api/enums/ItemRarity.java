package de.linushuck.utopia.skyblock.libs.api.enums;

import lombok.Getter;

public enum ItemRarity
{
    NO_SELECTION("asdfghjkl"), //Only used for gui
    COMMON("white"),
    UNCOMMON("green"),
    RARE("blue"),
    EPIC("dark_purple"),
    LEGENDARY("gold"),
    MYTHIC("light_purple");
    @Getter
    private final String color;
    @Getter
    private final String name;

    ItemRarity(String color)
    {
        this.color = color;
        this.name = this.name();
    }

    public static ItemRarity recognizeRarity(String name)
    {
        String color = name.substring(1, name.indexOf(">"));
        for(ItemRarity rarity : values())
        {
            if(rarity.getColor().equals(color))
            {
                return rarity;
            }
        }
        return null;
    }

    public String getOpeningColor()
    {
        return "<" + this.color + ">";
    }

    public String getClosingColor()
    {
        return "</" + this.color + ">";
    }

    public String getAsString(String toAdd)
    {
        return "<" + this.color + "><bold><language:rarity." + this.name + "." + toAdd + "></bold></" + this.color + ">";
    }


    // Cycles to the next sorting option
    public ItemRarity getNext()
    {
        return values()[(this.ordinal() + 1) % values().length];
    }

    // Cycles to the previous sorting option
    public ItemRarity getPrevious()
    {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }
}
