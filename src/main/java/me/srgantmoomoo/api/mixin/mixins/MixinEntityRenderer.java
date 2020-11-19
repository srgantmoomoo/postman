package me.srgantmoomoo.api.mixin.mixins;

import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.modules.render.NoHurtCam;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer{

	@Inject(method = "hurtCameraEffect", at = @At("HEAD"), cancellable = true)
	public void hurtCameraEffect(float ticks, CallbackInfo info){
		if (ModuleManager.isModuleEnabled("noHurtCam") && ((NoHurtCam)ModuleManager.getModuleByName("noHurtCam")).hurtCam.getValue())
			info.cancel();
	}
}
