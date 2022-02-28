package me.srgantmoomoo.postman.impl.modules.bot;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class OffHandBot extends Module {
	
	public OffHandBot() {
		super("(wip) offHandBot", "a bot that better manages ur off hand.", Keyboard.KEY_NONE, Category.BOT);
	}

}
