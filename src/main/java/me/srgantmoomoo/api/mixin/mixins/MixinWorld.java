package me.srgantmoomoo.api.mixin.mixins;

import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class MixinWorld{

	@Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
	private void updateLightmapHook(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> info){
		if (ModuleManager.isModuleEnabled("noSkylight") && ((NoSkylight)ModuleManager.getModuleByName("noSkylight")).noSkylight.getValue()){
			if (lightType == EnumSkyBlock.SKY){
				info.setReturnValue(true);
				info.cancel();
			}
		}
	}
}
