package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;

public class AutoMine extends Module {
    public AutoMine() {
        super("autoMine", "automatically mines.", Category.PLAYER, 0);
    }

    @Override
    public void onEvent(Event e) {
        if(!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null) return;

        if(mc.currentScreen == null) {
            mc.options.attackKey.setPressed(true);
        } else {
            mc.interactionManager.attackBlock(mc.player.getBlockPos(), mc.player.getHorizontalFacing());
        }
    }

    @Override
    public void onDisable() {
        MinecraftClient.getInstance().options.attackKey.setPressed(false);
    }
}
