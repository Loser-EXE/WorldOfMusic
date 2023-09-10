package worldofmusic.entity.render.model;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import worldofmusic.entity.pillager.MusicianPillagerEntity;
import worldofmusic.item.Instrument;
import worldofmusic.item.ModItems;

public class MusicianEntityModel<T extends MusicianPillagerEntity> extends SinglePartEntityModel<T> implements ModelWithArms, ModelWithHead {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart rightHand;
    private final ModelPart leftArm;
    private final ModelPart leftHand;

    private Instrument instrument;
    public MusicianEntityModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.leftLeg = root.getChild(EntityModelPartNames.LEFT_LEG);
        this.rightLeg = root.getChild(EntityModelPartNames.RIGHT_LEG);
        this.leftArm = root.getChild(EntityModelPartNames.LEFT_ARM);
        this.leftHand = leftArm.getChild("hand");
        this.rightArm = root.getChild(EntityModelPartNames.RIGHT_ARM);
        this.rightHand = rightArm.getChild("hand");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        ModelPartData head = root.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        head.addChild(EntityModelPartNames.NOSE, ModelPartBuilder.create().uv(24, 0).cuboid(-1.0f, -1.0f, -6.0f, 2.0f, 4.0f, 2.0f), ModelTransform.pivot(0.0f, -2.0f, 0.0f));
        root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 20).cuboid(-4.0f, 0.0f, -3.0f, 8.0f, 12.0f, 6.0f).uv(0, 38).cuboid(-4.0f, 0.0f, -3.0f, 8.0f, 18.0f, 6.0f, new Dilation(0.5f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        root.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 22).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), ModelTransform.pivot(-2.0f, 12.0f, 0.0f));
        root.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), ModelTransform.pivot(2.0f, 12.0f, 0.0f));
        ModelPartData leftArm = root.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 46).mirrored().cuboid(-1f, -2f, -2f, 4f, 6f, 4f), ModelTransform.pivot(5.0f, 2.0f, 0.0f));
        leftArm.addChild("hand", ModelPartBuilder.create().uv(45, 11).mirrored().cuboid(-4, 0, -4, 4f, 6f, 4f), ModelTransform.pivot(3.0f, 4.0f, 2.0f));
        ModelPartData rightArm = root.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 46).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 6.0f, 4.0f), ModelTransform.pivot(-5.0f, 2.0f, 0.0f));
        rightArm.addChild("hand", ModelPartBuilder.create().uv(45, 11).cuboid(0, 0, -4, 4.0F, 6.0F, 4.0F), ModelTransform.pivot(-3.0f, 4.0f, 2.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        instrument = entity.getInstrument();
        this.head.yaw = headYaw * ((float)Math.PI / 180);
        this.head.pitch = headPitch * ((float)Math.PI / 180);

        if(instrument == ModItems.FIFE) {
            this.rightArm.pitch = -1f;
            this.rightHand.pitch = -1.1f;
            this.rightArm.yaw = 0.4f + this.head.yaw;
            this.rightHand.yaw = -0.001f; //Fix z-fighting on the arm

            this.leftArm.pitch = -1.1f;
            this.leftArm.yaw = 0.6f + this.head.yaw;
            this.leftHand.pitch = -0.7f;
            this.leftHand.yaw = 0.2f;
        } else if(instrument == ModItems.DRUM) {
            this.rightArm.pitch = -1.58f;
        } else {
            this.rightArm.pitch = 0;
            this.rightArm.yaw = 0.0f;
            this.rightArm.roll = 0.0f;
            this.leftArm.pitch = 0;
            this.leftArm.yaw = 0.0f;
            this.leftArm.roll = 0.0f;
            this.rightLeg.yaw = 0.0f;
            this.rightLeg.roll = 0.0f;
            this.leftLeg.yaw = 0.0f;
            this.leftLeg.roll = 0.0f;
        }

        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662f) * 1.4f * limbDistance * 0.5f;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * limbDistance * 0.5f;
    }

    private ModelPart getArm(Arm arm) {
        if (arm == Arm.LEFT) {
            return this.leftArm;
        }
        return this.rightArm;
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        getArm(arm).rotate(matrices);
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
