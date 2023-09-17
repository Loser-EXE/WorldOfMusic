package worldofmusic.entity.render.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import worldofmusic.WorldOfMusic;

@Environment(EnvType.CLIENT)
public class ModEntitiyModelLayers {
    public static final EntityModelLayer DRUMMER_PILLAGER = registerMain("drummer_pillager");
    public static final EntityModelLayer FIFER_PILLAGER = registerMain("fifer_pillager");
    public static final EntityModelLayer BAGPIPER_PILLAGER = registerMain("bagpiper_pillager");

    private static EntityModelLayer registerMain(String name) {
        return new EntityModelLayer(new Identifier(WorldOfMusic.MOD_ID, name), "main");
    }

    public static void create() {
        WorldOfMusic.LOGGER.info("Creating entity model layers");
    }
}
