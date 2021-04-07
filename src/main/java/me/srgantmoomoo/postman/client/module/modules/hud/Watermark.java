package me.srgantmoomoo.postman.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;
import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.module.modules.pvp.AutoCrystal;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;


public class Watermark extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, Reference.POSTMAN_COLOR); 

	public Watermark() {
		super("watermark", "postman watermark!", new Point(-2,1), Category.HUD);
		this.addSettings(color);
	}
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, new WatermarkList());
	}
	
	private class WatermarkList implements HUDList {

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			return ChatFormatting.WHITE + Reference.NAME + " " + ChatFormatting.RESET + Reference.VERSION + " " + AutoCrystal.placing;
		}

		@Override
		public Color getItemColor(int index) {
			return color.getValue();
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