package me.srgantmoomoo.postman.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Watermark extends Module {
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	public Watermark() {
		super("watermark", "classic hud", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(xaxis, yaxis);
	}
	ScaledResolution sr = new ScaledResolution(mc);
	FontRenderer fr = mc.fontRenderer;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
	if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
		if(on) {
		fr.drawStringWithShadow(Refrence.NAME, (float) (1 + xaxis.getValue()), (float) (2 + yaxis.getValue()), 0xffffffff);
		fr.drawStringWithShadow(Refrence.VERSION, (float) (42 + xaxis.getValue()), (float) (2 + yaxis.getValue()), 0xff79c2ec);
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

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
}
