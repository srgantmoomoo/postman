package me.srgantmoomoo.postman.client.module.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;

import java.awt.*;

public class Coords extends HudModule {
	public final ColorSetting color = new ColorSetting("color", this, new JColor(172, 172, 172, 255));
	public final BooleanSetting sort = new BooleanSetting("sortRight", this, false);


	public Coords() {
		super("coords", "shows ur coords on ur hud.", new Point(122, 57), Category.HUD);
		this.addSettings(sort, color);
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
			if (mc.player.dimension == -1) {
				return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posX * 8f)
						+ ChatFormatting.RESET + "(y)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posY)
						+ ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posZ * 8f);
			}
			return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posX)
					+ ChatFormatting.RESET + "(y)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posY)
					+ ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posZ);
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
			return sort.isEnabled();
		}
	}
}
