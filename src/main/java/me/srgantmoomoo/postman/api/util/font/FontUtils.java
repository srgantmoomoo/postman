package me.srgantmoomoo.postman.api.util.font;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.util.render.JColor;
import net.minecraft.client.Minecraft;

public class FontUtils {

	private static final Minecraft mc = Minecraft.getMinecraft();
	
	public static
	void drawStringWithShadow(boolean customFont, String text, int x, int y, JColor color) {
		if(customFont) {
			Main.customFontRenderer.drawStringWithShadow(text, x, y, color);
		}
		else {
			mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
		}
	}

	public static int getStringWidth(boolean customFont, String string) {
		if (customFont) {
			return Main.customFontRenderer.getStringWidth(string);
		}
		else {
			return mc.fontRenderer.getStringWidth(string);
		}
	}

	public static int getFontHeight(boolean customFont) {
		if (customFont) {
			return Main.customFontRenderer.getHeight();
		}
		else {
			return mc.fontRenderer.FONT_HEIGHT;
		}
	}
}