package me.srgantmoomoo.postman.module.modules.client;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.HudEditorScreen;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;

public class HudEditor extends Module {
    public BooleanSetting background = new BooleanSetting("background", this, true);
    public BooleanSetting pauseGame = new BooleanSetting("pauseGame", this, false);

    public HudEditor() {
        super("hudEditor", "enable hud editing screen.", Category.CLIENT, 0);
        this.addSettings();
    }

    @Override
    public void onEnable() {
        Main.INSTANCE.moduleManager.getModuleByName("clickGui").disable();
        MinecraftClient.getInstance().setScreen(new HudEditorScreen());
        Main.INSTANCE.load.loadHud();
    }

    @Override
    public void onDisable() {
        Main.INSTANCE.save.saveHud();
        if(MinecraftClient.getInstance().currentScreen instanceof HudEditorScreen)
            MinecraftClient.getInstance().setScreen(null);
    }
}