package me.srgantmoomoo.postman.ui.clickgui;

import java.io.IOException;
import java.util.List;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

public class ClickGui extends GuiScreen {
	private Minecraft mc = Minecraft.getMinecraft();
	ScaledResolution sr = new ScaledResolution(mc);
	int player;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		Gui.drawRect(80, 1, 160, 140, 0xffffffff);
		Gui.drawRect(180, 1, 260, 196, 0xffffffff);
		Gui.drawRect(280, 1, 360, 140, 0xffffffff);
		Gui.drawRect(380, 1, 460, 113, 0xffffffff);
		Gui.drawRect(480, 1, 560, 154, 0xffffffff);

		// category's
		fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + "player", 104, 3, 0xff79c2ec);
		fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + "render", 202, 3, 0xff79c2ec);
		fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + "pvp", 310, 3, 0xff79c2ec);
		fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + "exploits", 401, 3, 0xff79c2ec);
		fontRenderer.drawStringWithShadow(TextFormatting.ITALIC + "client", 506, 3, 0xff79c2ec);
				
    	// module's
		int count = 0;
		count = 0;
		Category player = Category.PLAYER;
		List<Module> modules = ModuleManager.getModulesByCategory(player);
			for(Module m : modules) {
				this.drawCenteredString(mc.fontRenderer, m.getName(), 119, 16 + count * 14, 0xffffffff);
				count++;
			}
			
			count = 0;
			Category render = Category.RENDER;
			List<Module> modules1 = ModuleManager.getModulesByCategory(render);
				for(Module m : modules1) {
					this.drawCenteredString(mc.fontRenderer, m.getName(), 219, 16 + count * 14, 0xffffffff);
					count++;
				}
				
				count = 0;
				Category pvp = Category.PVP;
				List<Module> modules11 = ModuleManager.getModulesByCategory(pvp);
					for(Module m : modules11) {
						this.drawCenteredString(mc.fontRenderer, m.getName(), 319, 16 + count * 14, 0xffffffff);
						count++;
					}
					
					count = 0;
					Category exploits = Category.EXPLOITS;
					List<Module> modules111 = ModuleManager.getModulesByCategory(exploits);
						for(Module m : modules111) {
							this.drawCenteredString(mc.fontRenderer, m.getName(), 419, 16 + count * 14, 0xffffffff);
							count++;
						}
						
						count = 0;
						Category client = Category.CLIENT;
						List<Module> modules1111 = ModuleManager.getModulesByCategory(client);
							for(Module m : modules1111) {
								this.drawCenteredString(mc.fontRenderer, m.getName(), 519, 16 + count * 14, 0xffffffff);
								count++;
							}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}