package me.srgantmoomoo.postman.impl.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class Capes extends Module {
	public Capes() {
		super("capes", "allows u to see ur and others postman capes.", Keyboard.KEY_NONE, Category.CLIENT);
	}
}
