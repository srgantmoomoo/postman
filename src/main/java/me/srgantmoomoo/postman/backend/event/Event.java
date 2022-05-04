package me.srgantmoomoo.postman.backend.event;

import me.srgantmoomoo.postman.backend.util.Wrapper;

public class Event {
	
	private Era era = Era.PRE;
    private final float partialTicks;
    private boolean cancelled = false;

    public Event() {
        partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
    }
    
    public Event(Era era) {
        partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
        this.era = era;
    }

    public Era getEra() {
        return era;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
    
    public enum Era {
        PRE,
        PERI,
        POST
    }
}