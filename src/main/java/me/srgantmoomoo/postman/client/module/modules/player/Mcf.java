package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.client.friend.FriendManager;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class Mcf extends Module {
	
	public Mcf() {
		super("mcf", "middle click a player to friend them.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
	private final Listener<InputEvent.MouseInputEvent> listener = new Listener<>(event -> {
		if (mc.objectMouseOver.typeOfHit.equals(RayTraceResult.Type.ENTITY) && mc.objectMouseOver.entityHit instanceof EntityPlayer && Mouse.isButtonDown(2)) {
			if (FriendManager.isFriend(mc.objectMouseOver.entityHit.getName())) {
				FriendManager.removeFriend(mc.objectMouseOver.entityHit.getName());
				ModuleManager.addChatMessage("removed friend: " + mc.objectMouseOver.entityHit.getName());	
			}else {
				FriendManager.addFriend(mc.objectMouseOver.entityHit.getName());
				ModuleManager.addChatMessage("added friend: " + mc.objectMouseOver.entityHit.getName());
			}
		}
	});

}