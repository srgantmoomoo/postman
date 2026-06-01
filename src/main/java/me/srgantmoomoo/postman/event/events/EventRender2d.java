package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;
import net.minecraft.client.gui.DrawContext;

// posted in MixinInGameHud
public class EventRender2d extends Event<EventRender2d> {
    public DrawContext context;

    public EventRender2d(DrawContext context) {
        this.context = context;
    }
}