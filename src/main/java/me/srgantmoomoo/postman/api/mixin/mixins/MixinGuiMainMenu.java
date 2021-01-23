package me.srgantmoomoo.postman.api.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.api.util.Reference;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.gui.FontRenderer;
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
	  ResourceLocation postman = new ResourceLocation(Reference.MOD_ID, "textures/postmancircle.png");
	    this.mc.getTextureManager().bindTexture(postman);
	    drawScaledCustomSizeModalRect(-2, -4, 60, 0, 60, 60, 60, 60, 60, 60);
	FontRenderer fr = mc.fontRenderer;
    //Gui.drawRect(2, 2, 4 + 4, 4, 1963986960);
    fr.drawStringWithShadow(TextFormatting.ITALIC + "postman", 58, 28, 0xff79c2ec);
    fr.drawStringWithShadow("made by SrgantMooMoo!", 58, 36, 0xffffffff);
    fr.drawStringWithShadow("ur on version" + " " + Reference.VERSION + "!", 58, 44, 0xffffffff);
    fr.drawStringWithShadow("https://moomooooo.github.io/postman/", 58, 4, 0x808080); //0xff0202ff
    fr.drawStringWithShadow("https://github.com/moomooooo/postman", 58, 12, 0x808080);
    fr.drawStringWithShadow("https://discord.gg/Jd8EmEuhb5", 58, 20, 0x808080);
	  }
	}
}

//s889fd2js900
//https://discord.gg/Jd8EmEuhb5
//https://github.com/moomooooo/postman.git
//without shadow 0xff0000d8