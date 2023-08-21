package worldofmusic.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import worldofmusic.WorldOfMusic;
import worldofmusic.block.ModBlocks;
import worldofmusic.mixin.PoiTypesInvoker;
import worldofmusic.mixin.VillagerProfessionInvoker;

public class MusicianVillager {
    public static final PointOfInterestType MUSICIAN_WORKSTATION = PoiTypesInvoker.registerInvoker("musician", PoiTypesInvoker.getAllStatesOfInvoker(ModBlocks.MUSICIAN_WORKSTATION), 1, 1);
    public static final VillagerProfession MUSICIAN_PROFESSION = Registry.register(
            Registry.VILLAGER_PROFESSION,
            new Identifier(WorldOfMusic.MOD_ID, "musician"),
            VillagerProfessionInvoker.initInvoker("musician", MUSICIAN_WORKSTATION, ImmutableSet.of(), ImmutableSet.of(), null));

    public static void registerMusicianVillager() {
        WorldOfMusic.LOGGER.info("Registering musician profession");
    }
}
