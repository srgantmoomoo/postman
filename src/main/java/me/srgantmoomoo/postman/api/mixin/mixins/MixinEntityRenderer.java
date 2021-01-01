package me.srgantmoomoo.postman.api.mixin.mixins;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.render.NoHurtCam;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

	@Inject(method = "hurtCameraEffect", at = @At("HEAD"), cancellable = true)
	public void hurtCameraEffect(float ticks, CallbackInfo info){
		if (ModuleManager.isModuleEnabled("noHurtCam") && ((NoHurtCam)ModuleManager.getModuleByName("noHurtCam")).mode.getMode().equals("normal"))
			info.cancel();
	}
}