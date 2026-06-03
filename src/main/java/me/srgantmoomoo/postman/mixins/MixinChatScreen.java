package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class MixinChatScreen {
    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if(keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            String input = ((ChatScreen)(Object)this).chatField.getText();
            if(input.startsWith(Main.INSTANCE.commandManager.getPrefix())) {
                MinecraftClient.getInstance().setScreen(null);
                Main.INSTANCE.commandManager.onClientChat(input);
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }
}