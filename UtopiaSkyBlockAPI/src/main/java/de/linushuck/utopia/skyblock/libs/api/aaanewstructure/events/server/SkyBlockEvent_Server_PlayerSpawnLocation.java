package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.events.server;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockServerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 1. {@link SkyBlockEvent_Server_AsyncPlayerPreLogin}<br>
 * 2. {@link SkyBlockEvent_Server_PlayerSpawnLocation} <-- Here are we<br>
 * 3. {@link SkyBlockEvent_Server_PlayerJoin}<br>
 * 4. {@link SkyBlockEvent_Server_PlayerQuit}
 */
@Data
@AllArgsConstructor
public class SkyBlockEvent_Server_PlayerSpawnLocation extends SkyBlockEvent
{
    private final Player player;
    private final SkyBlockServerType server;
    private Location spawnLocation;
}
