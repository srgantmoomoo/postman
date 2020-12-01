package me.srgantmoomoo.api.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
	FontRenderer fr = mc.fontRenderer;
  @Inject(method = "drawScreen", at = @At("TAIL"), cancellable = true)
  public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
	  fr.drawStringWithShadow("hey", 1, 1, 0xffffffff);
  }
}
