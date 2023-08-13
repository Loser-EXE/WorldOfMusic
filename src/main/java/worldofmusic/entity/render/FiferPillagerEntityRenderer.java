package worldofmusic.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.util.Identifier;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.MusicianPillagerEntity;
import worldofmusic.entity.render.model.ModEntitiyModelLayers;

@Environment(EnvType.CLIENT)
public class FiferPillagerEntityRenderer extends IllagerEntityRenderer<MusicianPillagerEntity> {
    public FiferPillagerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new IllagerEntityModel<>(ctx.getPart(ModEntitiyModelLayers.DRUMMER_PILLAGER_MODEL_LAYER)), 0.5f);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(MusicianPillagerEntity entity) {
        return new Identifier(WorldOfMusic.MOD_ID, "textures/entity/fifer_pillager.png");
    }
}
