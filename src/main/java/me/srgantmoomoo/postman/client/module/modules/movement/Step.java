package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.Event.Era;
import me.srgantmoomoo.postman.api.event.events.PlayerMotionUpdateEvent;
import me.srgantmoomoo.postman.api.event.events.PlayerUpdateMoveStateEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Step extends Module {
	public BooleanSetting entityStep = new BooleanSetting("entityStep", this, false);
	public ModeSetting height = new ModeSetting("height", this, "one", "one", "two");

    public Step() {
		super ("step", "steps lol", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(height,entityStep);
	}
    private byte cancelStage;
    private float _prevEntityStep;
    
    private final double[] oneblockPositions = {0.42D, 0.75D};
    private final double[] twoblockPositions = {0.4D, 0.75D, 0.5D, 0.41D, 0.83D, 1.16D, 1.41D, 1.57D, 1.58D, 1.42D};
    private double[] selectedPositions = new double[0];
    private int packets;

    @Override
    public void onEnable() {
        super.onEnable();
        cancelStage = 0;
        
        if (mc.player != null && mc.player.isRiding())
            _prevEntityStep = mc.player.getRidingEntity().stepHeight;
    }
    
    @Override
    public void onDisable()  {
        super.onDisable();
        mc.player.stepHeight = 0.5F;

        if (mc.player.isRiding())
            mc.player.getRidingEntity().stepHeight = _prevEntityStep;
    }

    @EventHandler
    private Listener<PlayerUpdateMoveStateEvent> onInputUpdate = new Listener<>(event -> {
        if (cancelStage != 0)
            mc.player.movementInput.jump = false;
        
        if (entityStep.isEnabled() && mc.player.isRiding()) {
            mc.player.getRidingEntity().stepHeight = 256f;
        }
    });
    
    @EventHandler
    private Listener<PlayerMotionUpdateEvent> OnMotionUpdate = new Listener<>(event -> {
    	if (event.getEra() == Era.PRE) {

            switch (this.height.getMode()) {
                case "one":
                    this.selectedPositions = this.oneblockPositions;
                    break;
                case "two":
                    this.selectedPositions = this.twoblockPositions;
                    break;
            }

            if (mc.player.collidedHorizontally && mc.player.onGround) {
                this.packets++;
            }

            final AxisAlignedBB bb = mc.player.getEntityBoundingBox();

            for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX + 1.0D); x++) {
                for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.maxZ + 1.0D); z++) {
                    final Block block = mc.world.getBlockState(new BlockPos(x, bb.maxY + 1, z)).getBlock();
                    if (!(block instanceof BlockAir)) {
                        return;
                    }
                }
            }

            if (mc.player.onGround && !mc.player.isInsideOfMaterial(Material.WATER) && !mc.player.isInsideOfMaterial(Material.LAVA) && !mc.player.isInWeb && mc.player.collidedVertically && mc.player.fallDistance == 0 && !mc.gameSettings.keyBindJump.isPressed() && mc.player.collidedHorizontally && !mc.player.isOnLadder() && this.packets > this.selectedPositions.length - 2) {
                for (double position : this.selectedPositions) {
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + position, mc.player.posZ, true));
                }
                mc.player.setPosition(mc.player.posX, mc.player.posY + this.selectedPositions[this.selectedPositions.length - 1], mc.player.posZ);
                this.packets = 0;
            }
    	}
    });
}
