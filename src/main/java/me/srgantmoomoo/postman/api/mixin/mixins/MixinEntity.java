package me.srgantmoomoo.postman.api.mixin.mixins;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import me.srgantmoomoo.postman.client.module.ModuleManager;

@Mixin(Entity.class)
public abstract class MixinEntity {

	@Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
	public void velocity(Entity entity, double x, double y, double z) {
		if (!ModuleManager.isModuleEnabled("noPush")) {
			entity.motionX += x;
			entity.motionY += y;
			entity.motionZ += z;
			entity.isAirBorne = true;
		}
	}
	
	@Redirect(method = "getVelocityMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
    private Block getVelocityMultiplierGetBlockProxy(BlockState blockState) {
        if (blockState.getBlockState() == Blocks.SOUL_SAND && ModuleManager.getModuleByName("noSlow").isToggled()) return Blocks.STONE;
        return blockState.getBlockState();
    }
	
	  @Shadow public abstract boolean equals(Object p_equals_1_);

	    @Shadow
	    public double posX;

	    @Shadow
	    public double posY;

	    @Shadow
	    public double posZ;

	    @Shadow
	    public double prevPosX;

	    @Shadow
	    public double prevPosY;

	    @Shadow
	    public double prevPosZ;

	    @Shadow
	    public double lastTickPosX;

	    @Shadow
	    public double lastTickPosY;

	    @Shadow
	    public double lastTickPosZ;

	    @Shadow
	    public float prevRotationYaw;

	    @Shadow
	    public float prevRotationPitch;

	    @Shadow
	    public float rotationPitch;

	    @Shadow
	    public float rotationYaw;

	    @Shadow
	    public boolean onGround;

	    @Shadow
	    public double motionX;

	    @Shadow
	    public double motionY;

	    @Shadow
	    public double motionZ;

	    @Shadow
	    public abstract boolean isSprinting();

	    @Shadow
	    public abstract boolean isRiding();

	    @Shadow
	    public void move(MoverType type, double x, double y, double z) {

	    }
}