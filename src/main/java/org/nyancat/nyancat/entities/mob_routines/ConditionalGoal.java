package org.nyancat.nyancat.entities.mob_routines;

import java.util.function.Supplier;

import org.nyancat.nyancat.entities.AbstractCatEntity.CatAction;

import net.minecraft.entity.ai.goal.Goal;

public class ConditionalGoal extends Goal {
    private final Goal inner;
    private final Supplier<CatAction> routineSupplier;
    private final CatAction activeFor;

    public ConditionalGoal(Goal inner, CatAction activeFor, Supplier<CatAction> routineSupplier) {
        this.inner = inner;
        this.activeFor = activeFor;
        this.routineSupplier = routineSupplier;
    }

    @Override
    public boolean canStart() {
        return routineSupplier.get() == activeFor && inner.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return routineSupplier.get() == activeFor && inner.shouldContinue();
    }

    @Override
    public void start() {
        inner.start();
    }

    @Override
    public void stop() {
        inner.stop();
    }

    @Override
    public void tick() {
        inner.tick();
    }
}
