package de.linushuck.utopia.skyblock.libs.api.skyblock.enums;

import lombok.Getter;

/**
 * Enumeration of profile types in the system.
 * Each type has its own current version number.
 */
public enum SkyBlockProfileType
{
    PLAYER(2),
    CLUSTER_PROFILE(1),
    INDIVIDUAL_PROFILE(2),
    PRIVATE_ISLAND(-1),
    ;

    @Getter
    private final int currentVersion;

    SkyBlockProfileType(int currentVersion)
    {
        this.currentVersion = currentVersion;
    }
}