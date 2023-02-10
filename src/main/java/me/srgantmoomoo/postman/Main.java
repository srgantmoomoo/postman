package me.srgantmoomoo.postman;

import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.setting.SettingManager;
import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

//soir
public class Main implements ModInitializer {
    int strong;
    int postman = strong;

    private static Logger logger;

    public final String MODID = "postman";
    public final String NAME = "postman";
    public final String VERSION = "4.0";

    public static Main INSTANCE;

    public Main() {
        INSTANCE = this;
    }

    public ModuleManager moduleManager;
    public SettingManager settingManager;

    @Override
    public void onInitialize() {
        moduleManager = new ModuleManager();
        settingManager = new SettingManager();
    }
}
