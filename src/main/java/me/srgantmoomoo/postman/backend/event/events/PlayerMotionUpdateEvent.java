package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;

public class PlayerMotionUpdateEvent extends Event {
    public PlayerMotionUpdateEvent(Era era) {
        super(era);
    }
}