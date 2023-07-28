package me.srgantmoomoo.postman.module.setting.settings;

import com.lukflug.panelstudio.setting.IBooleanSetting;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

public class BooleanSetting extends Setting implements IBooleanSetting {
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

        if(Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.saveSettings();
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void toggle() {
        setEnabled(!isEnabled());
    }

    @Override
    public boolean isOn() {
        return isEnabled();
    }
}
