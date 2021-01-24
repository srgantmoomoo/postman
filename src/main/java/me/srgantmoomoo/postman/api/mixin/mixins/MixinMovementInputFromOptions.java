package me.srgantmoomoo.postman.api.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.api.event.events.PlayerUpdateMoveStateEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.movement.InventoryMove;

@Mixin(value = MovementInputFromOptions.class, priority = 10000)
public abstract class MixinMovementInputFromOptions extends MovementInput {
	
	@Inject(method = "updatePlayerMoveState", at = @At("RETURN"))
    public void updatePlayerMoveStateReturn(CallbackInfo callback) {
        Main.EVENT_BUS.post(new PlayerUpdateMoveStateEvent());
    }

	@Redirect(method = "updatePlayerMoveState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
	public boolean isKeyPressed(KeyBinding keyBinding) {
		if (ModuleManager.isModuleEnabled("inventoryMove") && ((InventoryMove)ModuleManager.getModuleByName("inventoryMove")).isToggled()
				&& Minecraft.getMinecraft().currentScreen != null
				&& !(Minecraft.getMinecraft().currentScreen instanceof GuiChat)
				&& Minecraft.getMinecraft().player != null) {
			return Keyboard.isKeyDown(keyBinding.getKeyCode());
		}
		return keyBinding.isKeyDown();
	}
}
