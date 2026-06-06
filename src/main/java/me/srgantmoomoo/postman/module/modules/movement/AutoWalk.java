package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;

public class AutoWalk extends Module {

    public AutoWalk() {
        super("autoWalk", "automatically walks for u, u lazy fuck.", Category.MOVEMENT, 0);
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null)
            mc.options.forwardKey.setPressed(false);
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventTick) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player != null && mc.currentScreen == null)
                mc.options.forwardKey.setPressed(true);
        }
    }
}
