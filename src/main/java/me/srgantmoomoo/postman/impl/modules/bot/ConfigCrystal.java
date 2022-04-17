package me.srgantmoomoo.postman.impl.modules.bot;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.impl.modules.client.NotificationModule;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.srgantmoomoo.postman.impl.modules.pvp.AutoCrystal;

import java.util.Arrays;
import java.util.Objects;

//TODO automize.
public class ConfigCrystal extends Module {
	//public BooleanSetting auto = new BooleanSetting("autoConfig", this, false);
	public ModeSetting server = new ModeSetting("server", this, "2b2tpvp", "2b2tpvp", ".cc", "other");
	public NumberSetting ping = new NumberSetting("averagePing", this, 20, 0, 500, 1);
	public BooleanSetting multiplace = new BooleanSetting("multiplace", this, false); 
	
	public ConfigCrystal() {
		super("configCrystal", "configs based on server and ping.", Keyboard.KEY_NONE, Category.BOT);
		this.addSettings(server, ping, multiplace);
	}

	@Override
	public void onUpdate() {
		//if(auto.isEnabled())
			//automate();

		// multiplacing
		if(multiplace.isEnabled()) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).multiplace.setEnabled(true);

			// basically always want multiplaceplus enabled.
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).multiplacePlus.setEnabled(true);

			// highPing causes issues, should be disabled for multiplacing
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).highPing.setEnabled(false);
		}

		// rotations
		if(server.is("2b2tpvp")) {
			// rotate and spoof rotations for 2bpvp.
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).rotate.setEnabled(true);
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(true);
		}else if(server.is(".cc")) {
			// rotate and spoof rotations off for any .cc servers.
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).rotate.setEnabled(false);
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(false);
		}else {
			// generally for other servers it's safe to just keep these off.
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).rotate.setEnabled(false);
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).spoofRotations.setEnabled(false);
		}

		// break types
		if(ping.getValue() >= 20) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).breakType.setMode("packet");
		}else {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).breakType.setMode("swing");
		}

		// high ping.... generally just should be enabled unless someone lives with the damn server.
		if(ping.getValue() > 5) {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).highPing.setEnabled(true);
		}else {
			((AutoCrystal) Main.INSTANCE.moduleManager.getModuleByName("autoCrystal")).highPing.setEnabled(false);
		}
	}

	/*private void automate() {
		String detectServer = "something";
		double detectPing = 1;

		if(detectServer.equalsIgnoreCase("2b2t.pvp")) server.setMode("2b2tpvp");
		else if(detectServer.equalsIgnoreCase("any .cc server") || detectServer.equalsIgnoreCase("any other .cc server")) server.setMode(".cc");
		else server.setMode("other");

		ping.setValue(detectPing);
	}*/
}
