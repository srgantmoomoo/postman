package me.srgantmoomoo.postman.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArmorHud extends Module {
	public boolean on;
	public BooleanSetting ez = new BooleanSetting("ez", this, false);
	
	public ArmorHud() {
		super ("armorHud", "shows ur armor values on top of hotbar", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(ez);
	}
	private Minecraft mc = Minecraft.getMinecraft();

	
	 private static final RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();

	 	@SubscribeEvent
		public void renderOverlay(RenderGameOverlayEvent event) {
			if(on) {
			if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
		    GlStateManager.enableTexture2D();
		
		    ScaledResolution resolution = new ScaledResolution(mc);
		    int i = resolution.getScaledWidth() / 2;
		    int iteration = 0;
		    int y = resolution.getScaledHeight() - 55 - (mc.player.isInWater() ? 10 : 0);
		    for (ItemStack is : mc.player.inventory.armorInventory) {
		        iteration++;
		        if (is.isEmpty()) continue;
		        int x = i - 90 + (9 - iteration) * 24 - 25;
		        GlStateManager.enableDepth();
		        itemRender.zLevel = 200F;
		        itemRender.renderItemAndEffectIntoGUI(is, x, y);
		        if(ez.isEnabled()) {
		        itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, is, x, y, "ez");
		        }else {
		        	itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, is, x, y, "");
		        }
		        itemRender.zLevel = 0F;
		
		        GlStateManager.enableTexture2D();
		        GlStateManager.disableLighting();
		        GlStateManager.disableDepth();
		
		        String s = is.getCount() > 50 ? is.getCount() + "" : "";
		        mc.fontRenderer.drawStringWithShadow(s, x + 19 - 2 - mc.fontRenderer.getStringWidth(s), y + 9, 0xffffffff);
		        float green = ((float) is.getMaxDamage() - (float) is.getItemDamage()) / (float) is.getMaxDamage();
		        float red = 1 - green;
		        int dmg = 100 - (int) (red * 100);
		        mc.fontRenderer.drawStringWithShadow(dmg + "" + "%", x + 8 - mc.fontRenderer.getStringWidth(dmg + "" + "%") / 2, y - 8, 0xffffffff);
		    }
		
		    GlStateManager.enableDepth();
		    GlStateManager.disableLighting();
			}
			}
		}
	 	
	 	public void onEnable() {
	 		super.onEnable();
	 		on = true;
	 	}
	 	
	 	public void onDisable() {
	 		super.onDisable();
	 		on = false;
	 	}

}
