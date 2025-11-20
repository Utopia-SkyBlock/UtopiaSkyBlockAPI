package de.linushuck.utopia.skyblock.libs.api.interfaces;

import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockServerType;

import java.util.UUID;

public interface IRedisCommonRequests
{
    UUID getServerNow(UUID profileUUID);

    UUID getServerPrevious(UUID profileUUID);

    void setServerNow(UUID profileUUID, UUID serverNow);

    void setBoth(UUID profileUUID, UUID serverNow);
}
