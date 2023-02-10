package me.srgantmoomoo.postman.module.setting.settings;

import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

public class BooleanSetting extends Setting {
    private boolean enabled;

    public BooleanSetting(String name, Module parent, boolean enabled) {
        setName(name);
        setParent(parent);
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}
