package me.srgantmoomoo.postman.module.modules.client;

import java.awt.Color;
import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.settings.ColorSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;

public class Watermark extends HudModule {
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	private ColorSetting color;
	public ColorSetting colorSettings = new ColorSetting("colorSettings", this, new JColor(121, 193, 255, 255));

	
	public Watermark() {
		super ("watermark", "yeeyee", new Point(0,0));
	}
	
	@Override
	public void populate (Theme theme) {
		component=new ListComponent(getName(),theme.getPanelRenderer(),position,new WatermarkList());
	}
	
	
	private class WatermarkList implements HUDList {
		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			return "postman "+ Refrence.VERSION;
		}

		@Override
		public Color getItemColor(int index) {
			return colorSettings.getValue();
		}

		@Override
		public boolean sortUp() {
			return false;
		}

		@Override
		public boolean sortRight() {
			return false;
		}
	}
}
