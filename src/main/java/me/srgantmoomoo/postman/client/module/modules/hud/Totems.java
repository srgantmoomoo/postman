package me.srgantmoomoo.postman.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class Totems extends HudModule {
	private TotemList list=new TotemList();
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(218, 165, 32, 255)); 
	public BooleanSetting sort = new BooleanSetting("sortRight", this, false);

	public Totems() {
		super("totems", "shows how many totems u have on ur hud.", new Point(-3,11), Category.HUD);
		this.addSettings(color);
	}
	
	   public void onRender() {
	    	list.totems = mc.player.inventory.mainInventory.stream()
	    			.filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING)
	    			.mapToInt(ItemStack::getCount).sum();
	    	if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)
	    		list.totems++;
	    }
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, list);
	}
	
	private class TotemList implements HUDList {

		public int totems=0;
		
		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			return "" + totems;
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