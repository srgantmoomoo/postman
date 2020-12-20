package me.srgantmoomoo.postman.module.modules.hud;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.ModeSetting;
import me.srgantmoomoo.api.util.Refrence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClassicHud extends Module {
	public boolean enabled;
	public boolean constant;
	public ModeSetting arrayList = new ModeSetting("arrayList", this, "betic", "betic", "size");
	public ModeSetting inventory = new ModeSetting("inv", this, "normal", "normal", "compact", "none");
	public BooleanSetting coords = new BooleanSetting("coords", this, true);
	public BooleanSetting hey = new BooleanSetting("hey", this, true);
	public BooleanSetting postman = new BooleanSetting("thepostman", this, true);

	public ClassicHud() {
		super("hud", "classic hud", Keyboard.KEY_NONE, Category.CLIENT);
		constant = true;
		this.addSettings(inventory, postman, hey, coords);
	}

	public void onDisable() {
		enabled = false;
	}

	@SubscribeEvent
	public void renderOverlay1(RenderGameOverlayEvent event) {
		if (constant) {
			for (Module mod : Main.moduleManager.getModuleList()) {
				if (mod.getName().equals("hud") && mod.isToggled()) {
					enabled = true;
				}
				if (mod.getName().equals("darkHud") && mod.isToggled()) {
					toggled = false;
					enabled = false;
				}
				if (mod.getName().equals("lightHud") && mod.isToggled()) {
					toggled = false;
					enabled = false;
				}
			}
		}
	}

	private Minecraft mc = Minecraft.getMinecraft();

	int totems;

	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
			if (Minecraft.getMinecraft().fontRenderer.getStringWidth(
					arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
				return -1;
			}
			if (Minecraft.getMinecraft().fontRenderer.getStringWidth(
					arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
				return 1;
			}
			return 0;
		}
	}

	private final ResourceLocation inventorylogo = new ResourceLocation(Refrence.MOD_ID, "textures/postmancircle.png");
	private final ResourceLocation thepostman = new ResourceLocation(Refrence.MOD_ID, "textures/thepostman.png");

	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		
		if(arrayList.getMode().equals("size")) {
		Collections.sort(Main.moduleManager.modules, new ModuleComparator());
		}
		
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRenderer;

		if (enabled) {

			//watermark
			if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
				mc.renderEngine.bindTexture(inventorylogo);
				if(inventory.getMode().equals("normal")) {
				Gui.drawScaledCustomSizeModalRect(sr.getScaledWidth() - 106, 2, 50, 0, 50, 50, 50, 50, 50, 50);
				}else if(inventory.getMode().equals("compact")) {
					Gui.drawScaledCustomSizeModalRect(sr.getScaledWidth() - 102, 1, 50, 0, 50, 50, 50, 50, 50, 50);
				}
			}
			
			//thepostman
			if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
				if(postman.isEnabled()) {
				mc.renderEngine.bindTexture(thepostman);
				Gui.drawScaledCustomSizeModalRect(-10, sr.getScaledHeight() - 150, 0, 0, 150, 150, 150, 150, 150, 150);
				}
			}

			if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

				totems = mc.player.inventory.mainInventory.stream()
						.filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING)
						.mapToInt(ItemStack::getCount).sum();
				if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)
					totems++;
				final int[] counter = { 1 };
				
				//inventory viewer 
				drawInventory(10, 10);

				//title
				fr.drawStringWithShadow(Refrence.NAME, 1, 2, 0xffffffff);
				fr.drawStringWithShadow(Refrence.VERSION, 42, 2, 0xff79c2ec); // 0xff808080
				
				//hey!
				if(hey.isEnabled()) {
				if(coords.isEnabled()) {
				fr.drawStringWithShadow("hey" + " " + mc.player.getName() + "!", sr.getScaledWidth() - fr.getStringWidth("hey" + " " + mc.player.getName() + "!") - 1, sr.getScaledHeight() - 20, 0xffffff);
				}else {
					fr.drawStringWithShadow("hey" + " " + mc.player.getName() + "!", sr.getScaledWidth() - fr.getStringWidth("hey" + " " + mc.player.getName() + "!") - 1, sr.getScaledHeight() - 10, 0xffffff);
					}
				}
				
				//coords
				if(coords.isEnabled()) {
					fr.drawStringWithShadow("(x)" + mc.player.getPosition().getX() + " " + "(y)" + mc.player.getPosition().getY() + " " + "(z)" + mc.player.getPosition().getZ(), sr.getScaledWidth() - fr.getStringWidth("(x)" + mc.player.getPosition().getX() + " " + "(y)" + mc.player.getPosition().getY() + " " + "(z)" + mc.player.getPosition().getZ()) - 1, sr.getScaledHeight() - 10, 0xffffff);
				}
				
				//totem counter
				fr.drawStringWithShadow(totems + " ", 1, 12, 0xffffd700);
				
				//player count
				fr.drawStringWithShadow("players", 1, 60, 0xffffff);
				fr.drawStringWithShadow(mc.player.connection.getPlayerInfoMap().size() + " ", 41, 60, 0xffffff);
				
				//tps
				//fr.drawStringWithShadow("tps", 1, 60, 0xffffff);
				//fr.drawStringWithShadow("20", 18, 60, 0xffffff);
				
				//ping
				if (getPing() > 100) {
					fr.drawStringWithShadow("ping", 1, 40, 0xffe60000);
					fr.drawStringWithShadow(getPing() + " ", 23, 40, 0xffe60000);
				} else {
					fr.drawStringWithShadow("ping", 1, 40, 0xffffff);
					fr.drawStringWithShadow(getPing() + " ", 23, 40, 0xffffff);
				}

				//fps
				if (Minecraft.getDebugFPS() < 20) {
					fr.drawStringWithShadow("fps", 1, 50, 0xffe60000);
					fr.drawStringWithShadow(Minecraft.getDebugFPS() + " ", 19, 50, 0xffe60000);
				} else {
					fr.drawStringWithShadow("fps", 1, 50, 0xffffffff);
					fr.drawStringWithShadow(Minecraft.getDebugFPS() + " ", 19, 50, 0xffffffff);
				}

				//auto crystal
				// -- if on be green, otherwise red (same with surround)
				for (Module mod : Main.moduleManager.getModuleList()) {
					if (mod.getName().equals("autoCrystal") && mod.isToggled()) {
						fr.drawStringWithShadow("autoC:", 1, 20, 0xff00ff00);
						fr.drawStringWithShadow("on", 32, 20, 0xff00ff00);
					} else {
						if (mod.getName().equals("autoCrystal") && !mod.isToggled()) {
							fr.drawStringWithShadow("autoC:", 1, 20, 0xffe60000);
							fr.drawStringWithShadow("off", 32, 20, 0xffe60000);
						}
					}

					//surround
					if (mod.getName().equals("surround") && mod.isToggled()) {
						fr.drawStringWithShadow("srnd:", 1, 30, 0xff00ff00);
						fr.drawStringWithShadow("on", 28, 30, 0xff00ff00);
					} else {
						if (mod.getName().equals("surround") && !mod.isToggled()) {
							fr.drawStringWithShadow("srnd:", 1, 30, 0xffe60000);
							fr.drawStringWithShadow("off", 28, 30, 0xffe60000);
						}
					}
				}
				counter[0]++;
			}

			//arraylist
			if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
				int y = 80;
				final int[] counter = { 1 };
				for (Module mod : Main.moduleManager.getModuleList()) {
					if (!mod.getName().equalsIgnoreCase("tabGui") && !mod.getName().equalsIgnoreCase("lightHud")
							&& !mod.getName().equalsIgnoreCase("darkHud")
							&& !mod.getName().equalsIgnoreCase("hud") && mod.isToggled()) {
						fr.drawStringWithShadow(">" + mod.getName(), 1, y, rainbow(counter[0] * -300));
						y += fr.FONT_HEIGHT;
						counter[0]++;
					}
				}
			}
		}
	}

	public static int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
	}

	public void drawInventory(int x, int y) {
		ScaledResolution sr = new ScaledResolution(mc);

		if(inventory.getMode().equals("normal")) {
			GlStateManager.enableAlpha();
			Gui.drawRect(sr.getScaledWidth() - 163, 1, sr.getScaledWidth() - 1, 55, 0x4079c2ec); // 0x2fffc3b1
			GlStateManager.disableAlpha();
		}else if(inventory.getMode().equals("compact")) {
			GlStateManager.enableAlpha();
			Gui.drawRect(sr.getScaledWidth() - 155, 1, sr.getScaledWidth() - 1, 53, 0x4079c2ec); // 0x2fffc3b1
			GlStateManager.disableAlpha();
		}

		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
		for (int size = items.size(), item = 9; item < size; ++item) {
			if(inventory.getMode().equals("normal")) {
				final int slotX = sr.getScaledWidth() - 163 + 1 + item % 9 * 18;
				final int slotY = 1 + 1 + (item / 9 - 1) * 18;
				RenderHelper.enableGUIStandardItemLighting();
				mc.getRenderItem().renderItemAndEffectIntoGUI(items.get(item), slotX, slotY);
				mc.getRenderItem().renderItemOverlays(mc.fontRenderer, items.get(item), slotX, slotY);
				RenderHelper.disableStandardItemLighting();
			}else if(inventory.getMode().equals("compact")) {
				final int slotX = sr.getScaledWidth() - 155 + 1 + item % 9 * 17;
				final int slotY = 1 + 1 + (item / 9 - 1) * 17;
				RenderHelper.enableGUIStandardItemLighting();
				mc.getRenderItem().renderItemAndEffectIntoGUI(items.get(item), slotX, slotY);
				mc.getRenderItem().renderItemOverlays(mc.fontRenderer, items.get(item), slotX, slotY);
				RenderHelper.disableStandardItemLighting();
			}
		}
	}
	
	/*
	 public void drawInventory(int x, int y) {
		ScaledResolution sr = new ScaledResolution(mc);

		GlStateManager.enableAlpha();
		Gui.drawRect(sr.getScaledWidth() - 155, 1, sr.getScaledWidth() - 1, 53, 0x40ffa6f1); // 0x2fffc3b1
		GlStateManager.disableAlpha();

		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
		for (int size = items.size(), item = 9; item < size; ++item) {
			final int slotX = sr.getScaledWidth() - 155 + 1 + item % 9 * 17;
			final int slotY = 1 + 1 + (item / 9 - 1) * 17;
			RenderHelper.enableGUIStandardItemLighting();
			mc.getRenderItem().renderItemAndEffectIntoGUI(items.get(item), slotX, slotY);
			mc.getRenderItem().renderItemOverlays(mc.fontRenderer, items.get(item), slotX, slotY);
			RenderHelper.disableStandardItemLighting();
		}
	}
	  
	 */

	public int getPing() {
		int p = -1;
		if (mc.player == null || mc.getConnection() == null
				|| mc.getConnection().getPlayerInfo(mc.player.getName()) == null) {
			p = -1;
		} else {
			p = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
		}
		return p;
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}