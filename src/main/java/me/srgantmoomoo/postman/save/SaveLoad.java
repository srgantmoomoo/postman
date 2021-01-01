package me.srgantmoomoo.postman.save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.ColorSetting;
import me.srgantmoomoo.postman.settings.ModeSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import me.srgantmoomoo.postman.settings.Setting;
import me.srgantmoomoo.postman.settings.SettingsManager;
import me.srgantmoomoo.postman.ui.clickgui.ClickGuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiConfig;

/*
 * Written by @SrgantMooMoo on 11/30/20 with inspiration taken from @SebSb.
 */

public class SaveLoad {

	private File dir;
	private File dataFile;
	int currentTab;
	   
	public SaveLoad() {
		dir = new File(Minecraft.getMinecraft().gameDir, "postman");
		if(!dir.exists()) {
			dir.mkdir();
		}
		dataFile = new File(dir, "config.txt");
		if(!dataFile.exists()) {
			try {
				dataFile.createNewFile();
			} catch (IOException e) {e.printStackTrace();}
		}
		
		this.load();
	}
	 
	public void save() {
		ArrayList<String> toSave = new ArrayList<String>();
		
		for(Module mod : ModuleManager.modules) {
			if(!mod.getName().equals("tabGui"))
			toSave.add("MOD:" + mod.getName() + ":" + mod.isToggled() + ":" + mod.getKey());
		}
		
		for(Module mod : ModuleManager.modules) {
		for(Setting setting : mod.settings) {
			
			if(setting instanceof BooleanSetting) {
				BooleanSetting bool = (BooleanSetting) setting;
				toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + bool.isEnabled());
				}
			
			if(setting instanceof NumberSetting) {
				NumberSetting numb = (NumberSetting) setting;
				toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + numb.getValue());
			}
			
			if(setting instanceof ModeSetting) {
				ModeSetting mode = (ModeSetting) setting;
				toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + mode.getMode());
			}
			
			if(setting instanceof ColorSetting) {
				ColorSetting color = (ColorSetting) setting;
				toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + color.toInteger());
			}
			}
		} 
		
		try {
			PrintWriter pw = new PrintWriter(this.dataFile);
			for(String str : toSave) {
				pw.println(str);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		ArrayList<String> lines = new ArrayList<String>();
		
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
		
		for(String s : lines) {
			String[] args = s.split(":");
			if(s.toLowerCase().startsWith("mod:")) {
				Module m = Main.moduleManager.getModule(args[1]);
				if(m != null) {
					m.setToggled(Boolean.parseBoolean(args[2]));
					m.setKey(Integer.parseInt(args[3]));
				}
			}else if(s.toLowerCase().startsWith("set:")) {
				Module m = Main.moduleManager.getModule(args[1]);
				if(m != null) {
					Setting setting = Main.settingsManager.getSettingByName(m,args[2]);
					if(setting != null) {
						if(setting instanceof BooleanSetting) {
						((BooleanSetting)setting).setEnabled(Boolean.parseBoolean(args[3]));
					}
						if(setting instanceof NumberSetting) {
						((NumberSetting)setting).setValue(Double.parseDouble(args[3]));
					}
						if(setting instanceof ModeSetting) {
						((ModeSetting)setting).setMode(args[3]);
					}
						if(setting instanceof ColorSetting) {
						((ColorSetting)setting).fromInteger(Integer.parseInt(args[3]));
						}
					}
				}
			}
		}
	}
	public void saveClickGUIPositions() throws IOException {
		dataFile = new File(dir, "clickgui.txt");
		Main.getInstance().clickGui.gui.saveConfig(new ClickGuiConfig(dataFile));
    }
}
