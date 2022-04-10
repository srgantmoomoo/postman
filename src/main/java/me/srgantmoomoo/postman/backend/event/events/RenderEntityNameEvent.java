package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;
import net.minecraft.client.entity.AbstractClientPlayer;

public class RenderEntityNameEvent extends Event {
	public AbstractClientPlayer entity;
    public double x, y, z;
    public String name;
    public double distanceSq;

    public RenderEntityNameEvent(AbstractClientPlayer entity, double x, double y, double z, String name, double distanceSq) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        this.distanceSq = distanceSq;
    }
}
