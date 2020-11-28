package me.srgantmoomoo.postman.ui.clickgui;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ClickGuiModule extends Module {
	boolean on;
	
	public ClickGuiModule() {
		super("clickGui", "classic hud", Keyboard.KEY_RSHIFT, Category.CLIENT);
		this.addSettings();
	}

	@SubscribeEvent
	public void key(KeyInputEvent e) {
		int keyCode = Keyboard.getEventKey();
		if(keyCode == Keyboard.KEY_ESCAPE) {
			toggled = false;
			on = false;
			
		}
	}

	public void onDisable() {
		super.onDisable();
		on = false;
		}

	public void onEnable() {
		super.onEnable();
		on = true;
		if(on) {
		Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
		}
	}
}
