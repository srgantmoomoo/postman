/*package me.srgantmoomoo.api.util.font;

import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.postman.Main;
import net.minecraft.client.Minecraft;

public class FontUtils {
	private static final Minecraft mc = Minecraft.getMinecraft();
	
	public static float drawStringWithShadow(boolean customFont, String text, int x, int y, JColor color){
		if(customFont) return Main.fontRenderer.drawStringWithShadow(text, x, y, color);
		else return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
	}

	public static int getStringWidth(boolean customFont, String str){
		if (customFont) return Main.fontRenderer.getStringWidth(str);
		else return mc.fontRenderer.getStringWidth(str);
	}

	public static int getFontHeight(boolean customFont){
		if (customFont) return Main.fontRenderer.getHeight();
		else return mc.fontRenderer.FONT_HEIGHT;
	}
}*/
