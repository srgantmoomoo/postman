package me.srgantmoomoo.postman.framework.module.setting.settings;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.setting.Setting;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class KeybindSetting extends Setting implements com.lukflug.panelstudio.settings.KeybindSetting {
	private int key;
	
	public KeybindSetting(int key) {
		this.name = "KeyBind";
		this.key = key;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public String getKeyName() {
		return Keyboard.getKeyName(key);
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}
}
