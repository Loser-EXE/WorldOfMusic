package worldofmusic.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import worldofmusic.WorldOfMusic;
import worldofmusic.item.ModItems;

public class ModBlocks {
    public static final Block MUSICIAN_WORKSTATION = Registry.register(
            Registry.BLOCK,
            new Identifier(WorldOfMusic.MOD_ID, "musician_workstation"),
            new Block(FabricBlockSettings.of(Material.WOOD).hardness(1.0f))
    );

    public static final BlockItem MUSICIAN_WORKSTATION_ITEM = Registry.register(
            Registry.ITEM,
            new Identifier(WorldOfMusic.MOD_ID, "musician_workstation"),
            new BlockItem(MUSICIAN_WORKSTATION, new FabricItemSettings().group(ModItems.ITEM_GROUP))
    );

    public static void registerBlocks() {
        WorldOfMusic.LOGGER.info("Registering blocks");
    }
}
