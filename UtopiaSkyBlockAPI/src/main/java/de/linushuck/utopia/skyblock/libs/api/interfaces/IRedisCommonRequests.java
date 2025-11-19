package de.linushuck.utopia.skyblock.libs.api.interfaces;

import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockServerType;

import java.util.UUID;

public interface IRedisCommonRequests
{
    SkyBlockServerType getServerNow(UUID profileUUID);

    SkyBlockServerType getServerPrevious(UUID profileUUID);

    void setServerNow(UUID profileUUID, SkyBlockServerType serverNow);

    void setBoth(UUID profileUUID, SkyBlockServerType serverNow);
}
