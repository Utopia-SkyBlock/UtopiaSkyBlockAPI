package de.linushuck.utopia.skyblock.libs.essentials;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class BaseComponentHelper
{
    public static Component mini(String message, TagResolver... placeholders)
    {
        if(message == null)
        {
            message = "";
        }
        if(!message.endsWith("<reset>"))
        {
            message = "<!i><gray>" + message + "<reset>";
        }
        else
        {
            message = "<!i><gray>" + message;
        }
        return MiniMessage.miniMessage().deserialize(message, placeholders);
    }

    public static String miniReverse(Component component)
    {
        return MiniMessage.miniMessage().serialize(component);
    }
}
