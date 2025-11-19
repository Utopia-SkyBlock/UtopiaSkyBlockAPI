package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui.items.navigator;

import xyz.xenondevs.invui.gui.ScrollGui;

public class ScrollGuiAdapter<T> implements NavigableGui
{
    private final ScrollGui<T> scrollGui;

    public ScrollGuiAdapter(ScrollGui<T> scrollGui)
    {
        this.scrollGui = scrollGui;
    }

    @Override
    public void navigateForward()
    {
        int currentLine = scrollGui.getLine();
        int maxLine = scrollGui.getMaxLine();
        if(currentLine < maxLine)
        {
            scrollGui.setLine(currentLine + 1);
        }
    }

    @Override
    public void navigateBackward()
    {
        int currentLine = scrollGui.getLine();
        if(currentLine > 0)
        {
            scrollGui.setLine(currentLine - 1);
        }
    }

    @Override
    public void navigateFirst()
    {
        scrollGui.setLine(0);
    }

    @Override
    public void navigateLast()
    {
        scrollGui.setLine(scrollGui.getMaxLine());
    }

    @Override
    public int getCurrentPosition()
    {
        return scrollGui.getLine();
    }

    @Override
    public int getTotalPositions()
    {
        return scrollGui.getMaxLine() + 1;
    }
}