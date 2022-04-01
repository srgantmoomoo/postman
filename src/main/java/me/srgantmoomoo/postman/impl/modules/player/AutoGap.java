package me.srgantmoomoo.postman.impl.modules.player;

import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

/**
 * @author SrgantMooMoo
 * @since 3/30/22
 */

//TODO menu problems.
//TODO do stuff with isEating();
public class AutoGap extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "always", "always", "smart");
	public NumberSetting health = new NumberSetting("health", this, 16, 1, 20, 1);
	public BooleanSetting cancelInMenu = new BooleanSetting("cancelInMenu", this, false);
	public BooleanSetting switchToGap = new BooleanSetting("switchToGap", this, false);
	
	public AutoGap() {
		super("autoGap", "automattically eat any gapples in ur hand.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(mode, health, switchToGap, cancelInMenu);
	}
	private boolean wasSetFalse; // using these wasSetFalse booleans to avoid the players hand being constantly set to not clicking, disallowing the player to click.
	private boolean wasSetFalse2;
	private int oldSlot = 0;

	@Override
	public void onEnable() {
		if(mode.is("always")) oldSlot = mc.player.inventory.currentItem;
	}

	@Override
	public void onDisable() {
		if(mode.is("always")) mc.player.inventory.currentItem = oldSlot;
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	}

	private boolean ran = false;
	private boolean isEating = false;
	@Override
	public void onUpdate() {
		if(mode.is("always")) {
			if(switchToGap.isEnabled()) mc.player.inventory.currentItem = findGappleSlot();
			eatGap();
		}
		
		if(mode.is("smart")) {
			if(mc.player.getHealth() <= health.getValue()) {
				if(switchToGap.isEnabled()) {
					if(!ran) {
						oldSlot = mc.player.inventory.currentItem;
						ran = true;
					}
					mc.player.inventory.currentItem = findGappleSlot();
				}
				eatGap();
				wasSetFalse2 = false;
			}else if(!wasSetFalse2) {
				mc.player.inventory.currentItem = oldSlot;
				ran = false;
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
				wasSetFalse2 = true;
			}
		}
	}
	
	public void eatGap() {
		if(mc.currentScreen == null) {
			if(mc.player.getHeldItemMainhand().getItem().equals(Items.GOLDEN_APPLE) || mc.player.getHeldItemOffhand().getItem().equals(Items.GOLDEN_APPLE)) {
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
				wasSetFalse = false;
			}else if(!wasSetFalse) {
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
				wasSetFalse = true;
			}
		}else if(!cancelInMenu.isEnabled()) {
			if(mc.player.getHeldItemMainhand().getItem().equals(Items.GOLDEN_APPLE) || mc.player.getHeldItemOffhand().getItem().equals(Items.GOLDEN_APPLE))
				mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
		}
	}

	private int findGappleSlot() {
		int slot = -1;

		for(int i = 0; i < 9; i++) {
			ItemStack stack = mc.player.inventory.getStackInSlot(i);

			if(stack.getItem() == Items.GOLDEN_APPLE) {
				slot = i;
				break;
			}
		}

		return slot;
	}
}
