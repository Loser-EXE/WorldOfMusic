package worldofmusic.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.pillager.MusicianPillagerEntity;
import worldofmusic.entity.render.model.ModEntitiyModelLayers;
import worldofmusic.entity.render.model.MusicianEntityModel;

@Environment(EnvType.CLIENT)
public class DrummerPillagerEntityRenderer extends MusicianEntityRenderer<MusicianPillagerEntity> {
    public DrummerPillagerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MusicianEntityModel<>(ctx.getPart(ModEntitiyModelLayers.DRUMMER_PILLAGER)));
    }

    @Override
    public Identifier getTexture(MusicianPillagerEntity entity) {
        return new Identifier(WorldOfMusic.MOD_ID, "textures/entity/drummer_pillager.png");
    }
}
