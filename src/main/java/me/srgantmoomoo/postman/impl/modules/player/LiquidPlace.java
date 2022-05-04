package me.srgantmoomoo.postman.impl.modules.player;

import me.srgantmoomoo.postman.backend.event.Event;
import me.srgantmoomoo.postman.backend.event.events.CanCollideCheckEvent;
import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import org.lwjgl.input.Keyboard;

public class LiquidPlace extends Module {

    @EventHandler
    private final Listener<CanCollideCheckEvent> CanCollid = new Listener<>(Event::cancel);

    public LiquidPlace() {
        super("liquidPlace", "lets u place blocks on liquid.", Keyboard.KEY_NONE, Category.PLAYER);
    }
}
