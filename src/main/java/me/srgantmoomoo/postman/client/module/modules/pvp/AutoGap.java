package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

public class AutoGap extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "always", "always", "smart");
	
	public AutoGap() {
		super("autoGap", "automattically eat any gapples in ur hand", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode);;
	}
	
	public void onUpdate() {
		if(mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
			if(mc.currentScreen == null) {
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
				if(mc.gameSettings.keyBindSprint.isKeyDown()) mc.player.setSprinting(true);
			}else {
            mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
			}
		}
	}

}
