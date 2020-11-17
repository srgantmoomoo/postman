/*package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.event.events.EventClientTick;
import me.srgantmoomoo.api.event.events.EventNetworkPostPacketEvent;
import me.srgantmoomoo.api.event.events.EventPlayerUpdateMoveState;
import me.srgantmoomoo.api.util.PlayerUtil;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShield;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging.Action;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

	public class NoSlow extends Module {
		private Minecraft mc = Minecraft.getMinecraft();

		
	public NoSlow() {
		super ("noSlow", "slow? no.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	 @Override
	    public void onDisable()
	    {
	        super.onDisable();
	        Blocks.ICE.setDefaultSlipperiness(0.98f);
	        Blocks.FROSTED_ICE.setDefaultSlipperiness(0.98f);
	        Blocks.PACKED_ICE.setDefaultSlipperiness(0.98f);
	    }

	    @EventHandler
	    private Listener<EventPlayerUpdateMoveState> OnIsKeyPressed = new Listener<>(event ->
	    {
	        if (mc.currentScreen != null)
	        {

	            mc.player.movementInput.moveStrafe = 0.0F;
	            mc.player.movementInput.moveForward = 0.0F;
	            
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()));
	            if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()))
	            {
	                ++mc.player.movementInput.moveForward;
	                mc.player.movementInput.forwardKeyDown = true;
	            }
	            else
	            {
	                mc.player.movementInput.forwardKeyDown = false;
	            }

	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()));
	            if (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()))
	            {
	                --mc.player.movementInput.moveForward;
	                mc.player.movementInput.backKeyDown = true;
	            }
	            else
	            {
	                mc.player.movementInput.backKeyDown = false;
	            }

	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindLeft.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()));
	            if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()))
	            {
	                ++mc.player.movementInput.moveStrafe;
	                mc.player.movementInput.leftKeyDown = true;
	            }
	            else
	            {
	                mc.player.movementInput.leftKeyDown = false;
	            }

	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()));
	            if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()))
	            {
	                --mc.player.movementInput.moveStrafe;
	                mc.player.movementInput.rightKeyDown = true;
	            }
	            else
	            {
	                mc.player.movementInput.rightKeyDown = false;
	            }

	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()));
	            mc.player.movementInput.jump = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
	        }
	    });

	    @EventHandler
	    private Listener<EventClientTick> OnTick = new Listener<>(p_Event ->
	    {
	        if (mc.player.isHandActive())
	        {
	            if (mc.player.getHeldItem(mc.player.getActiveHand()).getItem() instanceof ItemShield)
	            {
	                if (mc.player.movementInput.moveStrafe != 0 || mc.player.movementInput.moveForward != 0 && mc.player.getItemInUseMaxCount() >= 8)
	                {
	                    mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
	                }
	            }
	        }

	        if (true)
	        {
	            if (mc.player.getRidingEntity() != null)
	            {
	                Blocks.ICE.setDefaultSlipperiness(0.98f);
	                Blocks.FROSTED_ICE.setDefaultSlipperiness(0.98f);
	                Blocks.PACKED_ICE.setDefaultSlipperiness(0.98f);
	            }
	            else
	            {
	                Blocks.ICE.setDefaultSlipperiness(0.45f);
	                Blocks.FROSTED_ICE.setDefaultSlipperiness(0.45f);
	                Blocks.PACKED_ICE.setDefaultSlipperiness(0.45f);
	            }
	        }

	    });

	    @EventHandler
	    private Listener<EventPlayerUpdateMoveState> OnUpdateMoveState = new Listener<>(event ->
	    {
	        if (true && mc.player.isHandActive() && !mc.player.isRiding())
	        {
	            mc.player.movementInput.moveForward /= 0.2F;
	            mc.player.movementInput.moveStrafe /= 0.2F;
	        }
	    });

	    @EventHandler
	    private Listener<EventNetworkPostPacketEvent> PacketEvent = new Listener<>(p_Event ->
	    {
	        if (p_Event.getPacket() instanceof CPacketPlayer)
	        {
	                if (true && mc.player.isHandActive() && !mc.player.isRiding())
	                    mc.player.connection.sendPacket(new CPacketPlayerDigging(Action.ABORT_DESTROY_BLOCK, PlayerUtil.GetLocalPlayerPosFloored(), EnumFacing.DOWN));
	        }
	    });
}
*/