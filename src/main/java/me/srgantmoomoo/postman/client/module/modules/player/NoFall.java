package me.srgantmoomoo.postman.client.module.modules.player;

import me.srgantmoomoo.postman.api.event.events.NetworkPacketEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import org.lwjgl.input.Keyboard;

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