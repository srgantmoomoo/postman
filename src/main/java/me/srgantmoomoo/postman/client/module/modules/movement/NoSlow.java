package me.srgantmoomoo.postman.client.module.modules.movement;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

	public class NoSlow extends Module {
	public BooleanSetting food = new BooleanSetting("food", this, true);
	public BooleanSetting web = new BooleanSetting("web", this, true);
	public BooleanSetting soulSand = new BooleanSetting("soulSand", this, true);
	public BooleanSetting slimeBlock = new BooleanSetting("slimeBlock", this, true);
	
	public NoSlow() {
		super ("noSlow", "slow? no.", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(food,web,soulSand,slimeBlock);
	}
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
		Blocks.DIRT.setLightOpacity(10);
	}
	
	public void onDisable() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
	private final Listener<InputUpdateEvent> eventListener = new Listener<>(event -> {
	
		
			if (mc.player.isHandActive() && !mc.player.isRiding() && !mc.player.isSneaking() && food.isEnabled()) { // added sneaking check as almost all anticheats rubberbands and its annoying
				event.getMovementInput().moveStrafe *= 5;
				event.getMovementInput().moveForward *= 5;
		}
			
	});
	private final Listener<TickEvent.ClientTickEvent> eventLis = new Listener<>(event -> {
		
		BlockPos playerStandingPos = mc.player.getPosition().down(); // gets the pos
		IBlockState blockUnderPlayer = mc.player.world.getBlockState(playerStandingPos); //gets the state of the block
		
		if(slimeBlock.isEnabled() && blockUnderPlayer.getBlock() == Blocks.SLIME_BLOCK) { //now gets the block, im pretty sure there is an easier way to do this, but it works so ye
			Blocks.SLIME_BLOCK.setDefaultSlipperiness(0.4945f); 
		}
		
	});
	
	
}