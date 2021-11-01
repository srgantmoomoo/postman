package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoHut extends Module {
	
	public AutoHut() {
		super ("autoHut", "automatically builds hut for u.", Keyboard.KEY_NONE, Category.PVP);

		//recoir is revamping this as a base then i can code it in 
		//old code had 20+ errors lol 
