package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;

public class RenderEvent extends Event {

	private final float partialTicks;

	public RenderEvent(float partialTicks) {
		super();
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return this.partialTicks;
	}
}