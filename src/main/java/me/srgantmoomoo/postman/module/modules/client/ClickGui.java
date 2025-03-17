package me.srgantmoomoo.postman.module.modules.client;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventGuiKeyPress;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
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
