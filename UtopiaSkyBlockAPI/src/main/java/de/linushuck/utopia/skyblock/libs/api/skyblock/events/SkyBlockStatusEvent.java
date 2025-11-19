package de.linushuck.utopia.skyblock.libs.api.skyblock.events;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockServerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkyBlockStatusEvent extends SkyBlockEvent
{

    private final String what;
    private final SkyBlockServerStatus oldSkyBlockServerStatus;
    private final SkyBlockServerStatus newstatus;
}
