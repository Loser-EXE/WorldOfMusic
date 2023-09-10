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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        this.getContextModel().setArmAngle(arm, matrices);
        float headYaw = ((ModelWithHead)this.getContextModel()).getHead().yaw;

        if(instrument == ModItems.FIFE) {
            matrices.translate(0, 0.27, -0.35);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(147));
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0));
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-60 + (headYaw * 60)));
        }

        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, stack, transformationMode, false, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
