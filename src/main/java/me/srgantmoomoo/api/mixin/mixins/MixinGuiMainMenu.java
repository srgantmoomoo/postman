package me.srgantmoomoo.api.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

/*
 * Written by @SrgantMooMoo on December 13th, 2020.
 */

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
  @Inject(method = {"drawScreen"}, at = {@At("TAIL")}, cancellable = true)
  public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
	  if(ModuleManager.getModuleByName("mainMenuInfo").isToggled()) {
	  ResourceLocation postman = new ResourceLocation(Refrence.MOD_ID, "textures/postmanbg.png");
	    this.mc.getTextureManager().bindTexture(postman);
	    drawModalRectWithCustomSizedTexture(2, 2, 0.0F, 0.0F, 60, 60 - 2, 60, (60 - 2));
	FontRenderer fr = mc.fontRenderer;
    //Gui.drawRect(2, 2, 4 + 4, 4, 1963986960);
    fr.drawStringWithShadow(TextFormatting.ITALIC + "postman", 64, 26, 0xff79c2ec);
    fr.drawStringWithShadow("made by SrgantMooMoo!", 64, 34, 0xffffffff);
    fr.drawStringWithShadow("ur on version" + " " + Refrence.VERSION + "!", 64, 42, 0xffffffff);
    fr.drawStringWithShadow("website coming soon!", 64, 2, 0xff0202ff);
    fr.drawStringWithShadow("https://github.com/moomooooo/postman.git5", 64, 10, 0xff0202ff);
    fr.drawStringWithShadow("https://discord.gg/Jd8EmEuhb5", 64, 18, 0xff0202ff);
	  }
	}
}

//s889fd2js900
//https://discord.gg/Jd8EmEuhb5
//https://github.com/moomooooo/postman.git
//without shadow 0xff0000d8