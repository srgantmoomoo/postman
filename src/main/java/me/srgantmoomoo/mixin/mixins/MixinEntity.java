package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import me.srgantmoomoo.postman.framework.module.ModuleManager;

@Mixin(Entity.class)
public abstract class MixinEntity {

	@Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
	public void velocity(Entity entity, double x, double y, double z) {
		if (!Main.INSTANCE.moduleManager.isModuleEnabled("noPush")) {
			entity.motionX += x;
			entity.motionY += y;
			entity.motionZ += z;
			entity.isAirBorne = true;
		}
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