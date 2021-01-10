package me.srgantmoomoo.postman.client.setting.settings;

import java.awt.Color;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.Setting;

public class ColorSetting extends Setting implements com.lukflug.panelstudio.settings.ColorSetting {

	private boolean rainbow;
	private JColor value;
	
	public ColorSetting (String name, Module parent, final JColor value) {
		this.name = name;
		this.parent = parent;
		this.value=value;
	}
	
	public JColor getValue() {
		if (rainbow) {
			return JColor.fromHSB((System.currentTimeMillis()%(360*20))/(360f * 20),0.5f,1f);
		}
		return this.value;
	}
	
	public static int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
	}
	
	public void setValue (boolean rainbow, final JColor value) {
		this.rainbow = rainbow;
		this.value = value;
	}
	
	public int toInteger() {
		return this.value.getRGB()&0xFFFFFF+(rainbow?1:0)*0x1000000;
	}
	
	public void fromInteger (int number) {
		this.value = new JColor(number&0xFFFFFF);
		this.rainbow = ((number&0x1000000)!=0);
	}
	
	public JColor getColor() {
		return this.value;
	}

	@Override
	public boolean getRainbow() {
		return this.rainbow;
	}

	@Override
	public void setValue(Color value) {
		setValue(getRainbow(),new JColor(value));
	}

	@Override
	public void setRainbow(boolean rainbow) {
		this.rainbow=rainbow;
	}
}