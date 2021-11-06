package me.srgantmoomoo.postman.client.module.modules.render;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class Peek extends Module {
	
	//look in me.srgantmoomoo.api.mixin.mixins.MixinGuiScreen
	public Peek() {
		super ("peek", "shows preview of wuts in a shulker.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
