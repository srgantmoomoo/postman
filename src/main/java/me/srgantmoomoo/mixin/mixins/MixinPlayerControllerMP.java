package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.postman.impl.modules.player.Multitask;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
	//author cookiedragon234
	@Inject(method = "resetBlockRemoving", at = @At("HEAD"), cancellable = true)
	private void resetBlock(CallbackInfo callbackInfo) {
		if (Multitask.INSTANCE.isToggled()) {
			callbackInfo.cancel();
		}
	}
}