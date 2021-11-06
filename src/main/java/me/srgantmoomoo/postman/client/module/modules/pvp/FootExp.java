package me.srgantmoomoo.postman.client.module.modules.pvp;

import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import org.lwjgl.input.Keyboard;

/*
 * I originally got this idea from olliem5, it was done in his "past" client. not sure where he got it, but that's where i got it :)
 */

public class FootExp extends Module {
	
	public FootExp() {
		super ("footExp", "automatically throws xp bottles downwards.", Keyboard.KEY_NONE, Category.PVP);
	}
	
	@EventHandler
	public Listener<PacketEvent.Send> listener = new Listener<>(event -> {
		if(event.getPacket() instanceof CPacketPlayerTryUseItem && mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle) {
			mc.player.connection.sendPacket(new CPacketPlayer.Rotation(mc.player.rotationYaw, 90.0f, mc.player.onGround));
		}
	});
}
