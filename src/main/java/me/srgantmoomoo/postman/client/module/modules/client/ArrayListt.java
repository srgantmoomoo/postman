package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;

public class ArrayListt extends HudModule {
	private ModuleArrayList list=new ModuleArrayList();
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(103, 167, 221, 255)); 

	public ArrayListt() {
		super("arrayList", "twobee2twotee", new Point(-3,59));
		this.addSettings(color);
	}
    
    @Override
    public void populate (Theme theme) {
    	component = new ListComponent(getName(),theme.getPanelRenderer(),position,list);
    }

    public void onRender() {
    	list.activeModules.clear();
    	for (Module module: ModuleManager.getModules()) {
    		if (module.isToggled()) list.activeModules.add(module);
    	}
    	list.activeModules.sort(Comparator.comparing(module -> -Main.getInstance().clickGui.guiInterface.getFontWidth(module.getName())));
    }

    private class ModuleArrayList implements HUDList {

		public List<Module> activeModules=new ArrayList<Module>();
		
		@Override
		public int getSize() {
			return activeModules.size();
		}
	
		@Override
		public String getItem(int index) {
			Module module = activeModules.get(index);
			return module.getName();
		}
	
		@Override
		public Color getItemColor(int index) {
			JColor c = color.getValue();
			return Color.getHSBColor(c.getHue() + (color.getRainbow() ? .02f * index : 0), c.getSaturation(), c.getBrightness());
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