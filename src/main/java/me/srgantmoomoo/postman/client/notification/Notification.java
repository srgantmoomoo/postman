package me.srgantmoomoo.postman.client.notification;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public class Notification extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "hud", "hud", "chat");
	public BooleanSetting toggle = new BooleanSetting("toggle", this, true);
	public BooleanSetting enemyTotemPops = new BooleanSetting("enemyTotemPops", this, true);
	public BooleanSetting selfTotemPops = new BooleanSetting("selfTotemPops", this, true);
	public BooleanSetting lowHealth = new BooleanSetting("lowHealth", this, true);
	
	public Notification() {
		super ("notification", "client notifications.", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(mode, toggle, enemyTotemPops, selfTotemPops, lowHealth);
	}
	
	@Override
	public void onUpdate() {
		logic();
	}
	
	public void logic() {
		if(mode.is("chat")) ChatNotification();
		else HudNotification();
	}
	
	public void ChatNotification() {
		
		boolean sent = false;
		
		for (Module mod : ModuleManager.getModules()) {
			if(!sent) {
				if(mod.isToggled()) {
					ModuleManager.addChatMessage(mod.getName() + TextFormatting.GREEN + " has been enabled.");
					sent = true;
				}
			}
		}
	}
	
	public void HudNotification() {
		
	}
}
