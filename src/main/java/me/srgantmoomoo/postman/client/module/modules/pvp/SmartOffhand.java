package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;

public class SmartOffHand extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "gap", "gap", "crystal");
	
	public SmartOffHand() {
		super("smartOffHand", "smart, off. HAND.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode);
	}

}
