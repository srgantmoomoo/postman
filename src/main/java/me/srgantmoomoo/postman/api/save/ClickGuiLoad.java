package me.srgantmoomoo.postman.api.save;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.client.ui.clickgui.back.ClickGuiConfig;

import java.io.IOException;

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
		Main.clickGui.gui.loadConfig(new ClickGuiConfig(fileName+mainName));
    }
}