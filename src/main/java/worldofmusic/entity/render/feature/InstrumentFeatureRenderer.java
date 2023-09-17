package worldofmusic.entity.render.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Vec3f;
import worldofmusic.item.Instrument;
import worldofmusic.item.ModItems;

public class InstrumentFeatureRenderer<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms> extends HeldItemFeatureRenderer<T, M> {
    public InstrumentFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    protected void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        Instrument instrument;
        if(!(stack.getItem() instanceof Instrument)) return;
        instrument = (Instrument) stack.getItem();

        matrices.push();
        float headYaw = ((ModelWithHead)this.getContextModel()).getHead().yaw;

        if(instrument == ModItems.FIFE) {
            this.getContextModel().setArmAngle(arm, matrices);
            matrices.translate(0, 0.27, -0.35);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(147));
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0));
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-60 + ((headYaw < 0.5 ? headYaw : 0.5f) * 60)));
        } else if(instrument == ModItems.DRUM) {
            matrices.translate(0, 0.7, -0.5);
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-180));
        }

        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, stack, transformationMode, false, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
