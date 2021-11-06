package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.PlayerJumpEvent;
import me.srgantmoomoo.postman.api.event.events.WaterPushEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

	@Shadow public abstract String getName();

	@Inject(method = "jump", at = @At("HEAD"))
	public void onJump(CallbackInfo callbackInfo) {
		if (Minecraft.getMinecraft().player.getName().equals(this.getName())) {
			Main.EVENT_BUS.post(new PlayerJumpEvent());
		}
	}

	@Inject(method = "isPushedByWater", at = @At("HEAD"), cancellable = true)
	private void onPushedByWater(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		WaterPushEvent event = new WaterPushEvent();
		Main.EVENT_BUS.post(event);
		if (event.isCancelled()) {
			callbackInfoReturnable.setReturnValue(false);
		}
	}
}