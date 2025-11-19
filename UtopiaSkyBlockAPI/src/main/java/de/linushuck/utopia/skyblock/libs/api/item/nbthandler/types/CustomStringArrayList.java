package de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types;

import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.Convertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomStringArrayList extends Convertable<List<String>>
{
    private List<String> value;


    @Override
    public String toString()
    {
        if(value == null || value.isEmpty())
        {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(String s : value)
        {
            builder.append(s).append("|,AL,|");
        }
        builder.setLength(builder.length() - 6);
        return builder.toString();
    }

    @Override
    public Convertable<List<String>> fromString(String string)
    {
        if(string == null || string.isEmpty())
        {
            return new CustomStringArrayList(new ArrayList<>());
        }

        // Split with negative limit to preserve empty strings
        String[] parts = string.split("\\|,AL,\\|", -1);
        return new CustomStringArrayList(new ArrayList<>(Arrays.asList(parts)));
    }
}
