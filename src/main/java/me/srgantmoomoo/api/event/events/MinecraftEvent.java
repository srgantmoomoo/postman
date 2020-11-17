package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.util.Wrapper;
import me.zero.alpine.type.Cancellable;

public class MinecraftEvent extends Cancellable
{
    private Era era = Era.PRE;
    private final float partialTicks;

    public MinecraftEvent()
    {
        partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
    }
    
    public MinecraftEvent(Era p_Era)
    {
        partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
        era = p_Era;
    }

    public Era getEra()
    {
        return era;
    }

    public float getPartialTicks()
    {
        return partialTicks;
    }
    
    public enum Era
    {
        PRE,
        PERI,
        POST
    }

}