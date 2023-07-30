package me.srgantmoomoo.postman.module.modules;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class Example extends Module {
    public BooleanSetting booleanSetting = new BooleanSetting("booleanSetting", this, false);
    public NumberSetting numberSetting = new NumberSetting("numberSetting", this, 5, 1, 10, 1);
    public ModeSetting modeSetting = new ModeSetting("modeSetting", this, "mode1", "mode1", "mode2", "mode3", "sickomode");
    public ColorSetting colorSetting = new ColorSetting("colorSetting", this, new Color(255, 255, 255, 255), false);

    public Example() {
        super("example", "kms.", Category.CLIENT, GLFW.GLFW_KEY_Y);
        this.addSettings(booleanSetting, numberSetting, modeSetting, colorSetting);
    }

    @Override
    public void onEnable() {
        System.out.println("hiiiiii.");
    }

    @Override
    public void onDisable() {
        System.out.println("byeeeee.");
    }

}
