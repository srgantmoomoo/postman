package me.srgantmoomoo.postman.backend.saveload;

import java.io.IOException;

import me.srgantmoomoo.Main;

public class ConfigStopper extends Thread {

    @Override
    public void run() {
        saveConfig();
    }

    public static void saveConfig() {
        try {
            Main.INSTANCE.clickGuiSave.clickGuiSave();
            Main.INSTANCE.clickGuiSave.saveClickGUIPositions();
            Main.log.info("saved config.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}