package me.srgantmoomoo.postman.module.setting.settings;

import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {
    private int index;
    private List<String> modes;

    public ModeSetting(String name, Module parent, String defaultMode, String... modes) {
        setName(name);
        setParent(parent);
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }

    public String getMode() {
        return this.modes.get(this.index);
    }

    public void setMode(String mode) {
        this.index = this.modes.indexOf(mode);
    }

    public boolean is(String mode) {
        return (this.index == this.modes.indexOf(mode));
    }

    public void cycle() {
        if(this.index < this.modes.size() - 1)
            index++;
        else
            this.index = 0;
    }
}
