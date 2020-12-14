package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.event.Event;

public class RenderEvent extends Event {
	private final float partialTicks;

	public RenderEvent(float ticks){
		super();
		partialTicks = ticks;
	}

	public float getPartialTicks(){
		return partialTicks;
	}
}
