package org.nyancat.nyancat.screens;

import org.nyancat.nyancat.custom_payloads.c2s.LevelUpdatePayloadC2S;
import org.nyancat.nyancat.entities.AbstractCatEntity;
import org.nyancat.nyancat.entities.AbstractCatEntity.LevelType;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class LevelGuiScreen extends Screen {
    private final AbstractCatEntity entity;

    @SuppressWarnings("unused")
    private int tickCounter;

    private int strengthInc, healthInc, speedInc, skillPointDiff;

    private String entityName;

    public LevelGuiScreen(AbstractCatEntity entity, String entityName) {
        super(Text.of(entityName + " Leveling System"));
        this.entityName = entityName;
        this.entity = entity;
        this.strengthInc = 0;
        this.healthInc = 0;
        this.speedInc = 0;
        this.skillPointDiff = 0;
    }

    @Override
    protected void init() {
        super.init();
        this.tickCounter = 0;
        this.clearChildren();

        int centerX = this.width / 2;

        // Main buttons
        this.addDrawableChild(ButtonWidget.builder(Text.of("Back to game"), button -> close())
            .dimensions(centerX + 2, 175, 107, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("Back"), button ->
            client.setScreen(new TameEntityScreen(entity, entityName))
        ).dimensions(centerX - 107, 175, 98, 20).build());

        // Health buttons
        this.addDrawableChild(ButtonWidget.builder(Text.of("+"), button -> {
            LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.HEALTH.ordinal(), 1, false);
            ClientPlayNetworking.send(packet);

            if (entity.getSkillPoints() + skillPointDiff <= 0) return;

            --skillPointDiff;
            ++healthInc;
        }).dimensions(centerX - 50, 95, 20, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.of("-"), button -> {
            // ensuring that the current health does not fuck up with the max health
            if (entity.getHealth() > entity.getHealthLevel() - 3 &&
                entity.getMaxHealth() + healthInc > entity.getHealthLevel() - 3
            ) {
                LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.HEALTH.ordinal(), -1, false);
                ClientPlayNetworking.send(packet);

                ++skillPointDiff;
                --healthInc;
            }
        }).dimensions(centerX - 75, 95, 20, 20).build());

        // Speed buttons
        this.addDrawableChild(ButtonWidget.builder(Text.of("+"), button -> {
            LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.SPEED.ordinal(), 1, false);
            ClientPlayNetworking.send(packet);

            if (entity.getSkillPoints() + skillPointDiff <= 0) return;

            --skillPointDiff;
            ++speedInc;
        }).dimensions(centerX + 60, 95, 20, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.of("-"), button -> {
            if (entity.getSpeed() + speedInc > entity.getSpeedLevel() - 3) {
                LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.SPEED.ordinal(), -1, false);
                ClientPlayNetworking.send(packet);

                ++skillPointDiff;
                --speedInc;
            }
        }).dimensions(centerX + 35, 95, 20, 20).build());

        // Strength buttons
        this.addDrawableChild(ButtonWidget.builder(Text.of("+"), button -> {
            LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.STRENGTH.ordinal(), 1, false);
            ClientPlayNetworking.send(packet);

            if (entity.getSkillPoints() + skillPointDiff <= 0) return;

            --skillPointDiff;
            ++strengthInc;
        }).dimensions(centerX - 50, 130, 20, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.of("-"), button -> {
            if (entity.getStrength() + strengthInc > entity.getStrengthLevel() - 3) {
                LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.STRENGTH.ordinal(), -1, false);
                ClientPlayNetworking.send(packet);

                ++skillPointDiff;
                --strengthInc;
            }
        }).dimensions(centerX - 75, 130, 20, 20).build());
    }

    @Override
    public void tick() {
        super.tick();
        tickCounter++;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) 
    {
        this.renderBackground(ctx, mouseX, mouseY, delta);

        int centerX = this.width / 2;

        ctx.drawCenteredTextWithShadow(this.textRenderer, this.title, centerX, 40, 0x15629C);
        ctx.drawTextWithShadow(this.textRenderer, Text.of("Entity Name: §f" + entity.getName().getString()), centerX - 100, 55, 0xFFCC33);
        ctx.drawCenteredTextWithShadow(this.textRenderer, Text.of("Available Skill Points: §f" + (entity.getSkillPoints() + skillPointDiff)), centerX, 67, 0xFFCC33);
        ctx.drawTextWithShadow(this.textRenderer, Text.of("Entity Level: §f" + entity.getLevel()), centerX + 20, 55, 0xFFCC33);
        ctx.drawTextWithShadow(this.textRenderer, Text.of("Entity Health: §f" + entity.getHealth() + "/" + (entity.getMaxHealth() + healthInc)), centerX - 100, 83, 0xFFCC33);
        ctx.drawTextWithShadow(this.textRenderer, Text.of("Entity Speed: §f" + (entity.getSpeed() + speedInc)), centerX + 20, 83, 0xFFCC33);
        ctx.drawTextWithShadow(this.textRenderer, Text.of("Entity Strength: §f" + (entity.getStrength() + strengthInc)), centerX - 100, 118, 0xFFCC33);

        if (entity.getMaxHealth() + healthInc >= entity.getHealthLevel() &&
            entity.getSpeed() + speedInc >= entity.getSpeedLevel() &&
            entity.getStrength() + strengthInc >= entity.getStrengthLevel()
        ) {
            LevelUpdatePayloadC2S packet = new LevelUpdatePayloadC2S(this.entity.getId(), LevelType.HEALTH.ordinal(), 3, true);
            ClientPlayNetworking.send(packet);
            close();
            // entity.showLevelUpParticles(true);
        }

        super.render(ctx, mouseX, mouseY, delta);
    }
}