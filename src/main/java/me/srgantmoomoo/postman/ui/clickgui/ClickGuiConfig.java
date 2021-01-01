package me.srgantmoomoo.postman.ui.clickgui;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lukflug.panelstudio.ConfigList;
import com.lukflug.panelstudio.PanelConfig;

import net.minecraft.client.Minecraft;
/*
 * ok, literally just skidded from gs atm, im v tired... will come back to this wen redoing clickgui... @SrgantMooMoo 12/16/2020 1:55am 0_0
 */
public class ClickGuiConfig implements ConfigList {
	private JsonObject panelObject=null;
	private File dir;
	private File dataFile;
	int currentTab;
	
	public ClickGuiConfig(File dataFile) {
		this.dataFile=dataFile;
	}
	
	@Override
	public void begin(boolean loading) {
		if (loading) {
	        dir = new File(Minecraft.getMinecraft().gameDir, "postman");
			if(!dir.exists()) {
			}
			dataFile = new File(dir, "clickgui.txt");
			if(!dataFile.exists()) {
				try {
					dataFile.createNewFile();
				} catch (IOException e) {e.printStackTrace();}
			}
	        
		}
	}

	@Override
	public void end(boolean loading) {
		ArrayList<String> lines = new ArrayList<String>();
		
		if (panelObject==null) return;
		if (!loading) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
			String line = reader.readLine();
			while(line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
		panelObject = null;
	}

	@Override
	public PanelConfig addPanel(String title) {
		if (panelObject==null) return null;
        JsonObject valueObject = new JsonObject();
        panelObject.add(title,valueObject);
        return new JPanelConfig(valueObject);
	}

	@Override
	public PanelConfig getPanel(String title) {
		if (panelObject==null) return null;
		JsonElement configObject = panelObject.get(title);
		if (configObject!=null && configObject.isJsonObject()) return new JPanelConfig(configObject.getAsJsonObject());
		return null;
	}
	
	
	private static class JPanelConfig implements PanelConfig {
		private final JsonObject configObject;
		
		public JPanelConfig (JsonObject configObject) {
			this.configObject=configObject;
		}
		
		@Override
		public void savePositon(Point position) {
			configObject.add("PosX", new JsonPrimitive(position.x));
			configObject.add("PosY", new JsonPrimitive(position.y));
		}

		@Override
		public Point loadPosition() {
			Point point=new Point();
			JsonElement panelPosXObject = configObject.get("PosX");
			if (panelPosXObject != null && panelPosXObject.isJsonPrimitive()){
			    point.x=panelPosXObject.getAsInt();
			} else return null;
			JsonElement panelPosYObject = configObject.get("PosY");
			if (panelPosYObject != null && panelPosYObject.isJsonPrimitive()){
			    point.y=panelPosYObject.getAsInt();
			} else return null;
			return point;
		}

		@Override
		public void saveState(boolean state) {
			configObject.add("State",new JsonPrimitive(state));
		}

		@Override
		public boolean loadState() {
			JsonElement panelOpenObject = configObject.get("State");
			if (panelOpenObject != null && panelOpenObject.isJsonPrimitive()){
			    return panelOpenObject.getAsBoolean();
			}
			return false;
		}
	}
}

