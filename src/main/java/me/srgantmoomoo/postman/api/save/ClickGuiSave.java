package me.srgantmoomoo.postman.api.save;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.client.ui.clickgui.back.ClickGuiConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Hoosiers
 * @since 10/15/2020
 */

public class ClickGuiSave {

    public ClickGuiSave() {
        try {
        	clickGuiSave();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String fileName = "postman/";
    final String mainName = "clickGui/";

    public void clickGuiSave() throws IOException {
        if (!Files.exists(Paths.get(fileName))) {
            Files.createDirectories(Paths.get(fileName));
        }
        if (!Files.exists(Paths.get(fileName + mainName))) {
            Files.createDirectories(Paths.get(fileName + mainName));
        }
    }

    public void registerFiles(String location, String name) throws IOException {
        if (!Files.exists(Paths.get(fileName + location + name + ".json"))) {
            Files.createFile(Paths.get(fileName + location + name + ".json"));
        }
        else {
            File file = new File(fileName + location + name + ".json");

            file.delete();

            Files.createFile(Paths.get(fileName + location +name + ".json"));
        }
    }

    public void saveClickGUIPositions() throws IOException {
        registerFiles(mainName, "ClickGUI");
		Main.clickGui.gui.saveConfig(new ClickGuiConfig(fileName+mainName));
    }
}