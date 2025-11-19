package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui.items.navigator;

public interface NavigableGui
{
    /**
     * Navigate forward in the GUI (next page or scroll down)
     */
    void navigateForward();

    /**
     * Navigate backward in the GUI (previous page or scroll up)
     */
    void navigateBackward();

    /**
     * Navigate to the first position (first page or top of scroll)
     */
    void navigateFirst();

    /**
     * Navigate to the last position (last page or bottom of scroll)
     */
    void navigateLast();

    /**
     * Get the current position (page number or scroll position)
     */
    int getCurrentPosition();

    /**
     * Get the total number of positions (total pages or max scroll position)
     */
    int getTotalPositions();
}
