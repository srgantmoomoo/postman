package me.srgantmoomoo.postman.client.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.HudModule;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;


public class KillAuraHud extends HudModule {
	private KillAuraInfoList list=new KillAuraInfoList();
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(230, 0, 0, 255)); 
	public BooleanSetting sort = new BooleanSetting("sortRight", this, false);


	public KillAuraHud() {
		super("killAuraHud", "shows u if killAura is on or off.", new Point(-2, 49), Category.HUD);
		this.addSettings(color, sort);
	}
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, list);
	}
	
	private class KillAuraInfoList implements HUDList {

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			if (Main.INSTANCE.moduleManager.isModuleEnabled("killAura")) return ChatFormatting.GREEN + "killA" +  " on";
			else return "killA" + " off";
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