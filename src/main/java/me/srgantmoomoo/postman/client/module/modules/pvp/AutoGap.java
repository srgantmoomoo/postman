package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;

public class AutoGap extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "always", "always", "smart");
	
	public AutoGap() {
		super("autoGap", "automattically eat any gapples in ur hand", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode);;
	}
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisbale() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
		
		 if (wasEating) {
			 wasEating = false;
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
		 }
	}
	
	private boolean wasEating = false;
	
	public void onUpdate() {
		if(mode.is("always")) {
			if(!(mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock) || !(mc.player.getHeldItemOffhand().getItem() instanceof ItemBlock)) {
				if(mc.gameSettings.keyBindSprint.isKeyDown()) mc.player.setSprinting(true);
				eatGap();
			}
		}
		
		if(mode.is("smart")) {
			if(mc.player.getHealth() <= 14) eatGap();
			
			if (wasEating && mc.player.getHealth() >= 14) {
				wasEating = false;
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	        }
		}
	}
	
	public void eatGap() {
		if(mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
			if(mc.currentScreen == null) {
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
				wasEating = true;
			}else {
            mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
            wasEating = true;
			}
		}
	}

}