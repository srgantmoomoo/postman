package me.srgantmoomoo.postman.api.util.render;

import java.awt.Color;

import net.minecraft.client.renderer.GlStateManager;

/**
* @author lukflug
*/
// Why would anyone ever need to use JavaDoc properly?

public class JColor extends Color {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JColor (int rgb) {
		super(rgb);
	}
	
	public JColor (int rgba, boolean hasalpha) {
		super(rgba,hasalpha);
	}
	
	public JColor (int r, int g, int b) {
		super(r,g,b);
	}
	
	public JColor (int r, int g, int b, int a) {
		super(r,g,b,a);
	}
	
	public JColor (Color color) {
		super(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
	}
	
	public JColor (JColor color, int a) {
		super(color.getRed(),color.getGreen(),color.getBlue(),a);
	}
	
	public static JColor fromHSB (float hue, float saturation, float brightness) {
		return new JColor(Color.getHSBColor(hue,saturation,brightness));
	}
	
	public float getHue() {
		return RGBtoHSB(getRed(),getGreen(),getBlue(),null)[0];
	}
	
	public float getSaturation() {
		return RGBtoHSB(getRed(),getGreen(),getBlue(),null)[1];
	}
	
	public float getBrightness() {
		return RGBtoHSB(getRed(),getGreen(),getBlue(),null)[2];
	}
	
	public void glColor() {
		GlStateManager.color(getRed()/255.0f,getGreen()/255.0f,getBlue()/255.0f,getAlpha()/255.0f);
	}
}
