package de.linushuck.utopia.skyblock.libs.api.eventsystem.events;

import de.linushuck.utopia.skyblock.libs.api.eventsystem.FinalEventMethod;
import de.linushuck.utopia.skyblock.libs.api.eventsystem.SkyBlockEvent;
import de.linushuck.utopia.skyblock.libs.api.skyblock.enums.SkyBlockProfileType;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Called when a SkyBlock profile save operation starts.
 * Shows a title to the player by default.
 */
@Getter
@Setter
public class ProfileSaveStartEvent extends SkyBlockEvent implements FinalEventMethod
{
    private static final String MESSAGE = "<green>Saving</green> <blue><level></blue>";

    private final Player player;
    private final UUID profileUUID;
    private final SkyBlockProfileType profileType;
    private final boolean async;

    public ProfileSaveStartEvent(Player player, UUID profileUUID, SkyBlockProfileType profileType, boolean async)
    {
        super("ProfileSaveStartEvent", "Called when a profile save operation starts");
        this.player = player;
        this.profileUUID = profileUUID;
        this.profileType = profileType;
        this.async = async;
    }

    @Override
    public void onEvent()
    {
        // Default behavior: show title to player
        Title title = Title.title(MiniMessage.miniMessage()
                .deserialize(MESSAGE.replace("<level>", profileType.name())), Component.empty());
        player.showTitle(title);
    }
}