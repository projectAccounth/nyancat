package org.nyancat.nyancat.screens;

import org.lwjgl.glfw.GLFW;
import org.nyancat.nyancat.custom_payloads.c2s.ActionUpdatePayloadC2S;
import org.nyancat.nyancat.custom_payloads.c2s.NameUpdatePayloadC2S;
import org.nyancat.nyancat.entities.AbstractCatEntity;
import org.nyancat.nyancat.entities.AbstractCatEntity.CatAction;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TameEntityScreen extends Screen {
    private final AbstractCatEntity entity;
    private TextFieldWidget nameField;
    private boolean interacted = false;

    public TameEntityScreen(AbstractCatEntity entity, String entityName) {
        super(Text.literal(entityName + " Tame GUI"));
        this.entity = entity;
    }

    @Override
    protected void init() {
        int centerX = width / 2;

        nameField = new TextFieldWidget(textRenderer, centerX - 100, 65, 200, 20, Text.literal("Name"));
        nameField.setMaxLength(32);
        nameField.setText(entity.getCustomName() != null ? entity.getCustomName().getString() : "");
        nameField.setEditable(true);
        addSelectableChild(nameField);
        addDrawable(nameField);

        addDrawableChild(ButtonWidget.builder(Text.literal("Follow"),
            b -> setAction(CatAction.FOLLOW)).position(centerX - 99, 92).size(98, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Explore"), 
            b -> setAction(CatAction.DEFAULT)).position(centerX + 1, 92).size(98, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Stay"), 
            b -> setAction(CatAction.STAY)).position(centerX - 99, 114).size(98, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Fight"), 
            b -> setAction(CatAction.FIGHT)).position(centerX + 1, 114).size(98, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Level System"), 
            b -> openLevelGui()).position(centerX - 100, 136).size(200, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Save"), 
            b -> {
                entity.setCustomName(Text.literal(nameField.getText()));
                close();
            }
        ).position(centerX - 100, 165).size(200, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.translatable("gui.cancel"), 
            b -> close()
        ).position(centerX - 100, 187).size(200, 20).build());
    }

    private void setAction(CatAction action) {
        if (interacted) return;
        interacted = true;

        NameUpdatePayloadC2S namePacket = new NameUpdatePayloadC2S(this.entity.getId(), nameField.getText());
        ClientPlayNetworking.send(namePacket);

        ActionUpdatePayloadC2S packet = new ActionUpdatePayloadC2S(this.entity.getId(), action.ordinal());
        ClientPlayNetworking.send(packet);

        client.player.sendMessage(Text.literal(nameField.getText() + " will " + actionToText(action) + "!"), false);
        this.close();
    }

    private void openLevelGui() {
        // Open another screen
        client.setScreen(new LevelGuiScreen(entity, entity.getClass().getName()));
    }

    private String actionToText(CatAction action) {
        return switch (action) {
            case FOLLOW -> "Follow";
            case STAY -> "Stay";
            case DEFAULT -> "Explore";
            case FIGHT -> "Fight";
            default -> "Do something";
        };
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);

        // nameField.render(context, mouseX, mouseY, delta);
        context.drawTextWithShadow(textRenderer, Text.literal("Current Action: " + actionToText(entity.getAction())), width / 2 - 100, 53, 0x900000);
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 40, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            close();
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_ENTER) {
            setAction(entity.getAction());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void removed() {
        super.removed();
    }
}
