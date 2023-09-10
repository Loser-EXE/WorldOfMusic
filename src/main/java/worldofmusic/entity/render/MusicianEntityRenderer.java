package worldofmusic.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import worldofmusic.entity.pillager.MusicianPillagerEntity;
import worldofmusic.entity.render.feature.InstrumentFeatureRenderer;
import worldofmusic.entity.render.model.MusicianEntityModel;

abstract public class MusicianEntityRenderer<T extends MusicianPillagerEntity> extends MobEntityRenderer<T, MusicianEntityModel<T>> {
    public MusicianEntityRenderer(EntityRendererFactory.Context context, MusicianEntityModel<T> entityModel) {
        super(context, entityModel, 0.5f);
        this.addFeature(new HeadFeatureRenderer<>(this, context.getModelLoader()));
        this.addFeature(new InstrumentFeatureRenderer<>(this));
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override
    protected void scale(T illagerEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
    }
}
