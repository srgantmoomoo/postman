package me.srgantmoomoo.postman.backend.event;

import me.srgantmoomoo.postman.backend.util.Wrapper;
import me.zero.alpine.type.Cancellable;

public class Event extends Cancellable {
	
	private Era era = Era.PRE;
    private final float partialTicks;

    public Event() {
        this.partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
    }
    
    public Event(Era era) {
        this.partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
        this.era = era;
    }

    public Era getEra() {
        return era;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
    
    public enum Era {
        PRE,
        PERI,
        POST
    }
}