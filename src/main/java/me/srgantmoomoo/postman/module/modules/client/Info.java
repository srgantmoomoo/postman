package me.srgantmoomoo.postman.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Info extends Module {
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	public boolean on;
	
	public Info() {
		super("info", "classic hud", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(xaxis, yaxis);
	}
	private Minecraft mc = Minecraft.getMinecraft();
	ScaledResolution sr = new ScaledResolution(mc);
	FontRenderer fr = mc.fontRenderer;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
	if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
				if(on) {
					int totems;
					
					totems = mc.player.inventory.mainInventory.stream()
							.filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING)
							.mapToInt(ItemStack::getCount).sum();
					if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)
						totems++;
					final int[] counter = { 1 };
					
					//totem counter
					fr.drawStringWithShadow(totems + " ", (float) (1 + xaxis.getValue()), (float) (12 + yaxis.getValue()), 0xffffd700);
					
					//player count
					fr.drawStringWithShadow("players", (float) (1 + xaxis.getValue()), (float) (60 + yaxis.getValue()), 0xffffff);
					fr.drawStringWithShadow(mc.player.connection.getPlayerInfoMap().size() + " ", (float) (41 + xaxis.getValue()), (float) (60 + yaxis.getValue()), 0xffffff);
					
					//ping
					if (getPing() > 100) {
						fr.drawStringWithShadow("ping", (float) (1 + xaxis.getValue()), (float) (40 + yaxis.getValue()), 0xffe60000);
						fr.drawStringWithShadow(getPing() + " ", (float) (23 + xaxis.getValue()), (float) (40 + yaxis.getValue()), 0xffe60000);
					} else {
						fr.drawStringWithShadow("ping", (float) (1 + xaxis.getValue()), (float) (40 + yaxis.getValue()), 0xffffff);
						fr.drawStringWithShadow(getPing() + " ", (float) (23 + xaxis.getValue()), (float) (40 + yaxis.getValue()), 0xffffff);
					}

					//fps
					if (Minecraft.getDebugFPS() < 20) {
						fr.drawStringWithShadow("fps", (float) (1 + xaxis.getValue()), (float) (50 + yaxis.getValue()), 0xffe60000);
						fr.drawStringWithShadow(Minecraft.getDebugFPS() + " ", (float) (19 + xaxis.getValue()), (float) (50 + yaxis.getValue()), 0xffe60000);
					} else {
						fr.drawStringWithShadow("fps", (float) (1 + xaxis.getValue()), (float) (50 + yaxis.getValue()), 0xffffffff);
						fr.drawStringWithShadow(Minecraft.getDebugFPS() + " ", (float) (19 + xaxis.getValue()), (float) (50 + yaxis.getValue()), 0xffffffff);
					}

					//auto crystal
					// -- if on be green, otherwise red (same with surround)
					for (Module mod : Main.moduleManager.getModuleList()) {
						if (mod.getName().equals("autoCrystal") && mod.isToggled()) {
							fr.drawStringWithShadow("autoC:", (float) (1 + xaxis.getValue()), (float) (20 + yaxis.getValue()), 0xff00ff00);
							fr.drawStringWithShadow("on", (float) (32 + xaxis.getValue()), (float) (20 + yaxis.getValue()), 0xff00ff00);
						} else {
							if (mod.getName().equals("autoCrystal") && !mod.isToggled()) {
								fr.drawStringWithShadow("autoC:", (float) (1 + xaxis.getValue()), (float) (20 + yaxis.getValue()), 0xffe60000);
								fr.drawStringWithShadow("off", (float) (32 + xaxis.getValue()), (float) (20 + yaxis.getValue()), 0xffe60000);
							}
						}

						//surround
						if (mod.getName().equals("surround") && mod.isToggled()) {
							fr.drawStringWithShadow("srnd:", (float) (1 + xaxis.getValue()), (float) (30 + yaxis.getValue()), 0xff00ff00);
							fr.drawStringWithShadow("on", (float) (28 + xaxis.getValue()), (float) (30 + yaxis.getValue()), 0xff00ff00);
						} else {
							if (mod.getName().equals("surround") && !mod.isToggled()) {
								fr.drawStringWithShadow("srnd:", (float) (1 + xaxis.getValue()), (float) (30 + yaxis.getValue()), 0xffe60000);
								fr.drawStringWithShadow("off", (float) (28 + xaxis.getValue()), (float) (30 + yaxis.getValue()), 0xffe60000);
							}
						}
					}
					counter[0]++;
				}
					
				}
	}
	
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
	
	public void onEnable() {
		super.onEnable();
		on = true;
	}
	
	public void onDisable() {
		super.onDisable();
		on = false;
	}

}
