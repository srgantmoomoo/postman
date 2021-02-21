package me.srgantmoomoo.postman.api.mixin.mixins;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.RenderCameraEvent;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.render.NoHurtCam;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

	@Inject(method = "hurtCameraEffect", at = @At("HEAD"), cancellable = true)
	public void hurtCameraEffect(float ticks, CallbackInfo info) {
		if (ModuleManager.isModuleEnabled("noHurtCam") && ((NoHurtCam)ModuleManager.getModuleByName("noHurtCam")).mode.is("normal"))
			info.cancel();
	}
	
	@Redirect(method = "orientCamera", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"), expect = 0)
	private RayTraceResult rayTraceBlocks(WorldClient worldClient, Vec3d start, Vec3d end) {
		RenderCameraEvent event = new RenderCameraEvent();
	    Main.EVENT_BUS.post(event);
	    if (event.isCancelled())
	        return null;
	    else return worldClient.rayTraceBlocks(start, end);
	    }
}