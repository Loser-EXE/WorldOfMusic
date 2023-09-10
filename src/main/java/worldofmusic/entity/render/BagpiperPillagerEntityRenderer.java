package worldofmusic.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.pillager.BagPiperPillagerEntity;
import worldofmusic.entity.render.model.ModEntitiyModelLayers;
import worldofmusic.entity.render.model.MusicianEntityModel;

public class BagpiperPillagerEntityRenderer extends MusicianEntityRenderer<BagPiperPillagerEntity> {
    public BagpiperPillagerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MusicianEntityModel<>(ctx.getPart(ModEntitiyModelLayers.BAGPIPER_PILLAGER_MODEL_LAYER)));
    }

    @Override
    public Identifier getTexture(BagPiperPillagerEntity entity) {
        return new Identifier(WorldOfMusic.MOD_ID, "textures/entity/bagpiper_pillager.png");
    }
}
