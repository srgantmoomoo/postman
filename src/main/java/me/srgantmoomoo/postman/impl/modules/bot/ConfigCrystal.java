package me.srgantmoomoo.postman.impl.modules.bot;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.srgantmoomoo.postman.impl.modules.pvp.AutoCrystal;

public class ConfigCrystal extends Module {
	public BooleanSetting auto = new BooleanSetting("autoConfig", this, true); 
	public ModeSetting server = new ModeSetting("server", this, "2b2tpvp", "2b2tpvp", ".cc", "other");
	public NumberSetting ping = new NumberSetting("averagePing", this, 20, 0, 500, 1);
	public BooleanSetting multiplace = new BooleanSetting("multiplace", this, false); 
	
	public ConfigCrystal() {
		super("configCrystal", "configs based on server and ping.", Keyboard.KEY_NONE, Category.BOT);
		this.addSettings(server, ping, multiplace);
	}
	
	@Override
	public void onUpdate() {
		if(multiplace.isEnabled()) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).multiplace.setEnabled(true);

			if(ping.getValue() <= 1) ((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).multiplacePlus.setEnabled(false);
			else if(ping.getValue() > 1) ((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).multiplacePlus.setEnabled(true);
			
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).highPing.setEnabled(false);
			return;
		}
		
		if(server.is("2b2tpvp")) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).rotate.setEnabled(true);
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(true);
		}
		if(server.is(".cc")) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).rotate.setEnabled(false);
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(false);
		}
		if(server.is("other")) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).rotate.setEnabled(false);
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(false);
		}
		
		if(ping.getValue() <= 20) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).breakType.setMode("swing");
		}else if(ping.getValue() > 20) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).breakType.setMode("packet");
		}
		if(ping.getValue() <= 5) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).highPing.setEnabled(false);;
		}else if(ping.getValue() > 5) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).highPing.setEnabled(true);;
		}
	}

}
