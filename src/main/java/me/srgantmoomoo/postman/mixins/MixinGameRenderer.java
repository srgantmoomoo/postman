package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Type;
import me.srgantmoomoo.postman.event.events.EventRender3d;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
    private void renderHand(MatrixStack matrixStack, Camera camera, float f, CallbackInfo info) {
        EventRender3d e = new EventRender3d(f, matrixStack);
        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }
}
