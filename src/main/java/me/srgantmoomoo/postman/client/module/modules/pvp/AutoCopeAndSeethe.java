package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoCopeAndSeethe extends Module {
	
	public AutoCopeAndSeethe() {
		super("autoCopeAndSeethe", "penis yes noa.", Keyboard.KEY_NONE, Category.PVP);
	}
	
	public void onEnable() {
		super.onEnable();
			if (mc.player != null)
				mc.player.sendChatMessage("> @player here's a helpful tutorial on how to cope and seethe: https://www.youtube.com/watch?v=4t5AKrZu_KE");
			disable();
		}

}
