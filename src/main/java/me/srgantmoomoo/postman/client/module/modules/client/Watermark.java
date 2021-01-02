package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.api.util.Reference;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraftforge.fml.common.Mod.EventHandler;

public class Watermark extends HudModule {
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	
	public boolean on;
	
	public ColorSetting colorSettings = new ColorSetting("colorSettings", this, new JColor(121, 193, 255, 0));

	
	public Watermark() {
		super ("watermark", "yeeyee", new Point(0,0));
	}
	
	@Override
	public void populate (Theme theme) {
		component=new ListComponent(getName(),theme.getPanelRenderer(),position,new WatermarkList());
	}
	
	@EventHandler
	public void onEnable() {
	}
	
	private class WatermarkList implements HUDList {
		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			return "postman "+ Reference.VERSION;
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
