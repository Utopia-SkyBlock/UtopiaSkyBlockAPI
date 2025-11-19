package de.linushuck.utopia.skyblock.libs.api.interfaces;

import de.linushuck.utopia.skyblock.libs.api.enums.RedisChannel;
import redis.clients.jedis.JedisPool;

import java.util.function.Consumer;

public interface IRedisConnection
{
    JedisPool getJedisPool();

    void registerConsumer(RedisChannel channel, Consumer<String> consumer);

    void publish(RedisChannel channel, String message);

    String getPrefix();
}
