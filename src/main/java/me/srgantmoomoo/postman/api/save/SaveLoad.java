package me.srgantmoomoo.postman.api.save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.Setting;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;

/*
 * Written by @SrgantMooMoo on 11/30/20 with inspiration taken from @SebSb.
 */

public class SaveLoad {

	private File dir;
	private File dataFile;
	   
	public SaveLoad() {
		dir = new File(Minecraft.getMinecraft().gameDir, Reference.NAME);
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
					if(m.getName().equals("clickGuiModule") && m.getName().equals("hudEditor"))
						m.setToggled(!Boolean.parseBoolean(args[2]));
					
					if(!m.getName().equals("clickGuiModule") && !m.getName().equals("hudEditor"))
					m.setToggled(Boolean.parseBoolean(args[2]));
					m.setKey(Integer.parseInt(args[3]));
				}
			}else if(s.toLowerCase().startsWith("set:")) {
				Module m = Main.moduleManager.getModule(args[1]);
				if(m != null) {
					Setting setting = Main.settingManager.getSettingByName(m,args[2]);
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
}