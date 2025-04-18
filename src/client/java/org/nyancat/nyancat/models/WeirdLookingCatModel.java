package org.nyancat.nyancat.models;

import org.nyancat.nyancat.models.entity_renderstates.WeirdLookingCatRenderState;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class WeirdLookingCatModel extends EntityModel<WeirdLookingCatRenderState> {
    public ModelPart root;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg4;
    public ModelPart leg3;
    public ModelPart body;
    public ModelPart tail;
    public ModelPart nose;
    public ModelPart head;
    public ModelPart ear1p1;
    public ModelPart ear2p1;
    public ModelPart ear2p2;
    public ModelPart ear1p2;

	public WeirdLookingCatModel(ModelPart root) 
    {
		super(root);
        this.root = root.getChild("body");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.nose = root.getChild("nose");
        this.head = root.getChild("head");
        this.ear1p1 = root.getChild("ear1p1");
        this.ear2p1 = root.getChild("ear2p1");
        this.ear2p2 = root.getChild("ear2p2");
        this.ear1p2 = root.getChild("ear1p2");
	}
    
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        root.addChild("leg1", ModelPartBuilder.create()
            .uv(15, 20)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 6f, 1f),
            ModelTransform.origin(2f, 18.7f, -5.5f));
        root.addChild("leg2", ModelPartBuilder.create()
            .uv(15, 20)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 6f, 1f),
            ModelTransform.origin(-2f, 18.7f, -5.5f));
        root.addChild("leg4", ModelPartBuilder.create()
            .uv(15, 20)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 6f, 1f),
            ModelTransform.origin(2f, 18.7f, 4.5f));
        root.addChild("leg3", ModelPartBuilder.create()
            .uv(15, 20)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 6f, 1f),
            ModelTransform.origin(-2f, 18.7f, 4.5f));
        root.addChild("body", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 6f, 7f, 12f),
            ModelTransform.origin(-3f, 12f, -6.5f));
        root.addChild("tail", ModelPartBuilder.create()
            .uv(45, 1)
            .cuboid(-0.5f, -0.5f, -0.5f, 1f, 1f, 7f),
            ModelTransform.origin(0f, 13.7f, 5f));
        root.addChild("head", ModelPartBuilder.create()
            .uv(38, 19)
            .cuboid(-3.5f, -6f, -5.5f, 7f, 7f, 6f),
            ModelTransform.origin(0f, 14.7f, -5f));
        root.addChild("nose", ModelPartBuilder.create()
            .uv(1, 27)
            .cuboid(-2f, -1f, -6.5f, 4f, 2f, 1f),
            ModelTransform.origin(0f, 14.7f, -5f));
        root.addChild("ear1p1", ModelPartBuilder.create()
            .uv(1, 21)
            .cuboid(-3.5f, -7f, -3f, 3f, 1f, 1f),
            ModelTransform.origin(0f, 14.7f, -5f));
        root.addChild("ear2p1", ModelPartBuilder.create()
            .uv(1, 21)
            .cuboid(0.5f, -7f, -3f, 3f, 1f, 1f),
            ModelTransform.origin(0f, 14.7f, -5f));
        root.addChild("ear2p2", ModelPartBuilder.create()
            .uv(1, 24)
            .cuboid(1f, -8f, -3f, 2f, 1f, 1f),
            ModelTransform.origin(0f, 14.7f, -5f));
        root.addChild("ear1p2", ModelPartBuilder.create()
            .uv(1, 24)
            .cuboid(-3f, -8f, -3f, 2f, 1f, 1f),
            ModelTransform.origin(0f, 14.7f, -5f));
        return TexturedModelData.of(modelData, 64, 32);
    }
    @Override
    public void setAngles(WeirdLookingCatRenderState state) 
    {
        float f1 = state.limbSwingAmplitude;
        float f = state.limbSwingAnimationProgress;
        float f3 = state.relativeHeadYaw;
        float f4 = state.pitch;
        float sgn = (f < 0.01f) ? 0.0f : 1f;

        float fpPitch = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1 * sgn;

        this.head.pitch = f4 / 57.29578f;
        this.head.yaw = f3 / 57.29578f;
        this.ear1p1.pitch = f4 / 57.29578f;
        this.ear1p1.yaw = f3 / 57.29578f;
        this.ear2p1.pitch = f4 / 57.29578f;
        this.ear2p1.yaw = f3 / 57.29578f;
        this.ear2p2.pitch = f4 / 57.29578f;
        this.ear2p2.yaw = f3 / 57.29578f;
        this.ear1p2.pitch = f4 / 57.29578f;
        this.ear1p2.yaw = f3 / 57.29578f;
        this.nose.pitch = f4 / 57.29578f;
        this.nose.yaw = f3 / 57.29578f;
        this.leg1.pitch = fpPitch;
        this.tail.yaw = fpPitch;
        this.leg2.pitch = MathHelper.cos((float)(f * 0.6662f + 3.141593f)) * 1.4f * f1 * sgn;
        this.leg3.pitch = MathHelper.cos((float)(f * 0.6662f + 3.141593f)) * 1.4f * f1 * sgn;
        this.leg4.pitch = fpPitch;
    }
}