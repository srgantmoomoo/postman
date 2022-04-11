package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.postman.impl.modules.player.Multitask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.mixin.mixins.accessor.AccessorEntityPlayerSP;

@Mixin(Minecraft.class)
public class MixinMinecraft {

	@Shadow public EntityPlayerSP player;
	@Shadow public PlayerControllerMP playerController;

	private boolean handActive = false;
	private boolean isHittingBlock = false;

	@Inject(method = "rightClickMouse", at = @At("HEAD"))
	public void rightClickMousePre(CallbackInfo ci) {
		if (Multitask.INSTANCE.isToggled()) {
			this.isHittingBlock = this.playerController.getIsHittingBlock();
			this.playerController.isHittingBlock = false;
		}
	}

	@Inject(method = "rightClickMouse", at = @At("RETURN"))
	public void rightClickMousePost(CallbackInfo ci) {
		if (Multitask.INSTANCE.isToggled() && !playerController.getIsHittingBlock()) {
			this.playerController.isHittingBlock = this.isHittingBlock;
		}
	}

	@Inject(method = "sendClickBlockToController", at = @At("HEAD"))
	public void sendClickBlockToControllerPre(boolean leftClick, CallbackInfo ci) {
		if (Multitask.INSTANCE.isToggled()) {
			this.handActive = this.player.isHandActive();
			((AccessorEntityPlayerSP) this.player).gsSetHandActive(false);
		}
	}

	@Inject(method = "sendClickBlockToController", at = @At("RETURN"))
	public void sendClickBlockToControllerPost(boolean leftClick, CallbackInfo ci) {
		if (Multitask.INSTANCE.isToggled() && !this.player.isHandActive()) {
			((AccessorEntityPlayerSP) this.player).gsSetHandActive(handActive);
		}
	}
}
