package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Type;
import me.srgantmoomoo.postman.event.events.EventPushAwayFromEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void push(Entity entity, CallbackInfo info) {
        EventPushAwayFromEntity e = new EventPushAwayFromEntity();
        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if(e.isCancelled()) info.cancel();
    }
}
