package me.srgantmoomoo.postman.client.module.modules.bot;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.pvp.AutoCrystal;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

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
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).multiplace.setEnabled(true);

			if(ping.getValue() <= 1) ((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).multiplacePlus.setEnabled(false);
			else if(ping.getValue() > 1) ((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).multiplacePlus.setEnabled(true);
			
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).highPing.setEnabled(false);
			return;
		}
		
		if(server.is("2b2tpvp")) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).rotate.setEnabled(true);
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(true);
		}
		if(server.is(".cc")) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).rotate.setEnabled(false);
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(false);
		}
		if(server.is("other")) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).rotate.setEnabled(false);
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(false);
		}
		
		if(ping.getValue() <= 20) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).breakType.setMode("swing");
		}else if(ping.getValue() > 20) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).breakType.setMode("packet");
		}
		if(ping.getValue() <= 5) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).highPing.setEnabled(false);
        }else if(ping.getValue() > 5) {
			((AutoCrystal) ModuleManager.getModuleByName("autoCrystal")).highPing.setEnabled(true);
        }
	}

}
