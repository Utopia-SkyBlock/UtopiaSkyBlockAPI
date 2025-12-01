package de.linushuck.utopia.skyblock.libs.essentials;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseComponentHelper {
    private static final int MAX_CACHE_ENTRIES = 8192;

    private static Map<Long, Component> cache = Collections.synchronizedMap(
            new LinkedHashMap<>(256, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Long, Component> eldest) {
                    return size() > MAX_CACHE_ENTRIES;
                }
            });

    private static long getUniqueHash(String message, TagResolver... placeholders) {
        int messageHash = (message != null) ? message.hashCode() : 0;
        int placeholdersHash = (placeholders != null && placeholders.length > 0) ? Arrays.hashCode(placeholders) : 0;

        long combined = (((long) messageHash) << 32) ^ (placeholdersHash & 0xFFFFFFFFL);

        // apply MurmurHash3 64-bit finalizer to mix bits thoroughly
        long k = combined;
        k ^= k >>> 33;
        k *= 0xff51afd7ed558ccdL;
        k ^= k >>> 33;
        k *= 0xc4ceb9fe1a85ec53L;
        k ^= k >>> 33;
        return k;
    }

    public static Component mini(String message, TagResolver... placeholders)
    {
        long hash = getUniqueHash(message, placeholders);
        final String originalMessage = message;
        return cache.computeIfAbsent(hash, key -> {
            String msg = originalMessage;
            if (msg == null) {
                msg = "";
            }
            if (!msg.endsWith("<reset>")) {
                msg = "<!i><gray>" + msg + "<reset>";
            } else {
                msg = "<!i><gray>" + msg;
            }
            return MiniMessage.miniMessage().deserialize(msg, placeholders);
        });
    }

    public static String miniReverse(Component component)
    {
        return MiniMessage.miniMessage().serialize(component);
    }
}
