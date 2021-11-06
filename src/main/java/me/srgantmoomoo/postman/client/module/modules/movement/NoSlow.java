package me.srgantmoomoo.postman.client.module.modules.movement;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.InputUpdateEvent;
import org.lwjgl.input.Keyboard;

	public class NoSlow extends Module {
	public final BooleanSetting food = new BooleanSetting("food", this, true);
	public final BooleanSetting web = new BooleanSetting("web", this, true);
	public final BooleanSetting soulSand = new BooleanSetting("soulSand", this, true);
	public final BooleanSetting slimeBlock = new BooleanSetting("slimeBlock", this, true);
	
	public NoSlow() {
		super ("noSlow", "slow? no.", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(food, web, soulSand, slimeBlock);
	}
	
	@Override
	public void onEnable() {
		Blocks.DIRT.setLightOpacity(10);
	}
	
	@EventHandler
	private final Listener<InputUpdateEvent> eventListener = new Listener<>(event -> {
			if (mc.player.isHandActive() && !mc.player.isRiding() && food.isEnabled()) {
				event.getMovementInput().moveStrafe *= 5;
				event.getMovementInput().moveForward *= 5;
		}
	});
}