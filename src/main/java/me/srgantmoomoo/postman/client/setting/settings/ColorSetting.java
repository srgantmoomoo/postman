package me.srgantmoomoo.postman.client.setting.settings;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting implements com.lukflug.panelstudio.settings.ColorSetting {

	private boolean rainbow;
	private JColor value;

	public ColorSetting (String name, Module parent, final JColor value) {
		this.name = name;
		this.parent = parent;
		this.value = value;
	}
	
	public JColor getValue() {
		if (rainbow) {
			return getRainbow(0, this.getColor().getAlpha());
		}
		return this.value;
	}

	public static JColor getRainbow(int incr, int alpha) {
		JColor color =  JColor.fromHSB(((System.currentTimeMillis() + incr * 200)%(360*20))/(360f * 20),0.5f,1f);
		return new JColor(color.getRed(), color.getBlue(), color.getGreen(), alpha);
	}


	public void setValue (boolean rainbow, final JColor value) {
		this.rainbow = rainbow;
		this.value = value;
	}

	public long toInteger() {
		return this.value.getRGB();
	}

	public void fromInteger (long number) {
		this.value = new JColor(Math.toIntExact(number),true);
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
		setValue(getRainbow(), new JColor(value));
	}

	@Override
	public void setRainbow(boolean rainbow) {
		this.rainbow = rainbow;
	}
}