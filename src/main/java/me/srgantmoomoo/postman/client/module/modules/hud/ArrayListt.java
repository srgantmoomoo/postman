package me.srgantmoomoo.postman.client.module.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayListt extends HudModule {
	private final ModuleArrayList list=new ModuleArrayList();
	
	public final ColorSetting color = new ColorSetting("color", this, new JColor(255, 255, 255, 255));
	public final ModeSetting sortHeight = new ModeSetting("sortHeight", this, "betic", "betic", "up", "down");
	public final ModeSetting sortLength = new ModeSetting("sortLength", this, "left", "left", "right");
	public final BooleanSetting forgeHax = new BooleanSetting("forgeHax", this, true);
	public final BooleanSetting showHidden = new BooleanSetting("showHidden", this, false);

	public ArrayListt() {
		super("arrayList", "shows currently enabled modules.", new Point(-2, 69), Category.HUD);
		this.addSettings(color, sortHeight, sortLength, showHidden, forgeHax);
	}
    
    @Override
    public void populate (Theme theme) {
    	component = new ListComponent(getName(),theme.getPanelRenderer(),position,list);
    }

    public void onRender() {
    	list.activeModules.clear();
    	for (Module module: ModuleManager.getModules()) {
    		if(!showHidden.isEnabled()) {
    			if (module.isToggled() && !module.getCategory().equals(Category.HUD) && !module.getCategory().equals(Category.CLIENT) && !module.getName().equals("baritone")) {
    				list.activeModules.add(module);
    			}
    		}else
    			if (module.isToggled() && !module.getName().equalsIgnoreCase("Esp2dHelper")) list.activeModules.add(module);
    	}
    	if(sortHeight.is("up") || sortHeight.is("down")) {
    	list.activeModules.sort(Comparator.comparing(module -> -Main.clickGui.guiInterface.getFontWidth(module.getName())));
    	}
    }

    private class ModuleArrayList implements HUDList {

		public final List<Module> activeModules= new ArrayList <>();
		
		@Override
		public int getSize() {
			return activeModules.size();
		}
		
		@Override
		public String getItem(int index) {
			Module module = activeModules.get(index);
			if(forgeHax.isEnabled() && sortLength.is("right")) return module.getName() + "<";
			else if(forgeHax.isEnabled() && sortLength.is("left")) return ">" + module.getName();
			else return module.getName();
		}
		
		@Override
		public Color getItemColor(int index) {
			JColor c = color.getValue();
			return Color.getHSBColor(c.getHue() + (color.getRainbow() ? .05f * index : 0), (color.getRainbow() ? 0.5f : c.getSaturation()), c.getBrightness());
		}
		
		@Override
		public boolean sortUp() {
			return sortHeight.is("up");
		}

		@Override
		public boolean sortRight() {
			return sortLength.is("right");
		}
	}
}