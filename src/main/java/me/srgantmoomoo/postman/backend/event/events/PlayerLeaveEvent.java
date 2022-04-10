package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;

public class PlayerLeaveEvent extends Event {
	private final String name;

	public PlayerLeaveEvent(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}