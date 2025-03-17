package me.srgantmoomoo.postman.module.modules.client;

import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventGuiKeyPress;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ClickGui extends Module {
    public ColorSetting categoryColor = new ColorSetting("categoryColor", this, new Color(121, 193, 255, 255), false);
    public ColorSetting moduleColor = new ColorSetting("moduleColor", this, new Color(0, 0, 0, 150), false);

    public ClickGui() {
        super("clickGui", "click clack.", Category.CLIENT, GLFW.GLFW_KEY_RIGHT_SHIFT);
        this.addSettings(categoryColor, moduleColor);
    }

    @Override
    public void onEnable() {
        MinecraftClient.getInstance().setScreen(new ClickGuiScreen());
        //Main.INSTANCE.load.loadGui(); // gui
    }

    @Override
    public void onDisable() {
        //Main.INSTANCE.save.saveGui(); // gui
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventGuiKeyPress) {
            if(((EventGuiKeyPress) e).getKey() == GLFW.GLFW_KEY_ESCAPE)
                this.disable();
            /* something like this
            if(((EventGuiKeyPress) e)..getKey() == this.getKey()) {
                //MinecraftClient.getInstance().setScreen(Screen);
                MinecraftClient.getInstance().player.closeScreen();
                this.disable();
            }*/
        }
    }
}
