package me.srgantmoomoo.postman.module.modules;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventKeyPress;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ClickGuiModule extends Module {
    public ModeSetting theme = new ModeSetting("theme", this, "impact", "clear", "gameSense", "rainbow", "windows31", "impact");
    public NumberSetting animationSpeed = new NumberSetting("animationSpeed", this, 10, 0, 100, 1);

    public ClickGuiModule() {
        super("clickGui", "kms.", Category.CLIENT, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
    
    @Override
    public void onEvent(Event e) {
        if(e instanceof EventKeyPress) {
            if(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE))
                disable();
        }
    }
    
    @Override
    public void onEnable() {
        Main.INSTANCE.clickGui.enterGUI();
    }

    @Override
    public void onDisable() {
        Main.INSTANCE.clickGui.exitGUI();
    }
}
