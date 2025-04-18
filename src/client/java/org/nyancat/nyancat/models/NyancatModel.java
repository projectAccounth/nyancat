package org.nyancat.nyancat.models;

import org.nyancat.nyancat.models.entity_renderstates.NyancatRenderState;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class NyancatModel extends EntityModel<NyancatRenderState> {
    public ModelPart foot1;
    public ModelPart foot2;
    public ModelPart foot3;
    public ModelPart foot4;
    public ModelPart black5;
    public ModelPart black6;
    public ModelPart facep8;
    public ModelPart black8;
    public ModelPart black9;
    public ModelPart black16;
    public ModelPart black15;
    public ModelPart black14;
    public ModelPart black13;
    public ModelPart black11;
    public ModelPart black12;
    public ModelPart black10;
    public ModelPart black17;
    public ModelPart black1;
    public ModelPart black2;
    public ModelPart black3;
    public ModelPart face1;
    public ModelPart facep2;
    public ModelPart facep3;
    public ModelPart facep4;
    public ModelPart facep11;
    public ModelPart facep12;
    public ModelPart facep5;
    public ModelPart facep6;
    public ModelPart facep10;
    public ModelPart facep7;
    public ModelPart black7;
    public ModelPart body;
    public ModelPart black20;
    public ModelPart black21;
    public ModelPart black22;
    public ModelPart black25;
    public ModelPart foot1b1;
    public ModelPart black24;
    public ModelPart black19;
    public ModelPart black18;
    public ModelPart black23;
    public ModelPart black26;
    public ModelPart black29;
    public ModelPart black28;
    public ModelPart black27;
    public ModelPart tailp1;
    public ModelPart tailp2;
    public ModelPart tailp3;
    public ModelPart tail4;
    public ModelPart tail5;
    public ModelPart tail6;
    public ModelPart tail7;
    public ModelPart tail8;
    public NyancatModel(ModelPart root) {
        super(root);
        this.foot1 = root.getChild("foot1");
        this.foot2 = root.getChild("foot2");
        this.foot3 = root.getChild("foot3");
        this.foot4 = root.getChild("foot4");
        this.black5 = root.getChild("black5");
        this.black6 = root.getChild("black6");
        this.facep8 = root.getChild("facep8");
        this.black8 = root.getChild("black8");
        this.black9 = root.getChild("black9");
        this.black16 = root.getChild("black16");
        this.black15 = root.getChild("black15");
        this.black14 = root.getChild("black14");
        this.black13 = root.getChild("black13");
        this.black11 = root.getChild("black11");
        this.black12 = root.getChild("black12");
        this.black10 = root.getChild("black10");
        this.black17 = root.getChild("black17");
        this.black1 = root.getChild("black1");
        this.black2 = root.getChild("black2");
        this.black3 = root.getChild("black3");
        this.face1 = root.getChild("face1");
        this.facep2 = root.getChild("facep2");
        this.facep3 = root.getChild("facep3");
        this.facep4 = root.getChild("facep4");
        this.facep11 = root.getChild("facep11");
        this.facep12 = root.getChild("facep12");
        this.facep5 = root.getChild("facep5");
        this.facep6 = root.getChild("facep6");
        this.facep10 = root.getChild("facep10");
        this.facep7 = root.getChild("facep7");
        this.black7 = root.getChild("black7");
        this.body = root.getChild("body");
        this.black20 = root.getChild("black20");
        this.black21 = root.getChild("black21");
        this.black22 = root.getChild("black22");
        this.black25 = root.getChild("black25");
        this.foot1b1 = root.getChild("foot1b1");
        this.black24 = root.getChild("black24");
        this.black19 = root.getChild("black19");
        this.black18 = root.getChild("black18");
        this.black23 = root.getChild("black23");
        this.black26 = root.getChild("black26");
        this.black29 = root.getChild("black29");
        this.black28 = root.getChild("black28");
        this.black27 = root.getChild("black27");
        this.tailp1 = root.getChild("tailp1");
        this.tailp2 = root.getChild("tailp2");
        this.tailp3 = root.getChild("tailp3");
        this.tail4 = root.getChild("tail4");
        this.tail5 = root.getChild("tail5");
        this.tail6 = root.getChild("tail6");
        this.tail7 = root.getChild("tail7");
        this.tail8 = root.getChild("tail8");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        root.addChild("foot1", ModelPartBuilder.create()
            .uv(36, 23)
            .cuboid(-2f, -1f, -1f, 2f, 4f, 5f),
            ModelTransform.of(3.4f, 21f, 10f, 0f, 0f, 0f));
        root.addChild("foot2", ModelPartBuilder.create()
            .uv(36, 23)
            .cuboid(0f, 0f, -2.5f, 2f, 4f, 5f),
            ModelTransform.of(5.3f, 20f, 5f, 0f, 0f, 0f));
        root.addChild("foot3", ModelPartBuilder.create()
            .uv(50, 23)
            .cuboid(-2f, 0f, -2.5f, 2f, 4f, 5f),
            ModelTransform.of(3.5f, 20f, -4f, 0f, 0f, 0f));
        root.addChild("foot4", ModelPartBuilder.create()
            .uv(50, 23)
            .cuboid(0f, 0f, -2.5f, 2f, 4f, 5f),
            ModelTransform.of(5.3f, 20f, -9f, 0f, 0f, 0f));
        root.addChild("black5", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 10f, 1f, 1f),
            ModelTransform.of(3f, 20f, -3f, 0f, 1.5708f, 0f));
        root.addChild("black6", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 19f, -13f, 0f, 1.5708f, 0f));
        root.addChild("facep8", ModelPartBuilder.create()
            .uv(0, 12)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 10f, 1f),
            ModelTransform.of(3f, 9f, -13f, 0f, 1.5708f, 0f));
        root.addChild("black8", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 5f, 1f),
            ModelTransform.of(3f, 13f, -15f, 0f, 1.5708f, 0f));
        root.addChild("black9", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 4f, 1f),
            ModelTransform.of(3f, 9f, -14f, 0f, 1.5708f, 0f));
        root.addChild("black16", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, -0.5f, -0.5f, 2f, 1f, 1f),
            ModelTransform.of(3f, 8.5f, -12f, 0f, 1.5708f, 0f));
        root.addChild("black15", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 9f, -11f, 0f, 1.5708f, 0f));
        root.addChild("black14", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 10f, -10f, 0f, 1.5708f, 0f));
        root.addChild("black13", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, -0.5f, -0.5f, 4f, 1f, 1f),
            ModelTransform.of(3f, 11.5f, -6f, 0f, 1.5708f, 0f));
        root.addChild("black11", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 10f, -5f, 0f, 1.5708f, 0f));
        root.addChild("black12", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 9f, -4f, 0f, 1.5708f, 0f));
        root.addChild("black10", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, -0.5f, -0.5f, 2f, 1f, 1f),
            ModelTransform.of(3f, 8.5f, -2f, 0f, 1.5708f, 0f));
        root.addChild("black17", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 4f, 1f),
            ModelTransform.of(3f, 9f, -1f, 0f, 1.5708f, 0f));
        root.addChild("black1", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 5f, 1f),
            ModelTransform.of(3f, 13f, 0f, 0f, 1.5708f, 0f));
        root.addChild("black2", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 1f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 17f, -1f, 0f, 1.5708f, 0f));
        root.addChild("black3", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 19f, -2f, 0f, 1.5708f, 0f));
        root.addChild("face1", ModelPartBuilder.create()
            .uv(0, 23)
            .cuboid(-0.5f, 0f, -0.5f, 10f, 8f, 1f),
            ModelTransform.of(2.9f, 12f, -3f, 0f, 1.5708f, 0f));
        root.addChild("facep2", ModelPartBuilder.create()
            .uv(4, 17)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 5f, 1f),
            ModelTransform.of(3f, 13f, -1f, 0f, 1.5708f, 0f));
        root.addChild("facep3", ModelPartBuilder.create()
            .uv(0, 12)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 10f, 1f),
            ModelTransform.of(3f, 9f, -2f, 0f, 1.5708f, 0f));
        root.addChild("facep4", ModelPartBuilder.create()
            .uv(4, 19)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 3f, 1f),
            ModelTransform.of(3f, 9f, -3f, 0f, 1.5708f, 0f));
        root.addChild("facep11", ModelPartBuilder.create()
            .uv(4, 20)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 2f, 1f),
            ModelTransform.of(3f, 10f, -4f, 0f, 1.5708f, 0f));
        root.addChild("facep12", ModelPartBuilder.create()
            .uv(4, 21)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 11f, -5f, 0f, 1.5708f, 0f));
        root.addChild("facep5", ModelPartBuilder.create()
            .uv(4, 20)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 2f, 1f),
            ModelTransform.of(3f, 10f, -11f, 0f, 1.5708f, 0f));
        root.addChild("facep6", ModelPartBuilder.create()
            .uv(4, 21)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 11f, -10f, 0f, 1.5708f, 0f));
        root.addChild("facep10", ModelPartBuilder.create()
            .uv(4, 19)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 3f, 1f),
            ModelTransform.of(3f, 9f, -12f, 0f, 1.5708f, 0f));
        root.addChild("facep7", ModelPartBuilder.create()
            .uv(4, 17)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 5f, 1f),
            ModelTransform.of(3f, 13f, -14f, 0f, 1.5708f, 0f));
        root.addChild("black7", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 1f, 1f, 1f),
            ModelTransform.of(3f, 18f, -14f, 0f, 1.5708f, 0f));
        root.addChild("body", ModelPartBuilder.create()
            .uv(16, 3)
            .cuboid(-0.5f, 0f, -0.5f, 22f, 17f, 2f),
            ModelTransform.of(3.8f, 4f, 11f, 0f, 1.5708f, 0f));
        root.addChild("black20", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.3f, 5f, 9.5f, 0f, 0f, 0f));
        root.addChild("black21", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.3f, 4f, 8.5f, 0f, 0f, 0f));
        root.addChild("black22", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.3f, 6f, 10.5f, 0f, 0f, 0f));
        root.addChild("black25", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.3f, 19f, 9.5f, 0f, 0f, 0f));
        root.addChild("foot1b1", ModelPartBuilder.create()
            .uv(0, 9)
            .cuboid(-1f, 0.5f, -2.5f, 2f, 1f, 1f),
            ModelTransform.of(4.3f, 19f, 11f, 0f, 0f, 0f));
        root.addChild("black24", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.3f, 18f, 10.5f, 0f, 0f, 0f));
        root.addChild("black19", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0.5f, -0.5f, 2f, 1f, 1f),
            ModelTransform.of(3.9f, 5.5f, -10f, 0f, 0f, 0f));
        root.addChild("black18", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, -0.5f, -0.5f, 2f, 1f, 1f),
            ModelTransform.of(3.9f, 5.5f, -9f, 0f, 0f, 0f));
        root.addChild("black23", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 0f, -0.5f, 2f, 1f, 1f),
            ModelTransform.of(4f, 4f, -8f, 0f, 0f, 0f));
        root.addChild("black26", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.3f, 20f, 8.5f, 0f, 0f, 0f));
        root.addChild("black29", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.5f, 18f, -10.5f, 0f, 0f, 0f));
        root.addChild("black28", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.5f, 19f, -9.5f, 0f, 0f, 0f));
        root.addChild("black27", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(0f, 0f, 0f, 2f, 1f, 1f),
            ModelTransform.of(3.5f, 19f, -8.5f, 0f, 0f, 0f));
        root.addChild("tailp1", ModelPartBuilder.create()
            .uv(8, 18)
            .cuboid(-0.5f, 0.5f, 0.5f, 1f, 1f, 3f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tailp2", ModelPartBuilder.create()
            .uv(5, 11)
            .cuboid(-0.5f, -0.5f, 0.5f, 1f, 1f, 4f),
            ModelTransform.of(4.23333f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tailp3", ModelPartBuilder.create()
            .uv(4, 3)
            .cuboid(-0.5f, -1.5f, 0.5f, 1f, 1f, 5f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tail4", ModelPartBuilder.create()
            .uv(22, 25)
            .cuboid(-0.5f, -2.5f, 0.5f, 1f, 1f, 6f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tail5", ModelPartBuilder.create()
            .uv(24, 23)
            .cuboid(-0.5f, -3.5f, 1.5f, 1f, 1f, 5f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tail6", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, -4.5f, 2.5f, 1f, 1f, 4f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tail7", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, 1.5f, 0.5f, 1f, 1f, 1f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        root.addChild("tail8", ModelPartBuilder.create()
            .uv(0, 0)
            .cuboid(-0.5f, -3.5f, 0.5f, 1f, 1f, 1f),
            ModelTransform.of(4.2f, 14f, 11f, 0f, 0f, 0f));
        return TexturedModelData.of(modelData, 64, 32);
    }
    @Override
    public void setAngles(NyancatRenderState state) 
    {
        float f1 = state.limbSwingAmplitude;
        float f = state.limbSwingAnimationProgress;
        float sgn = (f < 0.01f) ? 0.0f : 1f;

        float fpPitch = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1 * sgn;

        this.foot1.pitch = fpPitch;
        this.foot2.pitch = MathHelper.cos((float)(f * 0.6662f + 3.141593f)) * 1.4f * f1 * sgn;
        this.foot3.pitch = MathHelper.cos((float)(f * 0.6662f + 3.141593f)) * 1.4f * f1 * sgn;
        this.foot4.pitch = fpPitch;

        this.tailp1.pitch = fpPitch;
        this.tailp2.pitch = fpPitch;
        this.tailp3.pitch = fpPitch;
        this.tail4.pitch = fpPitch;
        this.tail5.pitch = fpPitch;
        this.tail6.pitch = fpPitch;
        this.tail7.pitch = fpPitch;
        this.tail8.pitch = fpPitch;
    }
}
