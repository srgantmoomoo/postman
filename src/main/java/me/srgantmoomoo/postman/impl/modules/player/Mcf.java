package me.srgantmoomoo.postman.impl.modules.player;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class Mcf extends Module {
	
	public Mcf() {
		super("mcf", "middle click a player to friend them.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@EventHandler
	private final Listener<InputEvent.MouseInputEvent> listener = new Listener<>(event -> {
		if (mc.objectMouseOver.typeOfHit.equals(RayTraceResult.Type.ENTITY) && mc.objectMouseOver.entityHit instanceof EntityPlayer && Mouse.isButtonDown(2)) {
			if (Main.INSTANCE.friendManager.isFriend(mc.objectMouseOver.entityHit.getName())) {
				Main.INSTANCE.friendManager.removeFriend(mc.objectMouseOver.entityHit.getName());
				Main.INSTANCE.commandManager.sendClientChatMessage("removed friend: " + mc.objectMouseOver.entityHit.getName(), true);
			}else {
				Main.INSTANCE.friendManager.addFriend(mc.objectMouseOver.entityHit.getName());
				Main.INSTANCE.commandManager.sendClientChatMessage("added friend: " + mc.objectMouseOver.entityHit.getName(), true);
			}
		}
	});

}
