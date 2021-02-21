package me.srgantmoomoo.postman.api.mixin.mixins;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.RenderRainEvent;
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
}
