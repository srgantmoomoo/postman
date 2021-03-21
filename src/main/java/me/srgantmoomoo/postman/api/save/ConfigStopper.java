package me.srgantmoomoo.postman.api.save;

import java.io.IOException;

import me.srgantmoomoo.Main;

public class ConfigStopper extends Thread {

    @Override
    public void run() {
        saveConfig();
    }

    public static void saveConfig() {
        try {
            Main.clickGuiSave.clickGuiSave();
            Main.clickGuiSave.saveClickGUIPositions();
            Main.log.info("saved config.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}