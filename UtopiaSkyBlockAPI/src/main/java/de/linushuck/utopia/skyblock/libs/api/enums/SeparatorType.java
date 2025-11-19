package de.linushuck.utopia.skyblock.libs.api.enums;

import lombok.Getter;

public enum SeparatorType
{
    BIG("▬"),
    MEDIUM("_"),
    SMALL("-");
    @Getter
    private final String separator;

    SeparatorType(String separator)
    {
        this.separator = separator;
    }
}
