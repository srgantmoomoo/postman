package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.settings.ModeSetting;
import me.srgantmoomoo.postman.client.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArrayListt extends Module {
	public ModeSetting sort = new ModeSetting("sort", this, "left", "left", "right");
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 70, -1000, 1000, 10);
	public BooleanSetting right = new BooleanSetting("right", this, false);
	public BooleanSetting showHidden = new BooleanSetting("showHidden", this, false);
	public boolean on;
	//default, min, max, increments.
	
	public ArrayListt() {
		super("arrayList", "classic hud", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(showHidden, right, xaxis, yaxis);
	}
	private Minecraft mc = Minecraft.getMinecraft();
	ScaledResolution sr = new ScaledResolution(mc);
	FontRenderer fr = mc.fontRenderer;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
	if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
				if(on) {
				int y = 1;
				final int[] counter = { 1 };
				for (Module mod : Main.moduleManager.getModuleList()) {
					if (!mod.getName().equalsIgnoreCase("watermark") && !showHidden.isEnabled() 
							&& !mod.getName().equalsIgnoreCase("armorHud")
							&& !mod.getName().equalsIgnoreCase("hey!")
							&& !mod.getName().equalsIgnoreCase("tabGui")
							&& !mod.getName().equalsIgnoreCase("info")
							&& !mod.getName().equalsIgnoreCase("inventory")
							&& !mod.getName().equalsIgnoreCase("postman")
							&& !mod.getName().equalsIgnoreCase("keyStrokes")
							&& !mod.getName().equalsIgnoreCase("arrayList")
							&& !mod.getName().equalsIgnoreCase("discordRp")
							&& !mod.getName().equalsIgnoreCase("mainMenuInfo") 
							&& !mod.getName().equalsIgnoreCase("clickGui")
							&& !mod.getName().equalsIgnoreCase("Esp2dHelper") 
							&& mod.isToggled()) {
						if(right.isEnabled()) {
						fr.drawStringWithShadow(mod.getName() + "<", sr.getScaledWidth() - fr.getStringWidth(">" + mod.getName()) - (float) xaxis.getValue(), y + (float) yaxis.getValue(), rainbow(counter[0] * -300));
						}else
							fr.drawStringWithShadow(">" + mod.getName(), 1 + (float) xaxis.getValue(), y + (float) yaxis.getValue(), rainbow(counter[0] * -300));
						y += fr.FONT_HEIGHT;
						counter[0]++;
				}
					
					if(showHidden.isEnabled()) {
						if (!mod.getName().equalsIgnoreCase("Esp2dHelper") && mod.isToggled()) {
							if(right.isEnabled()) {
							fr.drawStringWithShadow(mod.getName() + "<", sr.getScaledWidth() - fr.getStringWidth(">" + mod.getName()) - (float) xaxis.getValue(), y + (float) yaxis.getValue(), rainbow(counter[0] * -300));
							}else
								fr.drawStringWithShadow(">" + mod.getName(), 1 + (float) xaxis.getValue(), y + (float) yaxis.getValue(), rainbow(counter[0] * -300));
							y += fr.FONT_HEIGHT;
							counter[0]++;
					}
					}
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
	public static int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
	}

}
