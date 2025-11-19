package de.linushuck.utopia.skyblock.libs.api.enums;


import lombok.Getter;
import org.bukkit.NamespacedKey;

public enum LoreSection
{
    NOLINESTART(false, false),
    BASICITEM(false, false),
    STATS(false, false),
    ENCHANTMENTS(true, false),
    DESCRIPTION(true, false),
    DESCRIPTION_NO_LINE(false, false),
    DESCRIPTION_OTHER(true, false),

    RIGHT_CLICK_ANY_ACTION(true, false),
    NON_SHIFT_RIGHT_CLICK_ACTION(true, false),
    SHIFT_RIGHT_CLICK_ACTION(true, false),
    LEFT_CLICK_ANY_ACTION(true, false),
    NON_SHIFT_LEFT_CLICK_ACTION(true, false),
    SHIFT_LEFT_CLICK_ACTION(true, false),
    BLOCK_BREAK_ACTION(true, false),
    BLOCK_PLACE_ACTION(true, false),
    PLAYER_FISH(true, false),

    REFORGES(true, false),
    RUNES(true, false),
    BONUS(true, false),
    MINION1(true, false),
    MINION2(true, false),
    MINIONGENERATEDRESOURCES(true, false),
    FRAGMENT(false, false),
    TYPEOFITEM(true, false),
    MINIONFUEL(true, false),
    MINIONAUTOSHIPPING(true, false),
    SHOP(true, false),
    NOLINESTARTEND(false, false),
    TRADES(true, false),

    // Auction-related sections - order matters for display
    BIDS(false, false),         // Seller info and bids count
    PRICE(true, false),        // Top bid and bidder info
    AUCTION_TIME_LEFT(true, false), // Time remaining
    FEE(true, false),          // Fee information
    ACTION(true, false);      // "Click to inspect" message
    @Getter
    private final boolean newLineBefore;
    @Getter
    private final boolean newLineAfter;

    @Getter
    private final NamespacedKey namespacedKey;

    LoreSection(boolean newLineBefore, boolean newLineAfter)
    {
        this.newLineBefore = newLineBefore;
        this.newLineAfter = newLineAfter;
        this.namespacedKey = new NamespacedKey("skyblockplugin", this.getName());
    }

    public String getName()
    {
        return this.name().toLowerCase();
    }
}
