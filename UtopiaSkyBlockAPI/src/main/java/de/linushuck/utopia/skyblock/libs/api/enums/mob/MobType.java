package de.linushuck.utopia.skyblock.libs.api.enums.mob;

import de.linushuck.utopia.skyblock.libs.api.aaanewstructure.SkyBlockItemBuilder;
import de.linushuck.utopia.skyblock.libs.api.item.HeadCreator;
import lombok.Getter;
import org.bukkit.entity.EntityType;

public enum MobType
{
    //vanilla

    //passive
    CHICKEN(BaseMobType.FarmersYard_Chicken, "Chicken", 1, 20, 0, 1, 1, 1, EntityType.CHICKEN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FkM2RkMDA4M2ZhYTY5YTA2MmY5YWQ4MTQxOGY1YTU5NjE4MGJmMTU5MmU0YjhkMTMwM2IyMzBiNjRiYzc5ZSJ9fX0="))),
    COW(BaseMobType.FarmersYard_Cow, "Cow", 1, 50, 0, 1, 1, 1, EntityType.COW, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGM0YjVmNmQ3NTEyNjM4MGY1MjBhNjdjYTU3YmM5YTU2YWExMWRiOGFmZTdlNWRjYjJhNTJkZmNmZWFlMDc4NSJ9fX0="))),
    PIG(BaseMobType.FarmersYard_Pig, "Pig", 1, 20, 0, 1, 1, 1, EntityType.PIG, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWIxNzYwZTM3NzhmODA4NzA0NmI4NmJlYzZhMGE4M2E1Njc2MjVmMzBmMGQ2YmNlODY2ZDRiZWQ5NWRiYTZjMSJ9fX0="))),
    RABBIT(BaseMobType.SandyCropland_Rabbit, "Rabbit", 1, 130, 0, 1, 1, 1, EntityType.RABBIT, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2QyNzE5MjcxYzcxNDk4MDIyNmZmMWJlZjRjYmIxZDU0NDExY2UzYTkxNGQ3ZGY3MzFiY2M3ZjYxZTk0ZTVjIn19fQ=="))),
    SHEEP(BaseMobType.ShroomscapeDunes_Sheep, "Sheep", 1, 20, 0, 1, 1, 1, EntityType.SHEEP, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODRlNWNkYjBlZGIzNjJjYjQ1NDU4NmQxZmQwZWJlOTcxNDIzZjAxNWIwYjFiZmM5NWY4ZDVhZjhhZmU3ZTgxMCJ9fX0="))),
    MOOSHROOM_COW(BaseMobType.ShroomscapeDunes_MushroomCow, "Mooshroom Cow", 1, 20, 0, 1, 1, 1, EntityType.MOOSHROOM, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU0M2I3MmRlZjFiMjQ3Njg1YWQ0ZDAyN2RmODZjOTYzMmU3ZGFjMTQzYTk1NTJlYzg5YzgwMDM1YzNiYTRhZSJ9fX0="))),

    //angry
    SNEAKY_CREEPER_3(BaseMobType.RockyVeins_Sneaky_Creeper, "Sneaky Creeper", true, 3, 120, 80, 1, 3, 3, EntityType.CREEPER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM4NzBjODRiZjIxNTVjOTljYmE4ODk1ODhjYTkxZWY3OGU1OTBlNWVjNThiMmJmNzllODdhYTZkMTQ3M2RkMSJ9fX0="))), //lvl 3 health 120
    LAPIS_ZOMBIE_7(BaseMobType.RockyVeins_Lapis_Zombie, "Lapis Zombie", true, 7, 200, 50, 20, 5, 20, EntityType.ZOMBIE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMyMzJkMDY4M2JhMWUzNjI1OTNmYTFhOTdkNzQ0YzI5MGIxNjU1ZDcyYjIyNzc5ODhhNmM4NWE5Mzg1NDI1NSJ9fX0="))), //lvl 7 health 200 //TODO chnage
    REDSTONE_PIGMAN_10(BaseMobType.RockyVeins_Redstone_Pigman, "Redstone Pigman", true, 10, 250, 75, 20, 12, 25, EntityType.ZOMBIFIED_PIGLIN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWVkODBiZjk5ZGZiMDc1MTkyYjAyYTVkZDZiZjkxZWRkZDRjYjFmNDgxODkyMzIxMjExZmUyMTQyZDlkZjIyYyJ9fX0="))), //lvl 10 health 250
    EMERALD_SLIME_5(BaseMobType.RockyVeins_Emerald_Slime, "Emerald Slime", true, 5, 80, 70, 12, 5, 20, EntityType.SLIME, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQxYTMxOTdkNWQzYjU0YzNjYmI3ZDVmNzUzMDU5M2QxNDljN2ZlYzc1M2E1YWQ4ZjEwMjc2ZDI2NDM0MjcwNSJ9fX0="))), //lvl 5 health 80
    EMERALD_SLIME_10(BaseMobType.RockyVeins_Emerald_Slime, "Emerald Slime", true, 10, 150, 100, 15, 8, 30, EntityType.SLIME, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQxYTMxOTdkNWQzYjU0YzNjYmI3ZDVmNzUzMDU5M2QxNDljN2ZlYzc1M2E1YWQ4ZjEwMjc2ZDI2NDM0MjcwNSJ9fX0="))), //lvl 10 health 80
    EMERALD_SLIME_15(BaseMobType.RockyVeins_Emerald_Slime, "Emerald Slime", true, 15, 250, 150, 20, 12, 50, EntityType.SLIME, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQxYTMxOTdkNWQzYjU0YzNjYmI3ZDVmNzUzMDU5M2QxNDljN2ZlYzc1M2E1YWQ4ZjEwMjc2ZDI2NDM0MjcwNSJ9fX0="))), //lvl 15 health 80
    MINER_ZOMBIE_15(BaseMobType.RockyVeins_Miner_Zombie, "Miner Zombie", true, 15, 250, 200, 20, 12, 30, EntityType.ZOMBIE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgzYWFhZWUyMjg2OGNhZmRhYTFmNmY0YTBlNTZiMGZkYjY0Y2QwYWVhYWJkNmU4MzgxOGMzMTJlYmU2NjQzNyJ9fX0="))), //lvl 15 health 250
    MINER_ZOMBIE_20(BaseMobType.RockyVeins_Miner_Zombie, "Miner Zombie", true, 20, 300, 250, 24, 15, 40, EntityType.ZOMBIE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMyMzJkMDY4M2JhMWUzNjI1OTNmYTFhOTdkNzQ0YzI5MGIxNjU1ZDcyYjIyNzc5ODhhNmM4NWE5Mzg1NDI1NSJ9fX0="))), //lvl 20 health 250
    MINER_SKELETON_15(BaseMobType.RockyVeins_Miner_Skeleton, "Miner Skeleton", true, 15, 250, 150, 20, 12, 30, EntityType.SKELETON, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg3ZWRkYzVmYTliNWIyOWM4ZjU4ODk4YmMxYjM5MzY3NmZkNWMxNzM4MDViZmQ1MmNiZWQwYzI5YmEzODY3NCJ9fX0="))), //lvl 15 health 250
    MINER_SKELETON_20(BaseMobType.RockyVeins_Miner_Skeleton, "Miner Skeleton", true, 20, 300, 200, 24, 15, 40, EntityType.SKELETON, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM2YmJjNDIxNWNlYTFiNmE0ODRlODkzYjExNmU3MzQ1OWVmMzZiZmZjNjIyNzQxZTU3N2U5NDkzYTQxZTZlIn19fQ=="))), //lvl 20 health 250

    GRAVEYARD_ZOMBIE_1(BaseMobType.Town_Graveyard_Zombie, "Graveyard Zombie", true, 1, 100, 20, 6, 1, 1, EntityType.ZOMBIE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgzYWFhZWUyMjg2OGNhZmRhYTFmNmY0YTBlNTZiMGZkYjY0Y2QwYWVhYWJkNmU4MzgxOGMzMTJlYmU2NjQzNyJ9fX0"))), //lvl 1 health 100
    ZOMBIE_VILLAGER_1(BaseMobType.Town_Zombie_Villager, "Zombie Villager", true, 1, 120, 24, 7, 1, 2, EntityType.ZOMBIE_VILLAGER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM3NTA1ZjIyNGQ1MTY0YTExN2Q4YzY5ZjAxNWY5OWVmZjQzNDQ3MWM4YTJkZjkwNzA5NmM0MjQyYzM1MjRlOCJ9fX0="))), //lvl 1 health 120

    DASHER_SPIDER_4(BaseMobType.WebbedCliff_Dasher_Spider, "Dasher Spider", true, 4, 170, 0, 5, 4, 8, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ=="))), //lvl 4 health 170
    DASHER_SPIDER_42(BaseMobType.WebbedCliff_Dasher_Spider, "Dasher Spider", true, 42, 900, 0, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ=="))), //lvl 42 health 900
    DASHER_SPIDER_45(BaseMobType.WebbedCliff_Dasher_Spider, "Dasher Spider", true, 45, 1100, 0, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ=="))), //lvl 45 health 1100
    DASHER_SPIDER_50(BaseMobType.WebbedCliff_Dasher_Spider, "Dasher Spider", true, 50, 1400, 0, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ=="))), //lvl 50 health 1400
    VORACIOUS_SPIDER_10(BaseMobType.WebbedCliff_Voracious_Spider, "Voracious Spider", true, 10, 300, 0, 5, 4, 8, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgxYjVjZjNhNjViZjljYWI0YmNhNTU3ODE3MjFhODZkNTY3ZDllMTA2NTUxNjQ3ZjM1YjcxNWVkZjAwYTEzZiJ9fX0="))), //lvl 10 health 300
    VORACIOUS_SPIDER_42(BaseMobType.WebbedCliff_Voracious_Spider, "Voracious Spider", true, 42, 750, 0, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgxYjVjZjNhNjViZjljYWI0YmNhNTU3ODE3MjFhODZkNTY3ZDllMTA2NTUxNjQ3ZjM1YjcxNWVkZjAwYTEzZiJ9fX0="))), //lvl 42 health 750
    VORACIOUS_SPIDER_45(BaseMobType.WebbedCliff_Voracious_Spider, "Voracious Spider", true, 45, 1150, 0, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgxYjVjZjNhNjViZjljYWI0YmNhNTU3ODE3MjFhODZkNTY3ZDllMTA2NTUxNjQ3ZjM1YjcxNWVkZjAwYTEzZiJ9fX0="))), //lvl 45 health 1150
    VORACIOUS_SPIDER_50(BaseMobType.WebbedCliff_Voracious_Spider, "Voracious Spider", true, 50, 1450, 0, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgxYjVjZjNhNjViZjljYWI0YmNhNTU3ODE3MjFhODZkNTY3ZDllMTA2NTUxNjQ3ZjM1YjcxNWVkZjAwYTEzZiJ9fX0="))), //lvl 50 health 1450
    SPLITTER_SPIDER_2(BaseMobType.WebbedCliff_Splitter_Spider, "Splitter Spider", true, 2, 180, 30, 5, 4, 8, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFjZjY5ZmM3YWY1NDk3YTE3NDE4OTFkMWU1YmYzMmI5NmFlMGQ2YzBiYmQzYzE0NzU4ZWE0NGEwM2M1NzI4MyIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 2 health 180
    SPLITTER_SPIDER_42(BaseMobType.WebbedCliff_Splitter_Spider, "Splitter Spider", true, 42, 4500, 550, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFjZjY5ZmM3YWY1NDk3YTE3NDE4OTFkMWU1YmYzMmI5NmFlMGQ2YzBiYmQzYzE0NzU4ZWE0NGEwM2M1NzI4MyIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 42 health 180
    SPLITTER_SPIDER_45(BaseMobType.WebbedCliff_Splitter_Spider, "Splitter Spider", true, 45, 6000, 650, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFjZjY5ZmM3YWY1NDk3YTE3NDE4OTFkMWU1YmYzMmI5NmFlMGQ2YzBiYmQzYzE0NzU4ZWE0NGEwM2M1NzI4MyIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 45 health 180
    SPLITTER_SPIDER_50(BaseMobType.WebbedCliff_Splitter_Spider, "Splitter Spider", true, 50, 9000, 850, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFjZjY5ZmM3YWY1NDk3YTE3NDE4OTFkMWU1YmYzMmI5NmFlMGQ2YzBiYmQzYzE0NzU4ZWE0NGEwM2M1NzI4MyIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 50 health 180
    WEAVER_SPIDER_3(BaseMobType.WebbedCliff_Weaver_Spider, "Weaver Spider", true, 3, 160, 10, 5, 4, 8, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTIxNDM4ZjY0NmRjMDQ1MTU5NjdlODE5NWNjYzNkMzFlMjNiMDJmOWFhMGFjOTE0ZWRjMjgyMmY5ODM5NGI4NiIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 3 health 160
    WEAVER_SPIDER_42(BaseMobType.WebbedCliff_Weaver_Spider, "Weaver Spider", true, 42, 800, 50, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTIxNDM4ZjY0NmRjMDQ1MTU5NjdlODE5NWNjYzNkMzFlMjNiMDJmOWFhMGFjOTE0ZWRjMjgyMmY5ODM5NGI4NiIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 42 health 800
    WEAVER_SPIDER_45(BaseMobType.WebbedCliff_Weaver_Spider, "Weaver Spider", true, 45, 1200, 80, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTIxNDM4ZjY0NmRjMDQ1MTU5NjdlODE5NWNjYzNkMzFlMjNiMDJmOWFhMGFjOTE0ZWRjMjgyMmY5ODM5NGI4NiIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 45 health 1200
    WEAVER_SPIDER_50(BaseMobType.WebbedCliff_Weaver_Spider, "Weaver Spider", true, 50, 1500, 120, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTIxNDM4ZjY0NmRjMDQ1MTU5NjdlODE5NWNjYzNkMzFlMjNiMDJmOWFhMGFjOTE0ZWRjMjgyMmY5ODM5NGI4NiIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19"))), //lvl 50 health 1500
    SPIDER_JOCKEY_3(BaseMobType.WebbedCliff_Spider_Jockey, "Spider Jockey", true, 3, 220, 10, 5, 4, 8, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA5MzkzNzNjYWZlNGIxZjUzOTdhYWZkMDlmM2JiMTY2M2U3YjYyOWE0MWE3NWZiZGMxODYwYjZiZjhiNDc1ZiJ9fX0="))), //lvl 3 health 220
    SPIDER_JOCKEY_42(BaseMobType.WebbedCliff_Spider_Jockey, "Spider Jockey", true, 3, 1000, 50, 15, 8, 16, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA5MzkzNzNjYWZlNGIxZjUzOTdhYWZkMDlmM2JiMTY2M2U3YjYyOWE0MWE3NWZiZGMxODYwYjZiZjhiNDc1ZiJ9fX0="))), //lvl 3 health 1000
    JOCKEY_SKELETON_3(BaseMobType.WebbedCliff_Jockey_Skeleton, "Jockey Skeleton", true, 3, 250, 10, 5, 4, 8, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg3ZWRkYzVmYTliNWIyOWM4ZjU4ODk4YmMxYjM5MzY3NmZkNWMxNzM4MDViZmQ1MmNiZWQwYzI5YmEzODY3NCJ9fX0="))), //lvl 3 health 250
    JOCKEY_SKELETON_42(BaseMobType.WebbedCliff_Jockey_Skeleton, "Jockey Skeleton", true, 42, 1000, 50, 1, 1, 1, EntityType.SPIDER, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg3ZWRkYzVmYTliNWIyOWM4ZjU4ODk4YmMxYjM5MzY3NmZkNWMxNzM4MDViZmQ1MmNiZWQwYzI5YmEzODY3NCJ9fX0="))), //lvl 42 health 1000
    GRAVEL_SKELETON_2(BaseMobType.WebbedCliff_Gravel_Skeleton, "Gravel Skeleton", true, 2, 100, 10, 5, 4, 8, EntityType.SKELETON, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg3ZWRkYzVmYTliNWIyOWM4ZjU4ODk4YmMxYjM5MzY3NmZkNWMxNzM4MDViZmQ1MmNiZWQwYzI5YmEzODY3NCJ9fX0="))), //lvl 2 health 100
    SILVERFISH_3(BaseMobType.WebbedCliff_Silverfish, "Silverfish", true, 3, 50, 40, 5, 4, 8, EntityType.SILVERFISH, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE5MWRhYjgzOTFhZjVmZGE1NGFjZDJjMGIxOGZiZDgxOWI4NjVlMWE4ZjFkNjIzODEzZmE3NjFlOTI0NTQwIn19fQ=="))), //lvl 3 health 50
    SILVERFISH_42(BaseMobType.WebbedCliff_Silverfish, "Silverfish", true, 42, 50, 40, 15, 8, 16, EntityType.SILVERFISH, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE5MWRhYjgzOTFhZjVmZGE1NGFjZDJjMGIxOGZiZDgxOWI4NjVlMWE4ZjFkNjIzODEzZmE3NjFlOTI0NTQwIn19fQ=="))), //lvl 42 health 50
    RAIN_SLIME(BaseMobType.WebbedCliff_Rain_Slime, "Rain Slime", true, 42, 1000, 50, 1, 1, 1, EntityType.SLIME, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQxYTMxOTdkNWQzYjU0YzNjYmI3ZDVmNzUzMDU5M2QxNDljN2ZlYzc1M2E1YWQ4ZjEwMjc2ZDI2NDM0MjcwNSJ9fX0="))),
    TOXIC_RAIN_SLIME(BaseMobType.WebbedCliff_Rain_Slime, "Toxic Rain Slime", true, 42, 1000, 50, 1, 1, 1, EntityType.SLIME, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmM3N2UxMzZkOWI4NTE3ZmNjNDRmZjhmMDA2ZWQyYTEwOGY4ODIyZmRjMzRiODA3NjgwYmVjYTY1NzI4M2Y4MSJ9fX0="))),

    ENDERMAN_1(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 1, 160, 10, 15, 2, 2, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 1 health 160
    ENDERMAN_2(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 2, 184, 12, 15, 2, 2, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 2 health 184
    ENDERMAN_3(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 3, 211, 15, 15, 3, 3, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 3 health 211
    ENDERMAN_4(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 4, 243, 20, 15, 4, 4, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 4 health 243
    ENDERMAN_5(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 5, 279, 25, 15, 5, 5, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 5 health 279
    ENDERMAN_6(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 6, 321, 30, 15, 6, 6, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 6 health 321
    ENDERMAN_7(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 7, 370, 35, 15, 7, 7, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 7 health 370
    ENDERMAN_8(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 8, 425, 40, 15, 8, 8, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 8 health 425
    ENDERMAN_9(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 9, 489, 45, 15, 9, 9, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 9 health 489
    ENDERMAN_10(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 10, 562, 50, 15, 9, 10, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 10 health 562
    ENDERMAN_11(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 11, 647, 60, 15, 10, 11, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 11 health 647
    ENDERMAN_12(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 12, 744, 70, 15, 10, 12, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 12 health 744
    ENDERMAN_13(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 13, 859, 80, 15, 11, 12, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 13 health 856
    ENDERMAN_14(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 14, 984, 90, 15, 12, 13, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 14 health 984
    ENDERMAN_15(BaseMobType.PrivateIsland_Enderman, "Enderman", true, 15, 1132, 100, 15, 12, 14, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 15 health 1132
    ENDERMAN_42(BaseMobType.ShatteredEnd_Enderman, "Enderman", true, 42, 4500, 500, 30, 10, 8, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 42 health 4500
    ENDERMAN_45(BaseMobType.ShatteredEnd_Enderman, "Enderman", true, 45, 6000, 600, 34, 12, 10, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 45 health 6000
    ENDERMAN_48(BaseMobType.ShatteredEnd_Enderman, "Enderman", true, 48, 7500, 650, 38, 14, 12, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 48 health 7500
    ENDERMAN_50(BaseMobType.ShatteredEnd_Enderman, "Enderman", true, 50, 9000, 700, 42, 16, 14, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NjdlYjU4NmMyMzE4NzQzNDFiOTA0NGZkNzA4YzMzZTNmYzU5ODZkMjlmYTU5NzZiOGY3Yjk0ZmQxYzc3NSJ9fX0="))), //lvl 50 health 9000
    ZEALOT_55(BaseMobType.ShatteredEnd_Zealot, "Zealot", true, 55, 13000, 1250, 45, 15, 10, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjcxMDRiYTZmMDkzNWY3MTI5NjZkMDUxYmUwNGQ5OWZiODY3ZTg1N2JhZTRjZmYyZjJiMjQyOWVkMjM3M2M1ZSJ9fX0="))), //lvl 55 health 13000
    ZEALOT_55_SUMMON_EYE(BaseMobType.ShatteredEnd_Special_Zealot, "Zealot", true, 55, 2000, 1250, 45, 15, 10, EntityType.ENDERMAN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDIyMmNmMzNlYTZiZWI0NWQ2MjE3MDA2NWU3YzZjNGM1N2JmZmIwZTQ2MGEwNWE1YTE2M2IwN2RlNGZhYTIwMSJ9fX0="))), //lvl 55 health 13000
    OBSIDIAN_DEFENDER_55(BaseMobType.ShatteredEnd_Obsidian_Defender, "Obsidian Defender", true, 55, 10000, 200, 40, 15, 10, EntityType.WITHER_SKELETON, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzRjMGU4MzMwNmU2OTU5OTJmMTFlYzI4YjIxNzQ5NjVkZTExNzdhMDM4NTAxZTI3YjJlYWY1MTU0N2Q5In19fQ=="))), //lvl 55 health 10000
    WATCHER_55(BaseMobType.ShatteredEnd_Watcher, "Watcher", true, 55, 9500, 500, 40, 15, 10, EntityType.SKELETON, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFiYmNkZDBhNWZlYjcyYWEyZTQzZGI5YWUyZmRkNDA2OTE5OGIxOWI1MTI2ZDliMTY0OTFjZTcxMjE2NmU3In19fQ=="))), //lvl 55 health 9500
    ENDERMITE_37(BaseMobType.ShatteredEnd_Endermite, "Endermite", true, 37, 2000, 400, 40, 10, 8, EntityType.ENDERMITE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWExYTA4MzFhYTAzYWZiNDIxMmFkY2JiMjRlNWRmYWE3ZjQ3NmExMTczZmNlMjU5ZWY3NWE4NTg1NSJ9fX0="))), //lvl 37 health 2000
    ENDERMITE_40(BaseMobType.ShatteredEnd_Endermite, "Endermite", true, 40, 2300, 475, 40, 11, 12, EntityType.ENDERMITE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWExYTA4MzFhYTAzYWZiNDIxMmFkY2JiMjRlNWRmYWE3ZjQ3NmExMTczZmNlMjU5ZWY3NWE4NTg1NSJ9fX0="))), //lvl 40 health 2000
    ENDERMITE_NEST_40(BaseMobType.ShatteredEnd_Nest_Endermite, "Nest Endermite", true, 40, 2300, 475, 40, 11, 12, EntityType.ENDERMITE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWE5NTM4NjlhMGM1M2U3YzVjMDEyMTE1YzJhNDMwM2NjYmZmOWQyZmI3OGJhZDRlMjhkMDY2ZWQxNmQwZjkxIn19fQ=="))), //lvl 40 health 2000


    BLAZE_30(BaseMobType.InfernoFortress_Blaze, "Blaze", true, 30, 2300, 475, 40, 11, 12, EntityType.BLAZE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ=="))), //lvl 40 health 2000
    MAGMACUBE_30(BaseMobType.InfernoFortress_MagmaCube, "MagmaCube", true, 30, 2300, 475, 40, 11, 12, EntityType.MAGMA_CUBE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFjOTdhMDZlZmRlMDRkMDAyODdiZjIwNDE2NDA0YWIyMTAzZTEwZjA4NjIzMDg3ZTFiMGMxMjY0YTFjMGYwYyJ9fX0="))), //lvl 40 health 2000
    GUARDIAN_40(BaseMobType.InfernoFortress_MagmaCube, "Guardian", true, 40, 5000, 500, 40, 11, 12, EntityType.MAGMA_CUBE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTJkMTYyMDMwMGE4ZTFlMGJkYTcwYzcxZWZiYzQ2Yjc4NDVkNjE3MTU4NGQ4MDBmYTkwMDRjZmFkODhkMmFhOCJ9fX0="))), //lvl 40 health 2000

    BLAZE_25(BaseMobType.InfernoFortress_Blaze, "Blaze", true, 25, 2300, 475, 40, 11, 12, EntityType.BLAZE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ=="))), //lvl 40 health 2000
    MAGMACUBE_25(BaseMobType.InfernoFortress_MagmaCube, "MagmaCube", true, 25, 2300, 475, 40, 11, 12, EntityType.MAGMA_CUBE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFjOTdhMDZlZmRlMDRkMDAyODdiZjIwNDE2NDA0YWIyMTAzZTEwZjA4NjIzMDg3ZTFiMGMxMjY0YTFjMGYwYyJ9fX0="))), //lvl 40 health 2000
    WITHER_SKELETON_25(BaseMobType.InfernoFortress_Wither_Skeleton, "Wither Skeleton", true, 25, 2300, 475, 40, 11, 12, EntityType.WITHER_SKELETON, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTA2MWU1ZDVlZDY5YzViZDJlNzllNWJjNGNjYzk0NDEzZjE0M2UyYjVlODIxMzE3NjE1ZjI5YmYxYjIwMGI4NyJ9fX0="))), //lvl 40 health 2000
    GHAST_10(BaseMobType.InfernoFortress_Ghast, "Ghast", true, 10, 2300, 475, 40, 11, 12, EntityType.GHAST, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRhYjhhMjJlNzY4N2NjNGM3OGYzYjZmZjViMWViMDQ5MTdiNTFjZDNjZDdkYmNlMzYxNzExNjBiM2M3N2NlZCJ9fX0="))), //lvl 40 health 2000
    ZOMBIFIED_PIGLIN_25(BaseMobType.InfernoFortress_Piglin, "Piglin", true, 25, 2300, 475, 40, 11, 12, EntityType.ZOMBIFIED_PIGLIN, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljMjI4ZDc4ZTM5NTUwMGJjNTRlOGU0NWU5ODExYWY4YzllYTU4MDQ1ZDcyZmE2ZjA5OTIxZGE1N2UwNTViNCJ9fX0="))), //lvl 40 health 2000

    /*npc*/
    GOBLIN(BaseMobType.DwarvensDelve_Goblin, "Goblin", true, 25, 800, 100, 1, 1, 1, EntityType.ZOMBIE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("ewogICJ0aW1lc3RhbXAiIDogMTYxNTE2MTQ5NzA2MCwKICAicHJvZmlsZUlkIiA6ICJjZGM5MzQ0NDAzODM0ZDdkYmRmOWUyMmVjZmM5MzBiZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJSYXdMb2JzdGVycyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82NTJjZTE0Y2IwZDZhNTJiNWNiMTA2Y2JhYTU4ZTFkMTI5MGRjY2YzNDFhNWZiNzQ4ZGE2MTIyZGQ5NTcxNWY5IgogICAgfQogIH0KfQ"))),
    STAR_SENTRY(BaseMobType.DwarvensDelve_Star_Sentry, "Star Sentry", true, 50, 10, 100, 1, 1, 1, EntityType.ZOMBIE, new SkyBlockItemBuilder(HeadCreator.createCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDg3YjhmM2NmMWMxMDE0ODgzMjc2ZjIzMDUwMWQ1YWYxMzk3Njk5MDcxYmZhYjFlZTI5NGM4NjE3MjJjYzJmIn19fQ=="))),
    ;
    @Getter
    private final BaseMobType baseMobType;
    @Getter
    private final String displayName;
    @Getter
    private final int lvl;
    @Getter
    private final int health;
    @Getter
    private final int damage;
    @Getter
    private final double combatxp;
    @Getter
    private final double coins;
    @Getter
    private final float xp;
    private final EntityType entityType;
    private final SkyBlockItemBuilder itemBuilder;
    @Getter
    private boolean aggressive = false;

    MobType(BaseMobType baseMobType, String displayName, int lvl, int health, int damage, double combatxp, double coins, float xp, EntityType entityType, SkyBlockItemBuilder itemBuilder)
    {
        this.baseMobType = baseMobType;
        this.displayName = displayName;
        this.lvl = lvl;
        this.health = health;
        this.damage = damage;
        this.combatxp = combatxp;
        this.coins = coins;
        this.xp = xp;
        this.entityType = entityType;
        this.itemBuilder = itemBuilder;
    }

    MobType(BaseMobType baseMobType, String displayName, boolean aggressive, int lvl, int health, int damage, double combatxp, double coins, float xp, EntityType entityType, SkyBlockItemBuilder itemBuilder)
    {
        this(baseMobType, displayName, lvl, health, damage, combatxp, coins, xp, entityType, itemBuilder);
        this.aggressive = aggressive;
    }

    public SkyBlockItemBuilder getSkyBlockItemBuilder()
    {
        return itemBuilder.clone();
    }

    public EntityType getMCEntityType()
    {
        return entityType;
    }

    public boolean isBaneOfArthropods()
    {
        return switch(this.entityType)
        {
            case SPIDER, CAVE_SPIDER, SILVERFISH -> true;
            default -> false;
        };
    }

    public boolean isSmite()
    {
        return switch(this.entityType)
        {
            case SKELETON, ZOMBIE, WITHER, ZOMBIFIED_PIGLIN -> true;
            default -> false;
        };
    }

    public boolean isCubism()
    {
        return switch(this.entityType)
        {
            case MAGMA_CUBE, SLIME -> true;
            default -> false;
        };
    }

    public boolean isEnderSlayer()
    {
        return switch(this.entityType)
        {
            case ENDERMAN, ENDERMITE -> true;
            default -> false;
        };
    }

    public boolean isImpaling()
    {
        return switch(this.entityType)
        {
            case GUARDIAN, ELDER_GUARDIAN -> true;
            default -> false;
        };
    }

    public boolean isBlaze()
    {
        return switch(this.entityType)
        {
            case BLAZE -> true;
            default -> false;
        };
    }
}