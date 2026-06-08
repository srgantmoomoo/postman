package me.srgantmoomoo.postman.mixins;

import com.mojang.authlib.GameProfile;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.modules.movement.NoSlow;
import me.srgantmoomoo.postman.module.modules.movement.SafeWalk;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.input.Input;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity extends AbstractClientPlayerEntity {
    @Shadow
    public Input input;

    private MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Override
    protected boolean clipAtLedge() {
        SafeWalk safeWalk = (SafeWalk) Main.INSTANCE.moduleManager.getModuleByName("safeWalk");
        if(safeWalk != null && safeWalk.isModuleEnabled())
            return true;
        return super.clipAtLedge();
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;shouldSlowDown()Z", ordinal = 0))
    private void noSlow(CallbackInfo info) {
        NoSlow noSlow = (NoSlow) Main.INSTANCE.moduleManager.getModuleByName("noSlow");
        if(noSlow == null || !noSlow.isModuleEnabled() || !noSlow.food.isEnabled()) return;

        ClientPlayerEntity player = (ClientPlayerEntity)(Object)this;
        if(!player.isUsingItem() || player.hasVehicle()) return;

        input.movementForward /= 0.2f;
        input.movementSideways /= 0.2f;
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;shouldStopSprinting()Z"))
    private boolean noSlowShouldStopSprinting(ClientPlayerEntity player) {
        NoSlow noSlow = (NoSlow) Main.INSTANCE.moduleManager.getModuleByName("noSlow");
        if(noSlow != null && noSlow.isModuleEnabled() && noSlow.food.isEnabled() && player.isUsingItem() && !player.hasVehicle())
            return false;
        return player.shouldStopSprinting();
    }
}