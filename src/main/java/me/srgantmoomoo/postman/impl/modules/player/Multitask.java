package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class Multitask extends Module {
	public static final Multitask INSTANCE = new Multitask();

	private Multitask() {
		super("multitask", "allows you to do different things in each hand.", Keyboard.KEY_NONE, Category.PLAYER);
	}
}
