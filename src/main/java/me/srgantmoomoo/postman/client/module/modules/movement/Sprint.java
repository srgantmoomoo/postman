package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.settings.ModeSetting;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "sickomode");
	
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	public Sprint() {
		super ("sprint", "now u cant walk, good going", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(mode);
	}
	
	@Override
	public void onUpdate() {
			if(mode.getMode().equals("normal")) {
			if(mc.player.movementInput.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally) {
				mc.player.setSprinting(true);
				}
			}else if(mode.getMode().equals("sickomode")) {
					mc.player.setSprinting(true);
			
		}
	}

}
