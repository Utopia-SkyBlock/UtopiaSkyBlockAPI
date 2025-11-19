package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui.items.navigator;

import xyz.xenondevs.invui.gui.PagedGui;

public class PagedGuiAdapter<T> implements NavigableGui
{
    private final PagedGui<T> pagedGui;

    public PagedGuiAdapter(PagedGui<T> pagedGui)
    {
        this.pagedGui = pagedGui;
    }

    @Override
    public void navigateForward()
    {
        int currentPage = pagedGui.getPage();
        int maxPage = pagedGui.getPageCount() - 1;
        if(currentPage < maxPage)
        {
            pagedGui.setPage(currentPage + 1);
        }
    }

    @Override
    public void navigateBackward()
    {
        int currentPage = pagedGui.getPage();
        if(currentPage > 0)
        {
            pagedGui.setPage(currentPage - 1);
        }
    }

    @Override
    public void navigateFirst()
    {
        pagedGui.setPage(0);
    }

    @Override
    public void navigateLast()
    {
        int maxPage = pagedGui.getPageCount() - 1;
        if(maxPage >= 0)
        {
            pagedGui.setPage(maxPage);
        }
    }

    @Override
    public int getCurrentPosition()
    {
        return pagedGui.getPage();
    }

    @Override
    public int getTotalPositions()
    {
        return pagedGui.getPageCount();
    }
}