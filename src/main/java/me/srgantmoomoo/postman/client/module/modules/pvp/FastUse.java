package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class FastUse extends Module {
	
	public BooleanSetting xpBottle = new BooleanSetting("xpBottle", this, true);
	public BooleanSetting bow = new BooleanSetting("bow", this, true);
	public BooleanSetting crystal = new BooleanSetting("crystal", this, true);
	public BooleanSetting destroy = new BooleanSetting("destroy", this, true);
	public BooleanSetting all = new BooleanSetting("all", this, true);


	public FastUse() {
		super ("fastUse", "Removes usage and break delay from certain items", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(xpBottle, bow, crystal, destroy, all);
	}
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onUpdate() {
		if (mc.player != null) {
			if (bow.isEnabled() && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3 && (mc.player.getHeldItemMainhand().getItem() == Items.BOW || mc.player.getHeldItemOffhand().getItem() == Items.BOW)) {
				mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
				mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
				mc.player.stopActiveHand();
			}
			if (xpBottle.isEnabled() && (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE)) {
				mc.rightClickDelayTimer = 0;
			}
			if (crystal.isEnabled() && (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL)) {
				mc.rightClickDelayTimer = 0;
			}
			if (destroy.isEnabled()) {
				mc.playerController.blockHitDelay = 0;
			}
			if (all.isEnabled()) {
				mc.rightClickDelayTimer = 0;
			}
		}
	}
	@Override
	public void onDisable() {
		mc.rightClickDelayTimer = 4;
		mc.playerController.blockHitDelay = 5;
	}
}

