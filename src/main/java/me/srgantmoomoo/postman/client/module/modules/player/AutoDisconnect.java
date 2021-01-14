package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.GuiMainMenu;

public class AutoDisconnect extends Module {
	public NumberSetting health = new NumberSetting("health", this, 10, 1, 30, 1);

	
	public AutoDisconnect() {
		super ("autoDisconnect", "seeeeeee", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(health);
	}
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	public void onUpdate() {
		if(toggled && mc.player.getHealth() <= health.getValue()) {
			disable();
		}
	}
	
	@EventHandler
	private final Listener<PlayerUpdateEvent> listener = new Listener<>(event -> {
	    if (mc.player == null || mc.world == null) return;
		if (mc.player.getHealth() <= health.getValue()) {
			mc.world.sendQuittingDisconnectingPacket();
			mc.loadWorld(null);
			mc.displayGuiScreen(new GuiMainMenu());
		}
	});
}
