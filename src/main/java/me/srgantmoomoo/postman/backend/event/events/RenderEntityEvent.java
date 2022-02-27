package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;

public class RenderEntityEvent extends Event {
    private Entity entity;

    public RenderEntityEvent(Entity entityIn, ICamera camera, double camX, double camY, double camZ) {
        entity = entityIn;
    }

    public Entity getEntity() {
        return entity;
    }

}