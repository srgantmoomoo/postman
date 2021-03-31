package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Desktop;
import java.net.URI;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;

public class CrazyNewDupe extends Module {
	public ModeSetting dupeMode = new ModeSetting("mode", this, "donate", "donate", "github", "discord");
	
	public CrazyNewDupe() {
		super("postmanAppreciation", "crazyyyyyyy 0_0.", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(dupeMode);
	}
	
	public void onEnable() {
		if(dupeMode.is("sex")) {
			try {
				Desktop.getDesktop().browse(URI.create("https://www.paypal.com/biz/fund?id=4A9XUTEQMVUZG"));
			} catch (Exception e) {}
		}
		if(dupeMode.is("blowy")) {
			try {
				Desktop.getDesktop().browse(URI.create("https://github.com/moomooooo/postman"));
			} catch (Exception e) {}
		}
		if(dupeMode.is("moneyGlitch")) {
			try {
				Desktop.getDesktop().browse(URI.create("https://discord.gg/Jd8EmEuhb5"));
			} catch (Exception e) {}
		}
	}

}
