package de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types;

import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.Convertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomBoolean extends Convertable<Boolean>
{
    private Boolean value;

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public CustomBoolean fromString(String string)
    {
        return new CustomBoolean(Boolean.parseBoolean(string));
    }
}
