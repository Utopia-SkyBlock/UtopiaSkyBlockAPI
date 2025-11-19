package de.linushuck.utopia.skyblock.libs.api.enums;

public enum RedisChannel
{
    AUCTION("auction"),
    PARTY("party"),
    GLOBAL("global"),
    PlayerMover("playermover"),
    PlayerMessage("playermessage"),
    COMMANDS("commands"),
    SERVER_REGISTRATION("server-registration");

    private final String channelName;

    RedisChannel(String channelName)
    {
        this.channelName = channelName;
    }

    public String getChannelName()
    {
        return channelName;
    }
}
