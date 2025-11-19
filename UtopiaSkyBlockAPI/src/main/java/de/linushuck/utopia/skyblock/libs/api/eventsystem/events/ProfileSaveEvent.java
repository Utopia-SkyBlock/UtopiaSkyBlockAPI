package de.linushuck.utopia.skyblock.libs.api.eventsystem.events;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockProfileType;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Called when a SkyBlock profile is being saved.
 */
@Getter
public class ProfileSaveEvent extends SkyBlockEvent
{
    private final Player player;
    private final UUID profileUUID;
    private final SkyBlockProfileType profileType;
    private final boolean async;

    public ProfileSaveEvent(Player player, UUID profileUUID, SkyBlockProfileType profileType, boolean async)
    {
        super("ProfileSaveEvent", "Called when a SkyBlock profile is saved");
        this.player = player;
        this.profileUUID = profileUUID;
        this.profileType = profileType;
        this.async = async;
    }
}