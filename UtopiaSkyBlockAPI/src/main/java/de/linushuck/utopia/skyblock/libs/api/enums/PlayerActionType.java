package de.linushuck.utopia.skyblock.libs.api.enums;

import lombok.Getter;

public enum PlayerActionType
{
    NONE(-1, "none"),
    RIGHT_CLICK_ACTION(0, "right_click_any_action"),
    NON_SHIFT_RIGHT_CLICK_ACTION(1, "non_shift_right_click_action"),
    SHIFT_RIGHT_CLICK_ACTION(2, "shift_right_click_action"),
    LEFT_CLICK_ACTION(3, "left_click_any_action"),
    NON_SHIFT_LEFT_CLICK_ACTION(4, "non_shift_left_click_action"),
    SHIFT_LEFT_CLICK_ACTION(5, "shift_left_click_action"),
    BLOCK_BREAK_ACTION(6, "block_break_action"),
    BLOCK_PLACE_ACTION(7, "block_place_action"),
    PLAYER_FISH(8, "player_fish");

    @Getter
    private final int value;

    @Getter
    private final String actionName;

    PlayerActionType(int value, String actionName)
    {
        this.value = value;
        this.actionName = actionName;
    }

    @Override
    public String toString()
    {
        return actionName;
    }
}
