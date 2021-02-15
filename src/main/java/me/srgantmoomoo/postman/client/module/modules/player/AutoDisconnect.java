package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.gui.GuiMainMenu;

public class AutoDisconnect extends Module {
	public NumberSetting health = new NumberSetting("health", this, 10, 1, 30, 1);

	
	public AutoDisconnect() {
		super ("autoDisconnect", "automatically disconnects at desired health.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(health);
	}
	
	public void onUpdate() {
		if (mc.player == null || mc.world == null) return;
		if (mc.player.getHealth() <= health.getValue()) {
			toggled = false;
			mc.world.sendQuittingDisconnectingPacket();
			mc.loadWorld(null);
			mc.displayGuiScreen(new GuiMainMenu());
		}
	}
}