package me.srgantmoomoo.postman.impl.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class Peek extends Module {
	public static final Peek INSTANCE = new Peek();

	//look in me.srgantmoomoo.api.mixin.mixins.MixinGuiScreen
	protected Peek() {
		super("peek", "shows preview of wuts in a shulker.", Keyboard.KEY_NONE, Category.RENDER);
	}
}
