package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
	@Inject(method = {"drawScreen"}, at = {@At("TAIL")}, cancellable = true)
	public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
		if(Main.INSTANCE.moduleManager.getModuleByName("mainMenuWatermark").isToggled()) {
			FontRenderer fr = mc.fontRenderer;
		    fr.drawStringWithShadow("you're using " + Reference.NAME + " right now :')", 2, 2, 0xffffffff); 
		}
	}
}