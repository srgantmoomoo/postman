package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.event.Event;
import me.srgantmoomoo.api.util.world.Location;

public class JumpEvent extends Event {

	private Location location;

	public JumpEvent(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}