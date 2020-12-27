package me.srgantmoomoo.postman.save;

import java.io.IOException;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.ui.clickgui.ClickGuiConfig;

public class ClickGuiLoad {
	
	  public ClickGuiLoad() {
	        try {
	            loadConfig();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  String fileName = "postman/";
	  String mainName = "click/";
	  public void loadConfig() throws IOException {
	        loadClickGUIPositions();
	    }
	  
	  public void loadClickGUIPositions() throws IOException {
			Main.getInstance().clickGui.gui.loadConfig(new ClickGuiConfig(fileName+mainName));
	    }


}
