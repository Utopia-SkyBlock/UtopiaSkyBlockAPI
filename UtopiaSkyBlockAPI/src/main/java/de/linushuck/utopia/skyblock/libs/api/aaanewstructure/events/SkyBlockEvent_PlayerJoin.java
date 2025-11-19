package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.events;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

/**
 * 1. {@link SkyBlockEvent_AsyncPlayerPreLogin}<br>
 * 2. {@link SkyBlockEvent_PlayerSpawnLocation}<br>
 * 3. {@link SkyBlockEvent_PlayerJoin} <-- Here are we<br>
 * 4. {@link SkyBlockEvent_PlayerQuit}
 */
@Data
@AllArgsConstructor
public class SkyBlockEvent_PlayerJoin extends SkyBlockEvent
{
    private final Player player;
    private final LoadingState loadingState;
}
