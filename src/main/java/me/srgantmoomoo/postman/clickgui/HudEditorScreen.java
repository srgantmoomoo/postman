package me.srgantmoomoo.postman.clickgui;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class HudEditorScreen extends Screen {
    Setting background = Main.INSTANCE.moduleManager.getModuleByName("clickGui").getSettingByName("background");
    Setting pauseGame = Main.INSTANCE.moduleManager.getModuleByName("clickGui").getSettingByName("pauseGame");

    public HudEditorScreen() {
        super(Text.literal("hudEditor"));
    }

    //private final ManagedShaderEffect blur = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/blur" + ".json"));
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if(((BooleanSetting) background).isEnabled())
            this.renderBackground(context, mouseX, mouseY, delta);

        // pulls the rendering for each individual hud module
        for(HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.drawDraggable(context, mouseX, mouseY);
        }

        // handles hud module dragging
        for(HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.getDraggableComponent().draw(context, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.getDraggableComponent().mouseClicked(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.getDraggableComponent().mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            Main.INSTANCE.moduleManager.getModuleByName("hudEditor").disable();
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldPause() {
        return ((BooleanSetting) this.pauseGame).isEnabled();
    }
}
