package me.srgantmoomoo.postman.module.modules;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class ClickGuiModule extends Module {
    public ModeSetting theme = new ModeSetting("theme", this, "impact", "clear", "gameSense", "rainbow", "windows31", "impact");
    public NumberSetting animationSpeed = new NumberSetting("animationSpeed", this, 10, 0, 100, 1);

    public ClickGuiModule() {
        super("clickGuiModule", "kms.", Category.CLIENT, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void onEnable() {
        Main.INSTANCE.clickGui.enterGUI();
        System.out.println("hi");
    }

    @Override
    public void onDisable() {
        Main.INSTANCE.clickGui.exitGUI();
        System.out.println("bye");
    }
}
