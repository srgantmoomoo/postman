package me.srgantmoomoo.postman.backend.saveload;

import java.io.IOException;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.impl.clickgui.back.ClickGuiConfig;

/**
 * @author Hoosiers
 * @since 10/15/2020
 */

public class ClickGuiLoad {

    public ClickGuiLoad() {
        try {
        	clickGuiLoad();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    String fileName = "postman/";
    String mainName = "clickGui/";

    public void clickGuiLoad() throws IOException {
        loadClickGUIPositions();
    }

    public void loadClickGUIPositions() throws IOException {
		Main.INSTANCE.clickGui.gui.loadConfig(new ClickGuiConfig(fileName+mainName));
    }
}