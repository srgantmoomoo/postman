package me.srgantmoomoo.postman.module.setting.settings;

import com.lukflug.panelstudio.setting.IEnumSetting;
import com.lukflug.panelstudio.setting.ILabeled;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting implements IEnumSetting {
    private int index;
    private List<String> modes;
    private final ILabeled[] array;

    public ModeSetting(String name, Module parent, String defaultMode, String... modes) {
        setName(name);
        setParent(parent);
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);

        array=Arrays.stream(modes).map(v->{
            return new ILabeled() {
                @Override
                public String getDisplayName() {
                    return v.toString();
                }
            };
        }).toArray(ILabeled[]::new);
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

    @Override
    public void increment() {
        if (this.index < this.modes.size() - 1) {
            this.index++;
        }else {
            this.index = 0;
        }
    }

    @Override
    public void decrement() {
        if (this.index > 0) {
            this.index--;
        }else {
            this.index = this.modes.size() - 1;
        }
    }

    @Override
    public String getValueName() {
        return this.modes.get(this.index);
    }

    @Override
    public int getValueIndex() {
        return this.index;
    }

    @Override
    public void setValueIndex(int index) {
        this.index = index;
    }

    @Override
    public ILabeled[] getAllowedValues() {
        return array;
    }
}
