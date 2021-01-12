package me.srgantmoomoo.postman.api.save;

import java.io.IOException;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.ui.clickgui.ClickGuiConfig;

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
		Main.getInstance().clickGui.gui.loadConfig(new ClickGuiConfig(fileName+mainName));
    }
}