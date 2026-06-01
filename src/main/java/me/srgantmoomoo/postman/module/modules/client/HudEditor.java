package me.srgantmoomoo.postman.module.modules.client;

import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.clickgui.HudEditorScreen;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventGuiKeyPress;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class HudEditor extends Module {
    public HudEditor() {
        super("hudEditor", "enable hud editing screen.", Category.CLIENT, 0);
        this.addSettings();
    }

    @Override
    public void onEnable() {
        MinecraftClient.getInstance().setScreen(new HudEditorScreen());
        //Main.INSTANCE.load.loadHud();
    }

    @Override
    public void onDisable() {
        //Main.INSTANCE.save.saveHud();
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