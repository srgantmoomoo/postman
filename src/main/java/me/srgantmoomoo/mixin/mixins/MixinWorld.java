package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.RenderRainEvent;
import me.srgantmoomoo.postman.impl.modules.render.NoRender;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class MixinWorld {

    @Inject(method = "getRainStrength", at = @At("HEAD"), cancellable = true)
    public void getRainStrength(float delta, CallbackInfoReturnable<Float> callback) {
        RenderRainEvent event = new RenderRainEvent();
        Main.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            callback.cancel();
            callback.setReturnValue(0.0f);
        }
    }
    
    @Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
    private void updateLightmapHook(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (NoRender.INSTANCE.isToggled() && NoRender.INSTANCE.skylight.isEnabled()) {
            if (lightType == EnumSkyBlock.SKY) {
                callbackInfoReturnable.setReturnValue(true);
                callbackInfoReturnable.cancel();
            }
        }
    }
}
