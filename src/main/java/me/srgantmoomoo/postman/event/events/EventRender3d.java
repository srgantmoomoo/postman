package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class EventRender3d extends Event<EventRender3d> {
    private final float partialTicks;
    private final MatrixStack matrixStack;

    public EventRender3d(float partialTicks, MatrixStack matrixStack) {
        this.partialTicks = partialTicks;
        this.matrixStack = matrixStack;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public MatrixStack getMatrixStack() {
        return matrixStack;
    }
}
