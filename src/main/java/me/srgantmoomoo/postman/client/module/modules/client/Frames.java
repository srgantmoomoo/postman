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
import net.minecraft.client.Minecraft;


public class Frames extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, new JColor(121, 193, 255, 255)); 

	public Frames() {
		super("frames", "thatweehoo", new Point(-3,29));
		this.addSettings(color);
	}
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, new FramesList());
	}
	
	private class FramesList implements HUDList {

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			if(Minecraft.getDebugFPS() <= 20) return ChatFormatting.RED + "fps "+ Minecraft.getDebugFPS();
			else return ChatFormatting.WHITE + "fps "+ Minecraft.getDebugFPS();
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