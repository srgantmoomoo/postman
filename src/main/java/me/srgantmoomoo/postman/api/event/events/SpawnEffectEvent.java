package me.srgantmoomoo.postman.api.event.events;

import me.srgantmoomoo.postman.api.event.Event;

public class SpawnEffectEvent extends Event {

    private int particleID;

    public SpawnEffectEvent(int particleID) {
        this.particleID = particleID;
    }

    public int getParticleID() {
        return particleID;
    }

    public void setParticleID(int particleID) {
        this.particleID = particleID;
    }
}