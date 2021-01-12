package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class InventorySlots extends Module {
	
	public InventorySlots() {
		super ("inventorySlots", "shows ur armor values on top of hotbar", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	 @EventHandler
	 private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
		 if(toggled) {
			 if (event.getPacket() instanceof CPacketCloseWindow) {
		         final CPacketCloseWindow packet = (CPacketCloseWindow) event.getPacket();
		         if (packet.windowId == Minecraft.getMinecraft().player.inventoryContainer.windowId) {
		             event.cancel();
		         }
			 }
		 }
	 });
}