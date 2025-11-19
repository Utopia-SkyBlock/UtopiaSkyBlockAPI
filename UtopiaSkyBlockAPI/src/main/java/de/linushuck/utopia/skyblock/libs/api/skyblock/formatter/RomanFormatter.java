package de.linushuck.utopia.skyblock.libs.api.skyblock.formatter;

import de.linushuck.utopia.skyblock.libs.api.aaanewstructure.SkyBlockItemBuilder;
import de.linushuck.utopia.skyblock.libs.api.item.ItemUtils;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@NoArgsConstructor
public class RomanFormatter
{

    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static String symbolOne = "<color:#FFFFFF>✦</color>";
    public static String symbolTwo = "<color:#FFFFFF>✦</color>";
    public static String colorOne = "<color:#FFFF55>";
    public static String colorTwo = "<color:#FFAA00>";
    public static String colorThree = "<color:#FF5555>";
    public static String colorClose = "</color>";

    public String toRoman(int num)
    {
        if(num <= 0)
        {
            return num + "";
        }
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < VALUES.length && num > 0; i++)
        {
            while(num >= VALUES[i])
            {
                num -= VALUES[i];
                result.append(SYMBOLS[i]);
            }
        }

        return result.toString();
    }

    public String toRomanWithColor(int num)
    {
        String s = toRoman(num);
        switch(s)
        {
            case "I", "III", "II" ->
            {
                s = "<white>" + s + "</white>";
            }
            case "IV" ->
            {
                s = "<yellow>" + s + "</yellow>";
            }
            case "V" ->
            {
                s = "<dark_purple>" + s + "</dark_purple>";
            }
            case "VI", "IX", "VIII", "VII" ->
            {
                s = "<red>" + s + "</red>";
            }
            case "X" ->
            {
                s = "<gold>" + s + "</gold>";
            }
        }
        return s;
    }

    public String formatCoins(double amount)
    {
        String formatted;
        if(amount >= 1000000)
        {
            formatted = String.format("%.2fM", amount / 1000000);
        }
        else if(amount >= 1000)
        {
            formatted = String.format("%.2fK", amount / 1000);
        }
        else
        {
            formatted = String.format("%.2f", amount);
        }
        return formatted;
    }

    public String formatDamage(double amount, boolean critical)
    {
        String formatted;
        if(amount >= 10000000)
        {
            formatted = String.format("%.2f M", amount / 1000000);
        }
        else if(amount >= 10000)
        {
            formatted = String.format("%.2f K", amount / 1000);
        }
        else
        {
            formatted = String.format("%.0f", amount);
        }

        if(critical && formatted.length() > 3)
        {
            return symbolOne + colorOne + formatted.charAt(0) + colorClose + colorTwo + formatted.charAt(1) + colorClose + colorThree + formatted.substring(2) + colorClose + symbolTwo;
        }
        else
        {
            return colorThree + formatted + colorClose; // Gray for non-critical hits
        }
    }

    public double getAmountFromString(String input)
    {
        double amountRaw = 0;
        char letter = ' ';
        //is last character a letter?
        if(input.isEmpty())
        {
            return -1;
        }
        if(Character.isLetter(input.charAt(input.length() - 1)))
        {
            String amountString = input.substring(0, input.length() - 1);
            try
            {
                amountRaw = Double.parseDouble(amountString);
            }
            catch(Exception e)
            {
                return -1;
            }
            letter = input.charAt(input.length() - 1);
        }
        else
        {
            try
            {
                amountRaw = Double.parseDouble(input);
            }
            catch(Exception e)
            {
                return -1;
            }
        }
        if(amountRaw < 0)
        {
            return -2;
        }
        double finalAmount = 0;
        switch(letter)
        {
            case 'k', 'K' -> finalAmount = amountRaw * 1000;
            case 'm', 'M' -> finalAmount = amountRaw * 1000000;
            case 'b', 'B' -> finalAmount = amountRaw * 1000000000;
            default -> finalAmount = amountRaw;
        }
        return finalAmount;
    }

    public ItemStack getItemWithAmount(double amount)
    {
        SkyBlockItemBuilder itemBuilder = new SkyBlockItemBuilder(Material.EMERALD);
        itemBuilder.translationName("Coins: " + formatCoins(amount));
        ItemStack item = itemBuilder.build();
        ItemUtils.addUUID(item);
        ItemUtils.setNBTDouble(item, "coins", amount);
        return item;
    }
}
