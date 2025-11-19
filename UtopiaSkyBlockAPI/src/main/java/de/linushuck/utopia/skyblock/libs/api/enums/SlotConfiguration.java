package de.linushuck.utopia.skyblock.libs.api.enums;


public enum SlotConfiguration
{
    SkillsLevel1GUI(new int[]{9, 18, 27, 28, 29, 20, 11, 2, 3, 4, 13, 22, 31, 32, 33, 24, 15, 6, 7, 8, 17, 26, 35, 44, 53}),
    PLAYERS_TABLIST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40}),

    REWARDS_ONE_ITEM(new int[]{22}),
    REWARDS_TWO_ITEMS(new int[]{21, 23,}),
    REWARDS_THREE_ITEMS(new int[]{22, 30, 32}),
    REWARDS_FOUR_ITEMS(new int[]{20, 21, 23, 24}),
    REWARDS_FIVE_ITEMS(new int[]{20, 21, 23, 24, 31}),
    REWARDS_SIX_ITEMS(new int[]{20, 21, 23, 24, 30, 32}),
    ;

    private final int[] slots;

    SlotConfiguration(int[] slots)
    {
        this.slots = slots;
    }

    public int[] getSlots()
    {
        return slots;
    }

    public int getSlot(int index)
    {
        return slots[index];
    }
}
