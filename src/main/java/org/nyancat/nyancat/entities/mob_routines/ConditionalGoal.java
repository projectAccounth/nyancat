package org.nyancat.nyancat.entities.mob_routines;

import java.util.function.Supplier;

import net.minecraft.entity.ai.goal.Goal;

public class ConditionalGoal<T> extends Goal {
    private final Goal inner;
    private final Supplier<T> routineSupplier;
    private final T activeFor;

    public ConditionalGoal(Goal inner, T activeFor, Supplier<T> routineSupplier) {
        this.inner = inner;
        this.routineSupplier = routineSupplier;
        this.activeFor = activeFor;
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
