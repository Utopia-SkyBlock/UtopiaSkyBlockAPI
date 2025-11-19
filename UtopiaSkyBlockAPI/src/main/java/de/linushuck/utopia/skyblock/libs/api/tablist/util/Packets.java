package de.linushuck.utopia.skyblock.libs.api.tablist.util;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Some generic-ish packet utils.
 */
public class Packets
{
    /**
     * Creates a PLAYER_INFO packet from the params.
     *
     * @param action
     * @param data
     * @return
     */
    public static PacketContainer getPacket(PlayerInfoAction action, PlayerInfoData data)
    {
        return getPacket(action, Collections.singletonList(data));
    }

    /**
     * Creates a PLAYER_INFO packet from the params.
     *
     * @param actions
     * @param data
     * @return
     */

    public static PacketContainer getPacket(Set<PlayerInfoAction> actions, List<PlayerInfoData> data)
    {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(Server.PLAYER_INFO);
        packet.getPlayerInfoActions().write(0, actions);
        packet.getPlayerInfoDataLists().write(1, data);
        return packet;
    }

    /**
     * Creates a PLAYER_INFO packet from the params.
     *
     * @param action
     * @param data
     * @return
     */

    public static PacketContainer getPacket(PlayerInfoAction action, List<PlayerInfoData> data)
    {
        return getPacket(Collections.singleton(action), data);
    }

    public static PacketContainer getRemovePacket(List<UUID> data)
    {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(Server.PLAYER_INFO_REMOVE);
        packet.getUUIDLists().write(0, data);
        return packet;
    }

    /**
     * Sends a list of ProtocolLib packets to a player.
     *
     * @param player
     * @param packets
     * @return
     */
    public static void send(Player player, List<PacketContainer> packets)
    {
        try
        {
            for(PacketContainer packet : packets)
            {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}