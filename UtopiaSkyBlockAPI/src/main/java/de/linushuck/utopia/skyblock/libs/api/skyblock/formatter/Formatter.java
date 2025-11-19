package de.linushuck.utopia.skyblock.libs.api.skyblock.formatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Formatter
{
    public static final MoneyFormatter Money = new MoneyFormatter();
    public static final TimeFormatter Time = new TimeFormatter();
    public static final RomanFormatter Roman = new RomanFormatter();
}