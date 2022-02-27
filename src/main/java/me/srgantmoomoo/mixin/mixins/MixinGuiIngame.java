package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.framework.module.ModuleManager;
import me.srgantmoomoo.postman.client.modules.render.NoRender;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

	@Inject(method = "renderPotionEffects", at = @At("HEAD"), cancellable = true)
	protected void renderPotionEffectsHook(ScaledResolution scaledRes, CallbackInfo callbackInfo) {
		if (Main.INSTANCE.moduleManager.isModuleEnabled("noRender") && ((NoRender)Main.INSTANCE.moduleManager.getModuleByName("noRender")).potionIndicators.isEnabled()) {
			callbackInfo.cancel();
		}
	}
}