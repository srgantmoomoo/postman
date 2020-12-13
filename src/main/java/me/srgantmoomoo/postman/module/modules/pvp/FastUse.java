package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class FastUse extends Module {
	
	public boolean plswork;
	public BooleanSetting xpBottle = new BooleanSetting("xpBottle", true);
	public BooleanSetting bow = new BooleanSetting("bow", true);
	
	public FastUse() {
		super ("fastUse", "lol bow go brrrrrrrr", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(xpBottle, bow);
	}
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onUpdate() {
		
		if (bow.isEnabled() && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3) {
			mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
			mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
			mc.player.stopActiveHand();
			}
		
		if (xpBottle.isEnabled() &&
				mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE))
				mc.rightClickDelayTimer = 0; 
		}
	
}

