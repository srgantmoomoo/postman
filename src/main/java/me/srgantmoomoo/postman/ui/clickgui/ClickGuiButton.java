package me.srgantmoomoo.postman.ui.clickgui;

import java.util.List;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ClickGuiButton extends GuiButton {
	
	int buttonWidth = 10;
	int buttonHeight = 10;
	int v;
	
	public ClickGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, buttonText);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(visible) {
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		}
		if(hovered) {
			v = 0xff888888;
		}else {
			v = 0xffffffff;
		}
		//drawRect(this.x + 90, this.y + 1, this.width, this.height, v);
		
		int count = 0;
		Category player = Category.PLAYER;
		List<Module> modules = ModuleManager.getModulesByCategory(player);
			for(Module m : modules) {
				this.drawCenteredString(mc.fontRenderer, m.getName(), this.x, this.y + count * 14, v);
				count++;
			}
	}

}
