package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;
import net.minecraft.entity.Entity;

public class AddEntityEvent extends Event {

    private Entity entity;

    public AddEntityEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}