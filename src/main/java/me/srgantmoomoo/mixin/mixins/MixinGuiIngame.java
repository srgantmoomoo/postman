package me.srgantmoomoo.mixin.mixins;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.impl.modules.render.NoRender;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

	@Inject(method = "renderPotionEffects", at = @At("HEAD"), cancellable = true)
	protected void renderPotionEffectsHook(ScaledResolution scaledRes, CallbackInfo callbackInfo) {
		if (NoRender.INSTANCE.isToggled() && NoRender.INSTANCE.potionIndicators.isEnabled()) {
			callbackInfo.cancel();
		}
	}
}