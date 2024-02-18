package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Type;
import me.srgantmoomoo.postman.event.events.EventGuiKeyPress;
import me.srgantmoomoo.postman.event.events.EventKeyPress;
import me.srgantmoomoo.postman.module.modules.ClickGuiModule;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    public void onKeyPressed(long window, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        EventKeyPress e = new EventKeyPress(key, scanCode);
        Main.INSTANCE.moduleManager.onKeyPress(e, key, scanCode);
        Main.INSTANCE.commandManager.onKeyPress();

        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if(e.isCancelled()) info.cancel();
    }

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onGuiKeyPressed(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        // for key listeners in gui screens
        EventGuiKeyPress e = new EventGuiKeyPress(key, scanCode);
        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if(e.isCancelled()) info.cancel();
    }
}
