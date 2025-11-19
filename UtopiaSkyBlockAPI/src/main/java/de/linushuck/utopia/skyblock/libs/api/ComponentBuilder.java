package de.linushuck.utopia.skyblock.libs.api;

import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;

@NoArgsConstructor
public class ComponentBuilder
{
    private final ArrayList<Component> components = new ArrayList<>();

    public void add(Component component)
    {
        components.add(component);
    }

    public Component build()
    {
        if(components.isEmpty())
        {
            return Component.empty();
        }

        Component first = components.getFirst();
        return components.stream()
                .skip(1)
                .reduce(first, (result, component) -> result.append(Component.newline()).append(component));
    }

    public String buildAsString()
    {
        if(components.isEmpty())
        {
            return "";
        }

        Component first = components.getFirst();
        Component out = components.stream()
                .skip(1)
                .reduce(first, (result, component) -> result.append(Component.newline()).append(component));
        return ComponentHelper.miniReverse(out);
    }
}
