package de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types;

import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.Convertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDouble extends Convertable<Double>
{
    private Double value;

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public CustomDouble fromString(String string)
    {
        return new CustomDouble(Double.parseDouble(string));
    }
}
