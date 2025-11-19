package de.linushuck.utopia.skyblock.libs.api;

import de.linushuck.utopia.skyblock.libs.essentials.Logger;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public enum SoundDesign
{

    ActionAllow(List.of("block.note_block.pling"), Sound.Source.BLOCK, 4.047619f, 4.047619f, 8f, 8f),
    ActionDeny(List.of("entity.enderman.teleport"), Sound.Source.PLAYER, 0f, 0f, 8f, 8f),

    SkyBlock_SkillProgress(List.of("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1.5f, 2.7f, 0.5f, 0.5f),
    SkyBlock_Unlock(List.of("entity.player.levelup"), Sound.Source.BLOCK, 2f, 2f, 1f, 1f),
    SkyBlock_LevelUp(List.of("entity.player.levelup"), Sound.Source.BLOCK, 1f, 2f, 1f, 1f),
    SkyBlock_ShopInteraction(List.of("block.note_block.pling"), Sound.Source.BLOCK, 4.047619f, 4.047619f, 8f, 8f),
    SkyBlock_LaunchPadStart1(List.of("entity.generic.explode"), Sound.Source.BLOCK, 1.5f, 2.7f, 0.5f, 0.5f),
    SkyBlock_LaunchPadStart2(List.of("entity.firework_rocket.launch"), Sound.Source.AMBIENT, 1.5f, 2.7f, 0.4f, 0.4f),
    SkyBlock_LaunchPadStart3(List.of("entity.bat.takeoff"), Sound.Source.PLAYER, 0.5f, 1f, 0.2f, 0.2f),
    SkyBlock_ActionTeleport(List.of("entity.enderman.teleport"), Sound.Source.PLAYER, 1f, 1f, 1f, 1f),
    SkyBlock_SculptorsAxe(List.of("item.axe.strip"), Sound.Source.PLAYER, 1f, 1f, 1f, 1f),


    //New sounds
    SkyBlock_PickupItem(List.of("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1.4920635f, 1.4920635f, 1f, 1f),
    SkyBlock_GrapplingHook(List.of("item.crossbow.loading_start"), Sound.Source.NEUTRAL, 0.3809524f, 0.3809524f, 0.5f, 0.5f),
    SkyBlock_Claim(List.of("block.note_block.pling"), Sound.Source.BLOCK, 4.047619f, 4.047619f, 8f, 8f),
    SkyBlock_OpenNetherStarMenu(List.of("ui.button.click"), Sound.Source.BLOCK, 1f, 1f, 0.5f, 0.5f),
    SkyBlock_OpenTravelMenu(List.of("entity.illusioner.mirror_move"), Sound.Source.MASTER, 1f, 1f, 0.8f, 0.8f),
    SkyBlock_OpenCraftingBench(List.of("block.wood.hit"), Sound.Source.BLOCK, 1.2380953f, 1.2380953f, 1f, 1f),
    SkyBlock_OpenStorage(List.of("block.chest.open"), Sound.Source.BLOCK, 0.84126985f, 0.84126985f, 1f, 1f),
    SkyBlock_OpenCollectionPage(List.of("entity.item_frame.add_item"), Sound.Source.PLAYER, 1f, 1f, 0.5f, 0.5f),
    SkyBlock_OpenRecipePage(List.of("item.book.page_turn"), Sound.Source.MASTER, 1f, 1f, 1f, 1f),
    SkyBlock_AspectOfTheDragons(List.of("entity.ender_dragon.growl"), Sound.Source.HOSTILE, 1f, 1f, 1f, 1f),
    SkyBlock_AspectOfTheVoid(List.of("entity.enderman.teleport"), Sound.Source.HOSTILE, 1f, 1f, 1f, 1f),
    SkyBlock_KillSomething1(List.of("entity.experience_orb.pickup"), Sound.Source.PLAYER, 2.1269841f, 2.1269841f, 0.5f, 0.5f),
    SkyBlock_KillSomething2(List.of("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1.4920635f, 1.4920635f, 1f, 1f),
    SkyBlock_PickupLoot(List.of("block.lava.pop"), Sound.Source.PLAYER, 1f, 1f, 1f, 1f),


    SkyBlock_RareDrop1(List.of("block.note_block.pling"), Sound.Source.RECORD, 0.5873016f, 0.5873016f, 0.8f, 0.8f),
    SkyBlock_RareDrop2(List.of("entity.experience_orb.pickup"), Sound.Source.RECORD, 0.6984127f, 0.6984127f, 0.2f, 0.2f),
    SkyBlock_RareDrop3(List.of("block.note_block.pling"), Sound.Source.RECORD, 0.5873016f, 0.5873016f, 0.4f, 0.4f),
    SkyBlock_RareDrop4(List.of("block.note_block.pling"), Sound.Source.RECORD, 0.7936508f, 0.7936508f, 0.8f, 0.8f),
    SkyBlock_RareDrop5(List.of("entity.experience_orb.pickup"), Sound.Source.RECORD, 0.93650794f, 0.93650794f, 0.2f, 0.2f),
    SkyBlock_RareDrop6(List.of("block.note_block.pling"), Sound.Source.RECORD, 0.7936508f, 0.7936508f, 0.4f, 0.4f),
    SkyBlock_RareDrop7(List.of("block.note_block.pling"), Sound.Source.RECORD, 1.0476191f, 1.0476191f, 0.8f, 0.8f),
    SkyBlock_RareDrop8(List.of("entity.experience_orb.pickup"), Sound.Source.RECORD, 1.2539682f, 1.2539682f, 0.2f, 0.2f),
    SkyBlock_RareDrop9(List.of("block.note_block.pling"), Sound.Source.RECORD, 1.0476191f, 1.0476191f, 0.4f, 0.4f),
    SkyBlock_RareDrop10(List.of("block.note_block.pling"), Sound.Source.RECORD, 1.1746032f, 1.1746032f, 0.8f, 0.8f),
    SkyBlock_RareDrop11(List.of("entity.experience_orb.pickup"), Sound.Source.RECORD, 1.4126984f, 1.4126984f, 0.2f, 0.2f),
    SkyBlock_RareDrop12(List.of("block.note_block.pling"), Sound.Source.RECORD, 1.1746032f, 1.1746032f, 0.4f, 0.4f),
    SkyBlock_EndSpawnEnderMite(List.of("entity.chicken.egg"), Sound.Source.PLAYER, 0.74603176f, 0.74603176f, 0.5f, 0.5f),

    ;
    private static final Random random = new Random();
    @Setter
    private static JavaPlugin fallBackPlugin;
    private final List<Key> keys;
    private final Sound.Source channel;
    private final float pitchMin;
    private final float pitchMax;
    private final float volumeMin;
    private final float volumeMax;

    SoundDesign(List<String> keys, Sound.Source channel, float pitchMin, float pitchMax, float volumeMin, float volumeMax)
    {
        this.keys = keys.stream().map(Key::key).toList();
        this.channel = channel;
        this.pitchMin = pitchMin;
        this.pitchMax = pitchMax;
        this.volumeMin = volumeMin;
        this.volumeMax = volumeMax;
    }

    public static void playSounds(Player player, Sound.Emitter emitter, int tickPeriod, SoundDesign... sounds)
    {
        AtomicInteger index = new AtomicInteger(0);
        Bukkit.getScheduler().runTaskTimer(fallBackPlugin, (task) ->
        {
            if(index.get() >= sounds.length)
            {
                task.cancel();
                return;
            }
            sounds[index.getAndIncrement()].playSound(player, emitter);
        }, 0, tickPeriod);
    }

    private void playSound(Player player, Sound.Emitter emitter)
    {
        if(player == null)
        {
            Logger.error(fallBackPlugin, "Player is null while playing sound");
            return;
        }
        if(keys.isEmpty())
        {
            return;
        }
        float volume = volumeMin + random.nextFloat() * (volumeMax - volumeMin);
        float pitch = pitchMin + random.nextFloat() * (pitchMax - pitchMin);
        int randomIndex = random.nextInt(keys.size());
        Sound sound = Sound.sound(keys.get(randomIndex), channel, volume, pitch);
        player.playSound(sound, emitter);
    }

    public void playSound(Player player)
    {
        playSound(player, Sound.Emitter.self());
    }
}

