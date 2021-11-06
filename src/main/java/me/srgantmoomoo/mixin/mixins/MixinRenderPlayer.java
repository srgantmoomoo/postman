package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.RenderEntityNameEvent;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public class MixinRenderPlayer {
    @Inject(method = "renderEntityName", at = @At("HEAD"), cancellable = true)
    public void renderLivingLabel(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq, CallbackInfo info) {
    	if(ModuleManager.getModuleByName("nametags").isToggled()) {
    		info.cancel();
    	}
    	
        RenderEntityNameEvent event = new RenderEntityNameEvent(entityIn, x, y, z, name, distanceSq);
        Main.EVENT_BUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }
}