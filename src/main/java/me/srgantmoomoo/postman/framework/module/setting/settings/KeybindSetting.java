package me.srgantmoomoo.postman.framework.module.setting.settings;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.setting.Setting;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class KeybindSetting extends Setting implements com.lukflug.panelstudio.settings.KeybindSetting {
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

	@Override
	public int getKey() {
		return code;
	}

	@Override
	public String getKeyName() {
		return Keyboard.getKeyName(code);
	}

	@Override
	public void setKey(int key) {
		code=key;
	}
}
