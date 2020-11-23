package me.srgantmoomoo.api.mixin.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.modules.client.TargetHud;

@Mixin (RenderPlayer.class)
public abstract class MixinRenderPlayer {

	@Inject(method = "renderEntityName", at = @At("HEAD"), cancellable = true)
	private void renderLivingLabel(AbstractClientPlayer entity, double x, double y, double z, String name, double distanceSq, CallbackInfo callback){
		if (ModuleManager.isModuleEnabled("targetHud") && TargetHud.isRenderingEntity(entity)){
			callback.cancel();
		}
	}
}
