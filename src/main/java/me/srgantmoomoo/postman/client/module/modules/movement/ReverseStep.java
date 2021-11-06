package me.srgantmoomoo.postman.client.module.modules.movement;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

public class ReverseStep extends Module {
	public NumberSetting height = new NumberSetting("height", this, 2.5, 0.5, 10, 0.5);
	
	public ReverseStep() {
		super ("reverseStep", "sucks u down when going down a block.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

	@Override
	public void onUpdate() {
		if (mc.world == null || mc.player == null || mc.player.isInWater() || mc.player.isInLava() || mc.player.isOnLadder() || mc.gameSettings.keyBindJump.isKeyDown()) {
			return;
		}

		if (mc.player != null && mc.player.onGround && !mc.player.isInWater() && !mc.player.isOnLadder()) {
			for (double y = 0.0; y < this.height.getValue() + 0.5; y += 0.01) {
				if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
					mc.player.motionY = -10.0;
					break;
				}
			}
		}
	}
}
