package me.srgantmoomoo.postman.client.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class Peek extends Module {
	
	//look in me.srgantmoomoo.api.mixin.mixins.MixinGuiScreen
	public Peek() {
		super ("peek", "shows preview of wuts in a shulker.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
