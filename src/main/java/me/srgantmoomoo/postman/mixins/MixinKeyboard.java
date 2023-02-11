package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.event.Type;
import me.srgantmoomoo.postman.event.events.EventKeyPress;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKeyPressed(long window, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        if(action == 1) {
            EventKeyPress e = new EventKeyPress(key, scanCode);
            e.setType(Type.PRE);

        }
    }
}
