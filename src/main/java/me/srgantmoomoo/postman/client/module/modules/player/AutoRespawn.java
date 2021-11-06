package me.srgantmoomoo.postman.client.module.modules.player;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

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
