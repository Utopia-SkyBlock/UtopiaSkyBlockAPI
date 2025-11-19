package de.linushuck.utopia.skyblock.libs.api.eventsystem;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkyBlockEvent
{
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    @Setter
    private boolean isValid = false;
    private final ArrayList<SkyBlockEventExecutor> executors = new ArrayList<>();

    public SkyBlockEvent(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public SkyBlockEvent()
    {
        this.name = this.getClass().getSimpleName();
        this.description = "";
    }

    public List<SkyBlockEventExecutor> getExecutors()
    {
        return Collections.unmodifiableList(executors);
    }

    public void addExecutor(SkyBlockEventExecutor executor)
    {
        executors.add(executor);
    }

    public void clearExecutors()
    {
        executors.clear();
    }
}