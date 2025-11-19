package de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types;

import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.Convertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomString extends Convertable<String>
{
    private String value;

    @Override
    public String toString()
    {
        return value;
    }

    @Override
    public CustomString fromString(String string)
    {
        return new CustomString(string);
    }
}

