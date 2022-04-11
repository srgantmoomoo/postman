package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.postman.impl.modules.player.NoPush;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class MixinEntity {
	@Shadow public abstract boolean equals(Object p_equals_1_);

	@Shadow
	public double posX;

	@Shadow
	public double posY;

	@Shadow
	public double posZ;

	@Shadow
	public float rotationYaw;

	@Shadow
	public boolean onGround;

	@Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
	public void velocity(Entity entity, double x, double y, double z) {
		if (!NoPush.INSTANCE.isToggled()) {
			entity.motionX += x;
			entity.motionY += y;
			entity.motionZ += z;
			entity.isAirBorne = true;
		}
	}
}