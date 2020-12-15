package me.srgantmoomoo.postman.settings;

import me.srgantmoomoo.postman.Main;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class KeybindSetting extends Setting {
	
	public int code;
	
	public KeybindSetting(int code) {
		this.name = "KeyBind";
		this.code = code;
	}
	
	public int getKeyCode() {
		return this.code;
	}
	
	public void setKeyCode(int code) {
		this.code = code;
	}

}
