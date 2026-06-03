package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;

public class AutoRespawn extends Module {

    public AutoRespawn() {
        super("autoRespawn", "automatically respawns after death occurs.", Category.PLAYER, 0);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if (MinecraftClient.getInstance().player.isDead()) {
                MinecraftClient.getInstance().player.requestRespawn();
            }
        }
    }
}
