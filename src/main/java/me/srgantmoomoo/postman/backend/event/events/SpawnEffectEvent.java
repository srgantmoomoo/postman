package me.srgantmoomoo.postman.backend.event.events;

import me.srgantmoomoo.postman.backend.event.Event;

public class SpawnEffectEvent extends Event {
    private final int particleID;

    public SpawnEffectEvent(int particleID) {
        this.particleID = particleID;
    }

    public int getParticleID() {
        return particleID;
    }
}