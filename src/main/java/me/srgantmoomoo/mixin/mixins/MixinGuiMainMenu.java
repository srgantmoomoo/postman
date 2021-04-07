package me.srgantmoomoo.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
	@Inject(method = {"drawScreen"}, at = {@At("TAIL")}, cancellable = true)
	public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
		if(ModuleManager.getModuleByName("mainMenuWatermark").isToggled()) {
			FontRenderer fr = mc.fontRenderer;
		    fr.drawStringWithShadow(TextFormatting.ITALIC + Reference.NAME + TextFormatting.WHITE + " by" + TextFormatting.GRAY + "" +
		    		TextFormatting.ITALIC + " SrgantMooMoo", 2, 2, 0xff79c2ec); 
		    fr.drawStringWithShadow(TextFormatting.WHITE + "ur on version " + TextFormatting.WHITE + TextFormatting.ITALIC + Reference.VERSION + TextFormatting.RESET + TextFormatting.WHITE + "!", 2, 12, 0xff79c2ec);
		    fr.drawStringWithShadow("https://github.com/moomooooo/postman", 2, 22, 0xff79c2ec);
		    fr.drawStringWithShadow("https://discord.gg/Jd8EmEuhb5", 2, 32, 0xff79c2ec);
		}
	}
}