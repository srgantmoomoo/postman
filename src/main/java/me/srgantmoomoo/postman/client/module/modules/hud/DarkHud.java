package me.srgantmoomoo.postman.client.module.modules.hud;

import java.awt.Color;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.postman.api.util.Reference;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
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

public class DarkHud extends Module {
	public boolean enabled;
	public boolean constant;
	
	public DarkHud() {
		super ("darkHud", "dark themed hud", Keyboard.KEY_NONE, Category.CLIENT);
		constant = true;
		}
		
		public void onDisable() {
			enabled = false;
		}
		
		
		@SubscribeEvent
	    public void renderOverlay1(RenderGameOverlayEvent event) {
				if(constant) {
					for (Module mod : Main.moduleManager.getModuleList()) {
			    		if (mod.getName().equals("darkHud") && mod.isToggled()) {
			    			enabled = true;
			    		}
			    		if(mod.getName().equals("lightHud") && mod.isToggled()) {
			    			toggled = false;
			    			enabled = false;
			    		}
			    		if(mod.getName().equals("classicHud") && mod.isToggled()) {
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
					if(Minecraft.getMinecraft().fontRenderer.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
						return -1;
					}
					if(Minecraft.getMinecraft().fontRenderer.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
						return 1;
					}
					return 0;
				}
			}
			
			private final ResourceLocation watermark = new ResourceLocation(Reference.MOD_ID, "textures/watermark.png");
			
				@SubscribeEvent
			    public void renderOverlay(RenderGameOverlayEvent event) {
					
				//Collections.sort(Main.moduleManager.modules, new ModuleComparator());
				ScaledResolution sr = new ScaledResolution(mc);
			    FontRenderer fr = mc.fontRenderer;
			    
			    if(enabled) {
			    
			    		//watermark posty
			    		if(event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			    			mc.renderEngine.bindTexture(watermark);
			    			Gui.drawScaledCustomSizeModalRect(sr.getScaledWidth() - 106, 3, 50 , 0, 50, 50, 50, 50, 50, 50);

			    		}
			    		
			            if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			            	
			            	totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
							if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) totems++;
			            	final int[] counter = {1};
			            	
			        		drawInventory(10, 10);
			        		
			            	
			            	//title
			            	fr.drawStringWithShadow(Reference.NAME, 2, 2, 0xffffffff); 
			            	fr.drawStringWithShadow(Reference.VERSION, 56, 2, 0xffffa6f1); //0xff808080
			            	
			            	//totem counter
			            	fr.drawStringWithShadow(totems + " ", 2, 14, 0xffffd700);
			            	
			            	//ping
			            	if (getPing() > 100) {
			            	fr.drawStringWithShadow("ping", 2, 22, 0xffe60000);
			            	fr.drawStringWithShadow(getPing() + " ", 24, 22, 0xffe60000);
			            	}else {
			            		fr.drawStringWithShadow("ping", 2, 22, 0xffffff);
				            	fr.drawStringWithShadow(getPing() + " ", 24, 22, 0xffffff);
			            	}
			            	
			            	//fps
			            	if (Minecraft.getDebugFPS() < 20) {
			            		fr.drawStringWithShadow("fps", 2, 32, 0xffe60000);
			            		fr.drawStringWithShadow(Minecraft.getDebugFPS() + " ", 20, 32, 0xffe60000);
			            	}else {
			            		fr.drawStringWithShadow("fps", 2, 32, 0xffffffff);
			            		fr.drawStringWithShadow(Minecraft.getDebugFPS() + " ", 20, 32, 0xffffffff);
			            	}
			            	
			            	//htr
			            	//--use if (htr is true) -- be green , if not -- be red
			            	fr.drawStringWithShadow("htr", 2, 42, 0xffffffff);
			            	
			            	//plr
			            	fr.drawStringWithShadow("plr", 2, 52, 0xffffffff);
			            	
			            	//lby
			            	fr.drawStringWithShadow("lby", 2, 62, 0xffffffff);
			            	
			            	//caura
			            	//-- if on be green, otherwise red (same with surround)
			            	for (Module mod : Main.moduleManager.getModuleList()) {
			            		if(mod.getName().equals("autoCrystal") && mod.isToggled()) {
			            			fr.drawStringWithShadow("autoC:", 37, 14, 0xff00ff00);
			            			fr.drawStringWithShadow("on", 68, 14, 0xff00ff00);
			            	}else {
			            		if(mod.getName().equals("autoCrystal") && !mod.isToggled()) {
			            		fr.drawStringWithShadow("autoC:", 34, 14, 0xffe60000);
		            			fr.drawStringWithShadow("off", 65, 14, 0xffe60000);
			            		}
			            	}
			            		
			            	//surround
			            		if(mod.getName().equals("surround") && mod.isToggled()) {
			            			fr.drawStringWithShadow("srnd:", 41, 62, 0xff00ff00);
			            			fr.drawStringWithShadow("on", 68, 62, 0xff00ff00);
			            		}else {
			            			if(mod.getName().equals("surround") && !mod.isToggled()) {
			            			fr.drawStringWithShadow("srnd:", 38, 62, 0xffe60000);
			            			fr.drawStringWithShadow("off", 65, 62, 0xffe60000);
			            			}
			            		}
			            	}
			            	counter[0]++;
			            }
			            if(event.getType() == RenderGameOverlayEvent.ElementType.BOSSHEALTH) {
			            	Gui.drawRect(0, 0, 82, 72, 0x80000000);
			            	//left
			            	Gui.drawRect(0, 0, 1, 72, 0xffffffff);
			            	//top
			            	Gui.drawRect(0, 0, 82, 1, 0xffffffff);
			            	//right
			            	Gui.drawRect(83, 0, 82, 72, 0xffffffff);
			            	//bottom
			            	Gui.drawRect(0, 71, 82, 72, 0xffffffff);
			            }
			            
			            //arraylist
			            if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			    			int y = 74;
			    			final int[] counter = {1};
			    			for (Module mod : Main.moduleManager.getModuleList()) {
			    				if (!mod.getName().equalsIgnoreCase("tabGui") && !mod.getName().equalsIgnoreCase("lightHud") && !mod.getName().equalsIgnoreCase("darkHud") && mod.isToggled()) {
			    				fr.drawStringWithShadow(mod.getName(), 0, y, rainbow(counter[0] * 300));
			    				y += fr.FONT_HEIGHT;
			    				counter[0]++;
			    				}
			    				}
			    			}
			            }
			   }
			   public static int rainbow(int delay) {
				   double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
				   rainbowState %= -360;
			       return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
		}
			   
			   public void drawInventory ( int x, int y) {
				   ScaledResolution sr = new ScaledResolution(mc);
				   
						GlStateManager.enableAlpha();
						Gui.drawRect(sr.getScaledWidth() - 163 , 1, sr.getScaledWidth() - 1, 55, 0x80000000);
							GlStateManager.disableAlpha();

						GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
						NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
						for (int size = items.size(), item = 9; item < size; ++item) {
							final int slotX = sr.getScaledWidth() - 163 + 1 + item % 9 * 18;
							final int slotY = 1 + 1 + (item / 9 - 1) * 18;
							RenderHelper.enableGUIStandardItemLighting();
							mc.getRenderItem().renderItemAndEffectIntoGUI(items.get(item), slotX, slotY);
							mc.getRenderItem().renderItemOverlays(mc.fontRenderer, items.get(item), slotX, slotY);
							RenderHelper.disableStandardItemLighting();
						}
					}
			   
			   public int getPing () {
					int p = -1;
					if (mc.player == null || mc.getConnection() == null || mc.getConnection().getPlayerInfo(mc.player.getName()) == null) {
						p = -1;
					} else {
						p = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
					}
					return p;
				}
		
	}

		//darker blue - 0xff157DEC
		//gosha red - 0xffe60000
		//gosha blue - 0xff5cb3ff
		//brown - 0xffb5651d
		//peach - 0xffffc3b1
		//nice yellow - 0xfffffacd
		//grey - 0xff808080
		
		
