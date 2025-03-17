package me.srgantmoomoo.postman.module.setting.settings;

import com.lukflug.panelstudio.theme.ITheme;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

import java.awt.Color;

public class ColorSetting extends Setting {
    private Color value;
    private boolean rainbow;

    public ColorSetting(String name, Module parent, Color value, boolean rainbow) {
        setName(name);
        setParent(parent);
        this.value = value;
        this.rainbow = rainbow;
    }

    public int toInteger() {
        return this.value.getRGB() & (0xFFFFFFFF);
    }

    public void fromInteger (long number) {
        this.value = new Color(Math.toIntExact(number & 0xFFFFFFFF),true);
    }

    public Color getValue() {
        if (rainbow) {
            int speed=10; //TODO speed
            return ITheme.combineColors(Color.getHSBColor((System.currentTimeMillis()%(360*speed))/(float)(360*speed),1,1),value);
        }
        else return value;
    }

    public void setValue(Color value) {
        this.value = value;

        Main.INSTANCE.save();
    }

    public boolean getRainbow() {
        return rainbow;
    }

    public void setRainbow (boolean rainbow) {
        this.rainbow=rainbow;

        Main.INSTANCE.save();
    }
}
