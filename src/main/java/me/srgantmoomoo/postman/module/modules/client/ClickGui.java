package me.srgantmoomoo.postman.module.modules.client;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {
    public ClickGui() {
        super("clickGui", "click clack.", Category.CLIENT, GLFW.GLFW_KEY_RIGHT_SHIFT);
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
}
