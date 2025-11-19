package de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types;

import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.Convertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomInt extends Convertable<Integer>
{
    private Integer value;

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public CustomInt fromString(String string)
    {
        return new CustomInt(Integer.parseInt(string));
    }
}
