package me.srgantmoomoo.postman.api.mixin.mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import me.srgantmoomoo.postman.client.module.ModuleManager;

@Mixin(Entity.class)
public class MixinEntity {

	@Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
	public void velocity(Entity entity, double x, double y, double z) {
		if (!ModuleManager.isModuleEnabled("noPush")) {
			entity.motionX += x;
			entity.motionY += y;
			entity.motionZ += z;
			entity.isAirBorne = true;
		}
	}
}