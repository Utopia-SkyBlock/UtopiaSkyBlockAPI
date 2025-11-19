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
 * Called when a SkyBlock profile save operation completes.
 * Shows a title to the player by default.
 */
@Getter
@Setter
public class ProfileSaveFinishEvent extends SkyBlockEvent implements FinalEventMethod
{
    private static final String MESSAGE = "<green>Saved</green> <blue><level></blue>";

    private final Player player;
    private final UUID profileUUID;
    private final SkyBlockProfileType profileType;
    private final boolean async;

    public ProfileSaveFinishEvent(Player player, UUID profileUUID, SkyBlockProfileType profileType, boolean async)
    {
        super("ProfileSaveFinishEvent", "Called when a profile save operation completes");
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