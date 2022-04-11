package me.srgantmoomoo.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.Event.Era;
import me.srgantmoomoo.postman.backend.event.events.PlayerMotionUpdateEvent;
import me.srgantmoomoo.postman.backend.event.events.PlayerMoveEvent;
import me.srgantmoomoo.postman.backend.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.impl.modules.movement.Sprint;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer {
	public MixinEntityPlayerSP() {
		super(null, null);
	}
	
   @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"), cancellable = true)
	public void OnPreUpdateWalkingPlayer(CallbackInfo info) {
		PlayerMotionUpdateEvent event = new PlayerMotionUpdateEvent(Era.PRE);
		Main.EVENT_BUS.post(event);
		if (event.isCancelled())
			info.cancel();
	}

   @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"), cancellable = true)
	public void OnPostUpdateWalkingPlayer(CallbackInfo info) {
		PlayerMotionUpdateEvent event = new PlayerMotionUpdateEvent(Era.POST);
		Main.EVENT_BUS.post(event);
		if (event.isCancelled())
			info.cancel();
	}

   @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
	public void onUpdate(CallbackInfo info) {
		PlayerUpdateEvent event = new PlayerUpdateEvent();
		Main.EVENT_BUS.post(event);
		if (event.isCancelled())
			info.cancel();
	}

   @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
   public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
	   PlayerMoveEvent moveEvent = new PlayerMoveEvent(type, x, y, z);
	   Main.EVENT_BUS.post(moveEvent);
	   super.move(type, moveEvent.x, moveEvent.y, moveEvent.z);
   }

	@Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal = 2))
	public void onLivingUpdate(EntityPlayerSP entityPlayerSP, boolean sprinting) {
		if (Sprint.INSTANCE.isToggled() && Sprint.INSTANCE.mode.is("sickomode") && (Minecraft.getMinecraft().player.movementInput.moveForward != 0.0f || Minecraft.getMinecraft().player.movementInput.moveStrafe != 0.0f)) entityPlayerSP.setSprinting(true);
		else entityPlayerSP.setSprinting(sprinting);
	}
}