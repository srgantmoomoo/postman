package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Color;
import java.awt.Point; 

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.api.util.Reference;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.ui.clickgui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class InventoryViewer extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, new JColor(121, 193, 255, 100)); 
	public ModeSetting sizee = new ModeSetting("size", this, "normal", "normal", "compact");
    
    public InventoryViewer() {
    	super("inventoryViewer","fdhusnapeepeenisggaewiojwajmvdj", new Point(0,10));
    	this.addSettings(color, sizee);
    }
    
    @Override
    public void populate (Theme theme) {
    	component = new InventoryViewerComponent(theme);
    }

    private class InventoryViewerComponent extends HUDComponent {

		public InventoryViewerComponent (Theme theme) {
			super(getName(),theme.getPanelRenderer(),InventoryViewer.this.position);
		}
		
		@Override
		public void render (Context context) {
			ScaledResolution sr = new ScaledResolution(mc);
			
			/*final ResourceLocation inventorylogo = new ResourceLocation(Reference.MOD_ID, "textures/postmancircle.png");
			mc.renderEngine.bindTexture(inventorylogo); {
			if(sizee.getMode().equals("normal")) {
				Gui.drawScaledCustomSizeModalRect(context.getPos().x + sr.getScaledWidth() - 600, context.getPos().y + 2, 50, 0, 50, 50, 50, 50, 50, 50);
				}else {
					Gui.drawModalRectWithCustomSizedTexture(50, 50, 50, 50, 50, 50, 50, 50);
				}
			}*/
			
			super.render(context);
			Color bgcolor=new JColor(color.getValue(),100);
			context.getInterface().fillRect(context.getRect(),bgcolor,bgcolor,bgcolor,bgcolor);
		
	        NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
	        for (int size = items.size(), item = 9; item < size; ++item) {
	        	if(sizee.getMode().equals("normal")) {
	            int slotX = context.getPos().x + 1 + item % 9 * 18;
	            int slotY = context.getPos().y + 1 + (item / 9 - 1) * 18;
				ClickGui.renderItem(items.get(item),new Point(slotX,slotY));
	        	}else {
	        		int slotX = context.getPos().x + 1 + item % 9 * 17;
		            int slotY = context.getPos().y + 1 + (item / 9 - 1) * 17;
					ClickGui.renderItem(items.get(item),new Point(slotX,slotY));
	        	}
	        }
		}

		@Override
		public int getWidth (Interface inter) {
			if(sizee.getMode().equals("normal")) {
			return 162;
			}else {
				return 154;
			}
		}

		@Override
		public void getHeight (Context context) {
			if(sizee.getMode().equals("normal")) {
			context.setHeight(54);
			}else {
				context.setHeight(52);
			}
		}
	}
}