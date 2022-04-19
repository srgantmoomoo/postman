package me.srgantmoomoo.postman.impl.clickgui.front;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class HudEditor extends Module {
    private static final ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");
    public BooleanSetting exitToClickGui = new BooleanSetting("exitToClickGui", this, true);

    public HudEditor() {
        super("hudEditor", "", Keyboard.KEY_NONE, Category.HUD);
        this.addSettings(exitToClickGui);
    }

    @Override
    public void onEnable() {
        Main.INSTANCE.clickGui.enterHUDEditor();
        if (ClickGuiModule.INSTANCE.blur.isEnabled()) mc.entityRenderer.loadShader(shader);
    }

    @Override
    public void onUpdate() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if (exitToClickGui.isEnabled()) {
                this.disable();
                Main.INSTANCE.clickGui.enterGUI();
            } else {
                this.disable();
            }
        }
    }

    @Override
    public void onDisable() {
        mc.entityRenderer.getShaderGroup().deleteShaderGroup();
    }
}
