package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    private boolean isSending = false;

    public NoFall() {
        super("noFall", "yea no... fall damage.", Category.PLAYER, 0);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if(mc.player == null) return;
            if(mc.player.getVelocity().y < -0.5 && !isSending) {
                isSending = true;
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true, false));
                isSending = false;
            }
        }
    }
}
