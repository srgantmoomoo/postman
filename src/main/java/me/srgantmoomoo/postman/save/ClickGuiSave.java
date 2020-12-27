package me.srgantmoomoo.postman.save;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.ui.clickgui.ClickGuiConfig;

public class ClickGuiSave {
	
	// SAVE ------
		public ClickGuiSave() {
			  try {
		            saveConfig();
		        }
		        catch (IOException e) {
		            e.printStackTrace();
		        }
		}
		
		 public static final String fileName = "postman/";
		    String mainName = "click/";
		    
		 public void saveConfig() throws IOException {
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

		            Files.createFile(Paths.get(fileName + location + name + ".json"));
		        }
		    }
		 
			public void saveClickGUIPositions() throws IOException {
		        registerFiles(mainName, "ClickGui");
				Main.getInstance().clickGui.gui.saveConfig(new ClickGuiConfig(fileName+mainName));
		    }
}
