package me.srgantmoomoo.postman.backend.saveload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.impl.clickgui.back.ClickGuiConfig;

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
    String mainName = "clickGui/";

    public void clickGuiSave() throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        if (!Files.exists(Paths.get(fileName + mainName))) {
            Files.createDirectories(Paths.get(fileName + mainName));
        }
    }

    public void registerFiles(String location, String name) throws IOException {
        Path path = Paths.get(fileName + location + name + ".json");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        else {
            File file = new File(fileName + location + name + ".json");

            file.delete();

            Files.createFile(path);
        }
    }

    public void saveClickGUIPositions() throws IOException {
        registerFiles(mainName, "ClickGUI");
		Main.INSTANCE.clickGui.gui.saveConfig(new ClickGuiConfig(fileName+mainName));
    }
}