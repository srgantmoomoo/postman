package me.srgantmoomoo.postman.module.setting.settings;

import com.lukflug.panelstudio.setting.IColorSetting;
import com.lukflug.panelstudio.theme.ITheme;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

import java.awt.Color;

public class ColorSetting extends Setting implements IColorSetting {
    private Color value;
    private boolean rainbow;

    public ColorSetting(String name, Module parent, Color value, boolean rainbow) {
        setName(name);
        setParent(parent);
        this.value = value;
        this.rainbow = rainbow;
    }

    @Override
    public Color getValue() {
        if (rainbow) {
            int speed=10; //TODO speed
            return ITheme.combineColors(Color.getHSBColor((System.currentTimeMillis()%(360*speed))/(float)(360*speed),1,1),value);
        }
        else return value;
    }

    @Override
    public void setValue(Color value) {
        this.value = value;
    }

    @Override
    public Color getColor() {
        return getValue();
    }

    @Override
    public boolean getRainbow() {
        return rainbow;
    }

    @Override
    public void setRainbow (boolean rainbow) {
        this.rainbow=rainbow;
    }

    @Override
    public boolean hasAlpha() {
        return true;
    }

    @Override
    public boolean allowsRainbow() {
        return true;
    }

    @Override
    public boolean hasHSBModel() {
        return false;
        //return ClickGUIModule.colorModel.getValue()==ColorModel.HSB;
    }

}
