package me.srgantmoomoo.postman.client.module.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;

import java.awt.*;


public class KillAuraHud extends HudModule {
	private final KillAuraInfoList list=new KillAuraInfoList();
	
	public final ColorSetting color = new ColorSetting("color", this, new JColor(230, 0, 0, 255));
	public final BooleanSetting sort = new BooleanSetting("sortRight", this, false);


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
			if (ModuleManager.isModuleEnabled("killAura")) return ChatFormatting.GREEN + "killA" +  " on";
			else return "KA" + " off";
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
