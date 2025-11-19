package de.linushuck.utopia.skyblock.libs.api.item.nbthandler.types;

import de.linushuck.utopia.skyblock.libs.api.enums.LoreSection;
import de.linushuck.utopia.skyblock.libs.api.item.nbthandler.Convertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomSection extends Convertable<LoreSection>
{
    private LoreSection value;

    @Override
    public String toString()
    {
        return value.name();
    }

    @Override
    public CustomSection fromString(String string)
    {
        return new CustomSection(LoreSection.valueOf(string));
    }
}
