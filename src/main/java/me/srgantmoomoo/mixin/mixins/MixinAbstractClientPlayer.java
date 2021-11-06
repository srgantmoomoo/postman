package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {

	@Shadow @Nullable protected abstract NetworkPlayerInfo getPlayerInfo();

	@Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
	public void getLocationCape(CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
		UUID uuid = Objects.requireNonNull(getPlayerInfo()).getGameProfile().getId();
		if (ModuleManager.isModuleEnabled("capes") && Main.cape.hasCape(uuid)) {
			callbackInfoReturnable.setReturnValue(new ResourceLocation(Reference.MOD_ID, "textures/postman-cape.png"));
		}
	}
}