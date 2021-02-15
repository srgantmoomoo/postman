package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;

public class Coords extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, new JColor(172, 172, 172, 255)); 

	public Coords() {
		super("coords", "shows ur coords on ur hud.", new Point(100,1));
		this.addSettings(color);
	}
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, new CoordsList());
	}
	
	private class CoordsList implements HUDList {
		
		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + mc.player.getPosition().getX()
					+ ChatFormatting.RESET + "(y)" + ChatFormatting.WHITE + mc.player.getPosition().getY()
					+ ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + mc.player.getPosition().getZ();
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
