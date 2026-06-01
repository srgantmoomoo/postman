package me.srgantmoomoo.postman.module.modules.client;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ClickGui extends Module {
    public ColorSetting categoryColor = new ColorSetting("categoryColor", this, new Color(121, 193, 255, 255), false);
    public ColorSetting componentColor = new ColorSetting("componentColor", this, new Color(0, 0, 0, 150), false);
    public ColorSetting settingColor = new ColorSetting("settingColor", this, new Color(0, 255, 0, 255), false);
    public BooleanSetting background = new BooleanSetting("background", this, true);
    public BooleanSetting pauseGame = new BooleanSetting("pauseGame", this, false);

    public ClickGui() {
        super("clickGui", "click clack.", Category.CLIENT, GLFW.GLFW_KEY_RIGHT_SHIFT);
        this.addSettings(categoryColor, componentColor, settingColor, background, pauseGame);
    }

    @Override
    public void onEnable() {
        MinecraftClient.getInstance().setScreen(new ClickGuiScreen());
        Main.INSTANCE.load.loadGui();
    }

    @Override
    public void onDisable() {
        Main.INSTANCE.save.saveGui();
        if(MinecraftClient.getInstance().currentScreen instanceof ClickGuiScreen)
            MinecraftClient.getInstance().setScreen(null);
    }
}
