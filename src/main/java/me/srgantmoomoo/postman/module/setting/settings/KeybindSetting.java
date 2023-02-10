package me.srgantmoomoo.postman.module.setting.settings;

import me.srgantmoomoo.postman.module.setting.Setting;

public class KeybindSetting extends Setting {
    private int key;

    public KeybindSetting(int key) { // no parent.
        setName("keybind");
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
