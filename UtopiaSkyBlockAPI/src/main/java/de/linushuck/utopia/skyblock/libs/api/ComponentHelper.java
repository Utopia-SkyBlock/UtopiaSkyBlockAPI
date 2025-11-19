package de.linushuck.utopia.skyblock.libs.api;

import de.linushuck.utopia.skyblock.libs.api.enums.SeparatorType;
import de.linushuck.utopia.skyblock.libs.essentials.BaseComponentHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.HashMap;

public class ComponentHelper extends BaseComponentHelper
{
    private static final HashMap<SeparatorType, HashMap<String, Component>> separators = new HashMap<>();

    public static Component getSeparator(SeparatorType type, String color)
    {
        separators.putIfAbsent(type, new HashMap<>());
        if(separators.get(type).containsKey(color))
        {
            return separators.get(type).get(color);
        }
        String separatorLine = type.getSeparator().repeat(70);
        Component separator = ComponentHelper.mini("<" + color + ">" + separatorLine + "</" + color + ">");
        separators.get(type).put(color, separator);
        return separator;
    }

    public static Component getSeparator(SeparatorType type, String color, int repeat)
    {
        String separatorLine = type.getSeparator().repeat(repeat);
        return ComponentHelper.mini("<" + color + ">" + separatorLine + "</" + color + ">");
    }

    public static String legacy(Component message)
    {
        return LegacyComponentSerializer.legacySection().serialize(message);
    }

    public static String runCommandAndHover(String command, String hoverMessage, String message)
    {
        if(command == null)
        {
            return "<hover:show_text:" + hoverMessage + ">" + message + "</hover>";
        }
        if(!command.startsWith("/"))
        {
            command = "/" + command;
        }
        return "<click:run_command:'" + command + "'><hover:show_text:'" + hoverMessage + "'>" + message + "</hover></click>";
    }

    public static String copyAndHover(String toCopy, String hoverMessage, String message)
    {
        return "<click:copy_to_clipboard:'" + toCopy + "'><hover:show_text:'" + hoverMessage + "'>" + message + "</hover></click>";
    }

    public static String openURLAndHover(String urlToOpen, String hoverMessage, String message)
    {
        return "<click:open_url:'" + urlToOpen + "'><hover:show_text:'" + hoverMessage + "'>" + message + "</hover></click>";
    }

    public static String hover(String hoverMessage, String message)
    {
        return "<hover:show_text:'" + hoverMessage + "'>" + message + "</hover>";
    }


    public static Component generateProgressBarComponent(int percentage, char barChar, int totalBars)
    {
        int numBars = (percentage * totalBars) / 100;
        int greenBars = numBars;
        int redBars = totalBars - numBars;

        return Component.text(String.valueOf(barChar).repeat(Math.max(0, greenBars)))
                .color(NamedTextColor.GREEN)
                .append(Component.text(String.valueOf(barChar).repeat(Math.max(0, redBars))).color(NamedTextColor.RED));
    }

    public static String generateProgressBarString(int percentage, char barChar, int totalBars)
    {
        int numBars = (percentage * totalBars) / 100;
        int greenBars = numBars;
        int redBars = totalBars - numBars;
        return ComponentHelper.miniReverse(Component.text(String.valueOf(barChar).repeat(Math.max(0, greenBars)))
                .color(NamedTextColor.GREEN)
                .append(Component.text(String.valueOf(barChar).repeat(Math.max(0, redBars)))
                        .color(NamedTextColor.RED)));
    }

    public static Component generateProgressBarComponent(int percentage, char barChar)
    {
        return generateProgressBarComponent(percentage, barChar, 10);
    }

    public static String generateProgressBarString(int percentage, char barChar)
    {
        return generateProgressBarString(percentage, barChar, 10);
    }

    public static Component generateProgressBarComponent(int percentage)
    {
        return generateProgressBarComponent(percentage, '█', 10);
    }

    public static String generateProgressBarString(int percentage)
    {
        return generateProgressBarString(percentage, '█', 10);
    }


    public static String convertNewFormatToLegacy(String string)
    {
        return LegacyComponentSerializer.legacySection().serialize(ComponentHelper.mini(string));
    }

    public static String suggestCommandAndHover(String command, String hoverMessage, String message)
    {
        if(!command.startsWith("/"))
        {
            command = "/" + command;
        }
        return "<click:suggest_command:'" + command + "'><hover:show_text:'" + hoverMessage + "'>" + message + "</hover></click>";
    }

    public static String plainText(Component component)
    {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }
}
