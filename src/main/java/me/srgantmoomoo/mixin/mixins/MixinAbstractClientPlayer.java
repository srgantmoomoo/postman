package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.postman.impl.modules.client.Capes;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;

import java.util.UUID;

import javax.annotation.Nullable;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {

	@Shadow @Nullable protected abstract NetworkPlayerInfo getPlayerInfo();

	@Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
	public void getLocationCape(CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
		UUID uuid = getPlayerInfo().getGameProfile().getId();
		if (Capes.INSTANCE.isToggled() && Main.INSTANCE.cape.hasCape(uuid)) {
			callbackInfoReturnable.setReturnValue(new ResourceLocation(Reference.MOD_ID, "textures/postman-cape.png"));
		}
	}
}