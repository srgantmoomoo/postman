package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.ModeSetting;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "sickomode");
	
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	public Sprint() {
		super ("sprint", "now u cant walk, good going", Keyboard.KEY_NONE, Category.PLAYER);
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

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
