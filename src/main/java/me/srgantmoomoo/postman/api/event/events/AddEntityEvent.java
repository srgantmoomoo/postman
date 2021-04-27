package me.srgantmoomoo.postman.api.event.events;

import me.srgantmoomoo.postman.api.event.Event;
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