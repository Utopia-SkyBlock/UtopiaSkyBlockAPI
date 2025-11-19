package de.linushuck.utopia.skyblock.libs.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecentEnumList<T extends Enum<T>>
{
    private final List<T> enumList;

    public RecentEnumList(Class<T> enumType)
    {
        this.enumList = new ArrayList<>(Arrays.asList(enumType.getEnumConstants()));
    }

    public void add(T enumValue)
    {
        if(!enumList.contains(enumValue))
        {
            enumList.add(enumValue);
        }
    }

    public T getAtIndex(int index)
    {
        if(index < 0 || index >= enumList.size())
        {
            return enumList.getLast(); // return the last possible index if out of bounds
        }
        return enumList.get(index);
    }

    public void toTop(T enumValue)
    {
        enumList.remove(enumValue);
        enumList.addFirst(enumValue);
    }
}