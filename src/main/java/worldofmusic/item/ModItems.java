package worldofmusic.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.ModEntities;

public class ModItems {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(WorldOfMusic.MOD_ID, "item_group"))
            .icon(() -> new ItemStack(ModItems.DRUM))
            .build();
    private static final FabricItemSettings DEFAULT_INSTRUMENT_SETTINGS = new FabricItemSettings()
            .group(ITEM_GROUP)
            .maxCount(1);
    public static final Instrument DRUM = (Instrument) register("drum", new DrumItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument FIFE = (Instrument) register("fife", new FifeItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument BAGPIPES = (Instrument) register("bagpipes", new BagPipeItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument BUGLE = (Instrument) register("bugle", new BugleItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument HANDCRAFTED_DRUM = (Instrument) register("handcrafted_drum", new HandcraftedDrum(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument HANDCRAFTED_FIFE = (Instrument) register("handcrafted_fife", new HandcraftedFife(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument BALALAIKA = (Instrument) register("balalaika", new BalalaikaItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument BANJO = (Instrument) register("banjo", new BanjoItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument BONGOS = (Instrument) register("bongos", new BongosItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument GUITAR = (Instrument) register("guitar", new GuitarItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Instrument REED_PIPE = (Instrument) register("reed_pipe", new ReedPipeItem(DEFAULT_INSTRUMENT_SETTINGS));
    public static final Item DRUMMER_PILLAGER_SPAWN_EGG = register("drummer_pillager_spawn_egg", new SpawnEggItem(
            ModEntities.DRUMMER_PILLAGER_ENTITY,
            0x4d1593,
            0x950b1d,
            new FabricItemSettings()
                    .group(ITEM_GROUP)
    ));
    public static final Item FIFER_PILLAGER_SPAWN_EGG = register("fifer_pillager_spawn_egg", new SpawnEggItem(
            ModEntities.FIFER_PILLAGER_ENTITY,
            0xbb1850,
            0x1a79c3,
            new FabricItemSettings()
                    .group(ITEM_GROUP)
    ));
    public static final Item BAGPIPER_PILLAGER_SPAWN_EGG = register("bagpiper_pillager_spawn_egg", new SpawnEggItem(
            ModEntities.BAGPIPER_PILLAGER_ENTITY,
            0x62a036,
            0xd02020,
            new FabricItemSettings()
                    .group(ITEM_GROUP)
    ));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(WorldOfMusic.MOD_ID, name), item);
    }

    public static void registerItems() {
        WorldOfMusic.LOGGER.info("Registering items");
    }
}
