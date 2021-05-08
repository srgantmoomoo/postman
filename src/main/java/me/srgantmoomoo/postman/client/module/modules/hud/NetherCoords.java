package me.srgantmoomoo.postman.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;

public class NetherCoords extends HudModule{

	public ColorSetting color = new ColorSetting("color", this, new JColor(255, 72, 72, 255)); 
	public BooleanSetting sort = new BooleanSetting("sortRight", this, false);
	
	public NetherCoords() {
		super("netherCoords", "shows ur coords in nether on ur hud.", new Point(122, 50), Category.HUD);
		this.addSettings(sort, color);
	}

	@Override
	public void populate(Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, new NetherCoordsList());
	}
	
	private class NetherCoordsList implements HUDList {
		
		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			if (mc.player.dimension == -1) {
				return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posX * 8f)
						+ ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posZ * 8f);
			}
			return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posX / 8f)
			+ ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posZ / 8f);
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