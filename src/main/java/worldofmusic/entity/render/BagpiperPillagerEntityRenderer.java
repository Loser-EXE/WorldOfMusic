package worldofmusic.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.util.Identifier;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.BagPiperPillagerEntity;
import worldofmusic.entity.render.model.ModEntitiyModelLayers;

public class BagpiperPillagerEntityRenderer extends IllagerEntityRenderer<BagPiperPillagerEntity> {
    public BagpiperPillagerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new IllagerEntityModel<>(ctx.getPart(ModEntitiyModelLayers.BAGPIPER_PILLAGER_MODEL_LAYER)), 0.5f);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(BagPiperPillagerEntity entity) {
        return new Identifier(WorldOfMusic.MOD_ID, "textures/entity/bagpiper_pillager.png");
    }
}
