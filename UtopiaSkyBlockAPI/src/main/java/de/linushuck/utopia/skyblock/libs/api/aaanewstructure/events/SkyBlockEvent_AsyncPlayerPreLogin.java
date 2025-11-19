package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.events;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * 1. {@link SkyBlockEvent_AsyncPlayerPreLogin} <-- Here are we<br>
 * 2. {@link SkyBlockEvent_PlayerSpawnLocation}<br>
 * 3. {@link SkyBlockEvent_PlayerJoin}<br>
 * 4. {@link SkyBlockEvent_PlayerQuit}
 */
@Data
@AllArgsConstructor
public class SkyBlockEvent_AsyncPlayerPreLogin extends SkyBlockEvent
{
    private final UUID playerUUID;
    private final LoadingState loadingState;
}
