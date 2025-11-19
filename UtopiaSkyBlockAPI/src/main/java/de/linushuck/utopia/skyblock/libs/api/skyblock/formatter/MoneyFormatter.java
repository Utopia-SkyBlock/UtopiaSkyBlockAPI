package de.linushuck.utopia.skyblock.libs.api.skyblock.formatter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MoneyFormatter
{
    private final DecimalFormat MONEY_FORMAT;

    {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        MONEY_FORMAT = new DecimalFormat("#,##0.00", symbols);
    }

    public String format(double amount)
    {
        return MONEY_FORMAT.format(amount);
    }
}