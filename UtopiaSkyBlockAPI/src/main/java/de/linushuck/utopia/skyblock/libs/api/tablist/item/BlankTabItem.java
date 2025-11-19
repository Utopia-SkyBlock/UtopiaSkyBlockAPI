package de.linushuck.utopia.skyblock.libs.api.tablist.item;

import de.linushuck.utopia.skyblock.libs.api.tablist.util.Skin;
import de.linushuck.utopia.skyblock.libs.api.tablist.util.Skins;
import lombok.ToString;

/**
 * A blank TextTabItem
 */
@ToString
public class BlankTabItem extends TextTabItem
{
    public BlankTabItem(Skin skin)
    {
        super("", 1000, skin);
    }

    public BlankTabItem()
    {
        this(Skins.DEFAULT_SKIN);
    }
}