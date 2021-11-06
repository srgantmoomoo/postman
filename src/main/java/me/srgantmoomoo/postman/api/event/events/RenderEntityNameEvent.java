package me.srgantmoomoo.postman.api.event.events;

import me.srgantmoomoo.postman.api.event.Event;
import net.minecraft.client.entity.AbstractClientPlayer;

public class RenderEntityNameEvent extends Event {
	
	public final AbstractClientPlayer Entity;
    public double X;
    public double Y;
    public double Z;
    public final String Name;
    public final double DistanceSq;

    public RenderEntityNameEvent(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq) {
        Entity = entityIn;
        x = X;
        y = Y;
        z = Z;
        Name = name;
        DistanceSq = distanceSq;
    }

}
