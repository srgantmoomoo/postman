package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.event.GameSenseEvent;

public class RenderEvent extends GameSenseEvent {
	private final float partialTicks;

	public RenderEvent(float ticks){
		super();
		partialTicks = ticks;
	}

	public float getPartialTicks(){
		return partialTicks;
	}
}
