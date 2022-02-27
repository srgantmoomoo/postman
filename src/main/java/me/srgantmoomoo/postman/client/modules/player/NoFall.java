package me.srgantmoomoo.postman.client.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.NetworkPacketEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Module {
	
	public NoFall() {
		super ("noFall", "yea no... fall.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@EventHandler
	private final Listener<NetworkPacketEvent> listener = new Listener<>(event -> {
		if (event.getPacket() instanceof CPacketPlayer) {
			final CPacketPlayer packet = (CPacketPlayer) event.getPacket();
			if (event.getPacket() instanceof CPacketPlayer && Minecraft.getMinecraft().player.fallDistance >= 3.0f) {
				packet.onGround = true;
			}
		}
	});
}