package me.srgantmoomoo.postman.settings;

import com.lukflug.panelstudio.theme.Renderer;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class KeybindSetting extends Setting {
	
	public int code;
	
	public KeybindSetting(int code) {
		this.name = "KeyBind";
		this.code = code;
	}
	
	public KeybindSetting(Renderer componentRenderer, Module module) {
		// TODO Auto-generated constructor stub
	}

	public int getKeyCode() {
		return this.code;
	}
	
	public void setKeyCode(int code) {
		this.code = code;
	}

}
