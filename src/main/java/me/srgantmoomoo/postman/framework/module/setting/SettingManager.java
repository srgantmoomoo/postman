package me.srgantmoomoo.postman.framework.module.setting;

import java.util.ArrayList;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

/**
 * @author SrgantMooMoo
 * @since 4/1/2022
 */

public class SettingManager {
	private final ArrayList<Setting> settings;
	
	public SettingManager(){
		this.settings = new ArrayList<Setting>();
	}

	public ArrayList<Setting> getSettings() {
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod) {
		ArrayList<Setting> out = new ArrayList<Setting>();
		for(Setting s : getSettings()) {
			if(s.parent.equals(mod)) {
				out.add(s);
			}
		}
		if(out.isEmpty()) {
			return null;
		}
		return out;
	}
	
	public Setting getSettingByName(Module mod, String name) {
		for (Module m : Main.INSTANCE.moduleManager.modules) {
			for (Setting set : m.settings) {
					if (set.name.equalsIgnoreCase(name) && set.parent == mod) {
						return set;
					}
				}
			}
			System.err.println("[postman] Error Setting NOT found: '" + name +"'!");
		return null;
	}
}
