package me.srgantmoomoo.postman.client.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class AutoRespawn extends Module {
	
	public AutoRespawn() {
		super("autoRespawn", "automatically respawns after death occurs.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(mc.player.isDead) {
			mc.player.respawnPlayer();
		}
	}

}
