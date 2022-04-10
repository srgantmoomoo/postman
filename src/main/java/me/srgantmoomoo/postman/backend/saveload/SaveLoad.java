package me.srgantmoomoo.postman.backend.saveload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.friend.Friend;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import me.srgantmoomoo.postman.framework.module.setting.Setting;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;

/**
 * inspiration taken from SebSb
 * @author SrgantMooMoo
 * @since 2/28/22
 */

public class SaveLoad {
	private final File dataFile;

	public SaveLoad() {
		File dir = new File(Minecraft.getMinecraft().gameDir, Reference.NAME);
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
		ArrayList<String> toSave = new ArrayList<>();

		// modules and keybinds
		toSave.add("modname:toggled:keybind");

		for(Module mod : Main.INSTANCE.moduleManager.modules) {
			toSave.add("MODULE:" + mod.getName() + ":" + mod.isToggled() + ":" + mod.getKey());
		}

		// settings
		toSave.add("\nmodname:settingname:value (:rainbow for color settings)");

		for(Module mod : Main.INSTANCE.moduleManager.modules) {
			for(Setting setting : mod.settings) {

				if(setting instanceof BooleanSetting) {
					BooleanSetting bool = (BooleanSetting) setting;
					toSave.add("SETTING:" + mod.getName() + ":" + setting.name + ":" + bool.isEnabled());
				}

				if(setting instanceof NumberSetting) {
					NumberSetting numb = (NumberSetting) setting;
					toSave.add("SETTING:" + mod.getName() + ":" + setting.name + ":" + numb.getValue());
				}

				if(setting instanceof ModeSetting) {
					ModeSetting mode = (ModeSetting) setting;
					toSave.add("SETTING:" + mod.getName() + ":" + setting.name + ":" + mode.getMode());
				}

				if(setting instanceof ColorSetting) {
					ColorSetting color = (ColorSetting) setting;
					toSave.add("SETTING:" + mod.getName() + ":" + setting.name + ":" + color.toInteger() + ":" + color.getRainbow());
				}
			}
		}

		// friends
		toSave.add("");

		for(Friend friend : Main.INSTANCE.friendManager.friends) {
			toSave.add("FRIEND:" + friend.getName());
		}

		// command prefix
		toSave.add("");

		toSave.add("COMMANDPREFIX:" + Main.INSTANCE.commandManager.prefix);

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
		ArrayList<String> lines = new ArrayList<>();

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
			if(s.startsWith("MODULE:")) {
				Module m = Main.INSTANCE.moduleManager.getModuleByName(args[1]);
				if(m != null) {
					if(!m.getName().equals("clickGui") && !m.getName().equals("hudEditor") && !m.getName().equals("blink") && !m.getName().equals("autoDisconnect") && !m.getName().equals("clientFont") && !m.getName().equals("protester")) {
						m.setToggled(Boolean.parseBoolean(args[2]));
						m.setKey(Integer.parseInt(args[3]));
					}

					// hud modules
					if(m.getName().equals("clickGui")) m.setToggled(false);
					if(m.getName().equals("hudEditor")) m.setToggled(false);
					// normal modules that can cause crashes
					if(m.getName().equals("blink")) m.setToggled(false);
					if(m.getName().equals("autoDisconnect")) m.setToggled(false);
					if(m.getName().equals("clientFont")) m.setToggled(false);
					if(m.getName().equals("protester")) m.setToggled(false);
					//TODO fix these
				}
			}else if(s.startsWith("SETTING:")) {
				Module m = Main.INSTANCE.moduleManager.getModuleByName(args[1]);
				if(m != null) {
					Setting setting = Main.INSTANCE.settingManager.getSettingByName(m,args[2]);
					if(setting != null) {
						if(setting instanceof BooleanSetting) {
							((BooleanSetting)setting).setEnabled(Boolean.parseBoolean(args[3]));
						}
						if(setting instanceof NumberSetting) {
							((NumberSetting)setting).setValue(Double.parseDouble(args[3]));
						}
						if(setting instanceof ModeSetting && ((ModeSetting) setting).modes.toString().contains(args[3])) { // u have to make sure the mode getting loaded actually still exists or else u will have angry mob of ppl telling u ur config is fucking garbage... but actually yes ur config is fucking garbage because u wrote it when u were fucking monke and didn't know wtf u were doing, like seriously come on now, who the fuck writes a config in a normal fucking txt file, r u fucking stupid??????? like just do it in fucking json u fucking dumb cunt.
							((ModeSetting)setting).setMode(args[3]);
						}
						if(setting instanceof ColorSetting) {
							((ColorSetting)setting).fromInteger(Integer.parseInt(args[3]));
							((ColorSetting)setting).setRainbow(Boolean.parseBoolean(args[4])); //TODO theres some config issues with this.
						}
					}
				}
			}else if(s.startsWith("FRIEND:")) {
				Main.INSTANCE.friendManager.addFriend(args[1]);
			}else if(s.startsWith("COMMANDPREFIX:")) {
				Main.INSTANCE.commandManager.setCommandPrefix(args[1]);
			}
		}
	}
}