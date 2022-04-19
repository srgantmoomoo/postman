package me.srgantmoomoo.mixin.mixins;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.CanCollideCheckEvent;

@Mixin(BlockLiquid.class)
public class MixinBlockLiquid {
	@Inject(method = "canCollideCheck", at = @At("HEAD"), cancellable = true)
    public void canCollideCheck(final IBlockState blockState, final boolean b, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        CanCollideCheckEvent event = new CanCollideCheckEvent();
        Main.EVENT_BUS.post(event);
        callbackInfoReturnable.setReturnValue(event.isCancelled());
    }
}
