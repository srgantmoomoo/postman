package me.srgantmoomoo.postman.module.modules.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.api.util.Wrapper;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.ModeSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Written by @SrgantMooMoo on November 6th, 2020
 */

public class InventoryViewer extends Module {
	boolean on;
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "compact", "none");
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	
	public InventoryViewer() {
		super ("inventory", "draws line to entitys and/or sotrage", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(mode, xaxis, yaxis);
	}
	private static final Minecraft mc = Minecraft.getMinecraft();
	private final ResourceLocation inventorylogo = new ResourceLocation(Refrence.MOD_ID, "textures/christmancircle.png");
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		ScaledResolution sr = new ScaledResolution(mc);
		if(on) {
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			mc.renderEngine.bindTexture(inventorylogo);
			if(mode.getMode().equals("normal")) {
			Gui.drawScaledCustomSizeModalRect((int) (sr.getScaledWidth() - 106 + xaxis.getValue()), (int) (2 + yaxis.getValue()), 50, 0, 50, 50, 50, 50, 50, 50);
			}else if(mode.getMode().equals("compact")) {
				Gui.drawScaledCustomSizeModalRect((int) (sr.getScaledWidth() - 102 + xaxis.getValue()), (int) (1 + yaxis.getValue()), 50, 0, 50, 50, 50, 50, 50, 50);
			}
			}
		}
	
	if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
		drawInventory(10, 10);
	}
	}
	
	public void drawInventory(int x, int y) {
		ScaledResolution sr = new ScaledResolution(mc);
		if(on) {
		
		if(mode.getMode().equals("normal")) {
			GlStateManager.enableAlpha();
			Gui.drawRect((int) (sr.getScaledWidth() - 163 + (float) xaxis.getValue()), (int) (1 + yaxis.getValue()), (int) (sr.getScaledWidth() - 1 + xaxis.getValue()), (int) (55 + yaxis.getValue()), 0x4079c2ec); // 0x2fffc3b1
			GlStateManager.disableAlpha();
		}else if(mode.getMode().equals("compact")) {
			GlStateManager.enableAlpha();
			Gui.drawRect((int) (sr.getScaledWidth() - 155 + (float) xaxis.getValue()), (int) (1 + yaxis.getValue()), (int) (sr.getScaledWidth() - 1 + xaxis.getValue()), (int) (53 + yaxis.getValue()), 0x4079c2ec); //0x40009dff
			GlStateManager.disableAlpha();
		}

		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
		for (int size = items.size(), item = 9; item < size; ++item) {
			if(mode.getMode().equals("normal")) {
				final int slotX = (int) (sr.getScaledWidth() - 163 + 1 + xaxis.getValue() + item % 9 * 18);
				final int slotY = (int) (1 + 1 + yaxis.getValue() + (item / 9 - 1) * 18);
				RenderHelper.enableGUIStandardItemLighting();
				mc.getRenderItem().renderItemAndEffectIntoGUI(items.get(item), slotX, slotY);
				mc.getRenderItem().renderItemOverlays(mc.fontRenderer, items.get(item), slotX, slotY);
				RenderHelper.disableStandardItemLighting();
			}else if(mode.getMode().equals("compact")) {
				final int slotX = (int) (sr.getScaledWidth() - 155 + 1 + xaxis.getValue() + item % 9 * 17);
				final int slotY = (int) (1 + 1 + yaxis.getValue() + (item / 9 - 1) * 17);
				RenderHelper.enableGUIStandardItemLighting();
				mc.getRenderItem().renderItemAndEffectIntoGUI(items.get(item), slotX, slotY);
				mc.getRenderItem().renderItemOverlays(mc.fontRenderer, items.get(item), slotX, slotY);
				RenderHelper.disableStandardItemLighting();
			}
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