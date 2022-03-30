package me.srgantmoomoo.postman.impl.modules.pvp;

import me.srgantmoomoo.Main;
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

//TODO menu problems.
public class AutoGap extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "always", "always", "smart");
	public NumberSetting health = new NumberSetting("health", this, 16, 1, 20, 1);
	public BooleanSetting cancelInMenu = new BooleanSetting("cancelInMenu", this, false);
	
	public AutoGap() {
		super("autoGap", "automattically eat any gapples in ur hand.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode, health, cancelInMenu);
	}
	private boolean wasSetFalse;
	private boolean wasSetFalse2;

	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	}
	
	@Override
	public void onUpdate() {
		if(mode.is("always")) {
			eatGap();
		}
		
		if(mode.is("smart")) {
			if(mc.player.getHealth() <= health.getValue()) {
				eatGap();
				wasSetFalse2 = false;
			}else if(!wasSetFalse2) {
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
			}
			else if(!wasSetFalse){
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
				wasSetFalse = true;
			}
		}else if(cancelInMenu.isEnabled()) {
			mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
		}
	}
}
