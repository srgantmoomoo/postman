package me.srgantmoomoo.postman.ui.clickgui;

import java.io.IOException;
import java.util.List;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

public class ClickGui extends GuiScreen {
	private Minecraft mc = Minecraft.getMinecraft();
	ScaledResolution sr = new ScaledResolution(mc);
	ClickGuiButton button;
	int player;
	
	public boolean playerOn;
	public boolean renderOn;
	public boolean pvpOn;
	public boolean exploitsOn;
	public boolean clientOn;
	
	final int BUTTON = 0;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		// PLAYER ------
		if(playerOn) {
			Gui.drawRect(80, 1, 160, 194, 0xe0ffffff);
		}else {
		Gui.drawRect(80, 1, 160, 14, 0xe0ffffff);
		}
		
		// RENDER ------
		if(renderOn) {
		Gui.drawRect(180, 1, 260, 248, 0xe0ffffff);
		}else {
			Gui.drawRect(180, 1, 260, 14, 0xe0ffffff);
		}
		
		// PVP ------
		if(pvpOn) {
			Gui.drawRect(280, 1, 360, 176, 0xe0ffffff);
		}else {
			Gui.drawRect(280, 1, 360, 14, 0xe0ffffff);
		}
		
		// EXPLOITS ------
		if(exploitsOn) {
		Gui.drawRect(380, 1, 460, 140, 0xe0ffffff);
		}else {
			Gui.drawRect(380, 1, 460, 14, 0xe0ffffff);
		}
		
		// CLIENT ------
		if(clientOn) {
		Gui.drawRect(480, 1, 560, 194, 0xe0ffffff);
		}else {
			Gui.drawRect(480, 1, 560, 14, 0xe0ffffff);
		}
		
		//button.drawButton(mc, mouseX, mouseY, partialTicks);
		
		/*
		 * PLAYER MODULES DRAWN ------
		 */
		if(playerOn) {
		String[] buttons = { "autoArmor", "autoTotem", "chatBot", "chatWatermark", "inventoryMove", "jesus", "noPush", "scaffold", "sprint", "velocity" };		
		
		int count = 0;
		for(String name : buttons) {
			float x = 120 - mc.fontRenderer.getStringWidth(name)/2f;
			float y = 18 + count * 18;
						
			boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT; 
			boolean enabled = ModuleManager.getModuleByName(name).isToggled();
						
			this.drawCenteredString(mc.fontRenderer, name, 120, 18 + count * 18, enabled ? 0xff79c2ec : hovered ? 0xfff9c2ec : -1); //0xff2090ec
			count++;
		}
		}
		
		/*
		 * RENDER MODULES DRAWN ------
		 */
		if(renderOn) {
			String[] buttons = { "esp's", "freecam", "fullBright", "holeEsp", "lowOffHand", "nametags", "newChunks", "noHurtCam", "peek", "tracers", "viewModel", "weather", "xray" };		
			
			int count = 0;
			for(String name : buttons) {
				float x = 220 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
							
				boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT; 
				boolean enabled = ModuleManager.getModuleByName(name).isToggled();
							
				this.drawCenteredString(mc.fontRenderer, name, 220, 18 + count * 18, enabled ? 0xff79c2ec : hovered ? 0xfff9c2ec : -1);
				count++;
			}
		}
		
		/*
		 * PVP MODULES DRAWN ------
		 */
		if(pvpOn) {
			String[] buttons = { "aimBot", "aura", "autoClicker", "autoCrystal", "autoLog", "bowSpam", "holeTp", "logOutSpot", "surround" };		
			
			int count = 0;
			for(String name : buttons) {
				float x = 320 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
							
				boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT; 
				boolean enabled = ModuleManager.getModuleByName(name).isToggled();
							
				this.drawCenteredString(mc.fontRenderer, name, 320, 18 + count * 18, enabled ? 0xff79c2ec : hovered ? 0xfff9c2ec : -1);
				count++;
			}
		}
		
		/*
		 * EXPLOITS MODULES DRAWN ------
		 */
		if(exploitsOn) {
			String[] buttons = { "antiHunger", "ezBackdoor", "chestStealer", "coordExploit", "dupe", "elytraFly", "playerClone" };		
			
			int count = 0;
			for(String name : buttons) {
				float x = 420 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
							
				boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT; 
				boolean enabled = ModuleManager.getModuleByName(name).isToggled();
							
				this.drawCenteredString(mc.fontRenderer, name, 420, 18 + count * 18, enabled ? 0xff79c2ec : hovered ? 0xfff9c2ec : -1);
				count++;
			}
		}
		
		/*
		 * CLIENT MODULES DRAWN ------
		 */
		if(clientOn) {
			String[] buttons = { "watermark", "arrayList", "info", "inventory", "hey!", "armorHud", "keyStrokes", "discordRp", "clickGui", "tabGui" };		
			
			int count = 0;
			for(String name : buttons) {
				float x = 520 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
							
				boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT; 
				boolean enabled = ModuleManager.getModuleByName(name).isToggled();
							
				this.drawCenteredString(mc.fontRenderer, name, 520, 18 + count * 18, enabled ? 0xff79c2ec : hovered ? 0xfff9c2ec : -1);
				count++;
			}
		}
		
		/*
		 * PLAYER CATEGORY DRAWN ------
		 */
		String[] playerCatButtons = { "player" };	
		
		for(String playerCatName : playerCatButtons) {
						
			this.drawCenteredString(mc.fontRenderer, TextFormatting.ITALIC + playerCatName, 120, 3, 0xff79c2ec);
		}
		
		/*
		 * RENDER CATEGORY DRAWN ------
		 */
		String[] renderCatButtons = { "render" };	
		
		for(String renderCatName : renderCatButtons) {
						
			this.drawCenteredString(mc.fontRenderer, TextFormatting.ITALIC + renderCatName, 220, 3, 0xff79c2ec);
		}
		
		/*
		 * PVP CATEGORY DRAWN ------
		 */
		String[] pvpCatButtons = { "pvp" };	
		
		for(String pvpCatName : pvpCatButtons) {
						
			this.drawCenteredString(mc.fontRenderer, TextFormatting.ITALIC + pvpCatName, 320, 3, 0xff79c2ec);
		}
		
		/*
		 * EXPLOITS CATEGORY DRAWN ------
		 */
		String[] exploitsCatButtons = { "exploits" };	
		
		for(String exploitsCatName : exploitsCatButtons) {
						
			this.drawCenteredString(mc.fontRenderer, TextFormatting.ITALIC + exploitsCatName, 420, 3, 0xff79c2ec);
		}
		
		/*
		 * CLIENT CATEGORY DRAWN ------
		 */
		String[] clientCatButtons = { "client" };	
		
		for(String clientCatName : clientCatButtons) {
						
			this.drawCenteredString(mc.fontRenderer, TextFormatting.ITALIC + clientCatName, 520, 3, 0xff79c2ec);
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void mouseClicked(int mouseX, int mouseY, int button) {
		
		/*
		 * PLAYER CATEGORY ------
		 */
		String[] playerCatButtons = { "player" };		
		
		for(String playerCatName : playerCatButtons) {
		float x = 120 - mc.fontRenderer.getStringWidth(playerCatName)/2f;
		float y = 3;
			
			if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(playerCatName) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
				switch(playerCatName) {
				case "player":
					if(playerOn) {
						playerOn = false;
					}else 
						playerOn = true;
					break;
				}
			}
		}
			
		/*
		 * RENDER CATEGORY ------
		 */
			String[] renderCatButtons = { "render" };		
			
			for(String renderCatName : renderCatButtons) {
			float x = 220 - mc.fontRenderer.getStringWidth(renderCatName)/2f;
			float y = 3;
				
				if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(renderCatName) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
					switch(renderCatName) {
					case "render":
						if(renderOn) {
							renderOn = false;
						}else 
							renderOn = true;
						break;
					}
				}
			}
			
			/*
			 * PVP CATEGORY ------
			 */
				String[] pvpCatButtons = { "pvp" };		
				
				for(String pvpCatName : pvpCatButtons) {
				float x = 320 - mc.fontRenderer.getStringWidth(pvpCatName)/2f;
				float y = 3;
					
					if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(pvpCatName) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
						switch(pvpCatName) {
						case "pvp":
							if(pvpOn) {
								pvpOn = false;
							}else 
								pvpOn = true;
							break;
						}
					}
				}
				
				/*
				 * EXPLOITS CATEGORY ------
				 */
					String[] exploitsCatButtons = { "exploits" };		
					
					for(String exploitsCatName : exploitsCatButtons) {
					float x = 420 - mc.fontRenderer.getStringWidth(exploitsCatName)/2f;
					float y = 3;
						
						if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(exploitsCatName) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
							switch(exploitsCatName) {
							case "exploits":
								if(exploitsOn) {
									exploitsOn = false;
								}else 
									exploitsOn = true;
								break;
							}
						}
					}
					
					/*
					 * CLIENT CATEGORY ------
					 */
						String[] clientCatButtons = { "client" };		
						
						for(String clientCatName : clientCatButtons) {
						float x = 520 - mc.fontRenderer.getStringWidth(clientCatName)/2f;
						float y = 3;
							
							if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(clientCatName) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
								switch(clientCatName) {
								case "client":
									if(clientOn) {
										clientOn = false;
									}else 
										clientOn = true;
									break;
								}
							}
						}
				
		/*
		 * PLAYER MODULES ------
		 */
		if(playerOn) {
		
		String[] buttons = { "autoArmor", "autoTotem", "chatBot", "chatWatermark", "inventoryMove", "jesus", "noPush", "scaffold", "sprint", "velocity" };		
		
		int count = 0;
		for(String name : buttons) {
			float x = 120 - mc.fontRenderer.getStringWidth(name)/2f;
			float y = 18 + count * 18;
			
			if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
				switch(name) {
				case "autoArmor":
					ModuleManager.getModuleByName("autoArmor").toggle();
					break;
				
				case "autoTotem":
					ModuleManager.getModuleByName("autoTotem").toggle();
					break;
					
				case "chatBot":
					ModuleManager.getModuleByName("chatBot").toggle();
					break;
					
				case "chatWatermark":
					ModuleManager.getModuleByName("chatWatermark").toggle();
					break;
					
				case "inventoryMove":
					ModuleManager.getModuleByName("inventoryMove").toggle();
					break;
					
				case "jesus":
					ModuleManager.getModuleByName("jesus").toggle();
					break;
					
				case "noPush":
					ModuleManager.getModuleByName("noPush").toggle();
					break;
					
				case "scaffold":
					ModuleManager.getModuleByName("scaffold").toggle();
					break;
					
				case "sprint":
					ModuleManager.getModuleByName("sprint").toggle();
					break;
					
				case "velocity":
					ModuleManager.getModuleByName("velocity").toggle();
					break;
				}
			}
			count++;
		}
	}
		
		/*
		 * RENDER MODULES ------
		 */
		if(renderOn) {
			
			String[] buttons = { "esp's", "freecam", "fullBright", "holeEsp", "lowOffHand", "nametags", "newChunks", "noHurtCam", "peek", "tracers", "viewModel", "weather", "xray" };
			
			int count = 0;
			for(String name : buttons) {
				float x = 220 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
				
				if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
					switch(name) {
					case "esp's":
						ModuleManager.getModuleByName("esp's").toggle();
						break;
					
					case "freecam":
						ModuleManager.getModuleByName("freecam").toggle();
						break;
						
					case "fullBright":
						ModuleManager.getModuleByName("fullBright").toggle();
						break;
						
					case "holeEsp":
						ModuleManager.getModuleByName("holeEsp").toggle();
						break;
						
					case "lowOffHand":
						ModuleManager.getModuleByName("lowOffHand").toggle();
						break;
						
					case "nametags":
						ModuleManager.getModuleByName("nametags").toggle();
						break;
						
					case "newChunks":
						ModuleManager.getModuleByName("newChunks").toggle();
						break;
						
					case "noHurtCam":
						ModuleManager.getModuleByName("noHurtCam").toggle();
						break;
						
					case "peek":
						ModuleManager.getModuleByName("peek").toggle();
						break;
						
					case "tracers":
						ModuleManager.getModuleByName("tracers").toggle();
						break;
						
					case "viewModel":
						ModuleManager.getModuleByName("viewModel").toggle();
						break;
						
					case "weather":
						ModuleManager.getModuleByName("weather").toggle();
						break;
						
					case "xray":
						ModuleManager.getModuleByName("xray").toggle();
						break;
					}
				}
				count++;
			}
	}
		
		/*
		 * PVP MODULES ------
		 */
		if(pvpOn) {
			
			String[] buttons = { "aimBot", "aura", "autoClicker", "autoCrystal", "autoLog", "bowSpam", "holeTp", "logOutSpot", "surround" };		
			
			int count = 0;
			for(String name : buttons) {
				float x = 320 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
				
				if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
					switch(name) {
					case "aimBot":
						ModuleManager.getModuleByName("aimBot").toggle();
						break;
					
					case "aura":
						ModuleManager.getModuleByName("aura").toggle();
						break;
						
					case "autoClicker":
						ModuleManager.getModuleByName("autoClicker").toggle();
						break;
						
					case "autoCrystal":
						ModuleManager.getModuleByName("autoCrystal").toggle();
						break;
						
					case "autoLog":
						ModuleManager.getModuleByName("autoLog").toggle();
						break;
						
					case "bowSpam":
						ModuleManager.getModuleByName("bowSpam").toggle();
						break;
						
					case "holeTp":
						ModuleManager.getModuleByName("holeTp").toggle();
						break;
						
					case "logOutSpot":
						ModuleManager.getModuleByName("logOutSpot").toggle();
						break;
						
					case "surround":
						ModuleManager.getModuleByName("surround").toggle();
						break;
					}
				}
				count++;
			}
	}
		
		/*
		 * EXPLOITS MODULES ------
		 */
		if(exploitsOn) {
			
			String[] buttons = { "antiHunger", "ezBackdoor", "chestStealer", "coordExploit", "dupe", "elytraFly", "playerClone" };		
			
			int count = 0;
			for(String name : buttons) {
				float x = 420 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
				
				if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
					switch(name) {
					case "antiHunger":
						ModuleManager.getModuleByName("antiHunger").toggle();
						break;
					
					case "ezBackdoor":
						ModuleManager.getModuleByName("ezBackdoor").toggle();
						break;
						
					case "chestStealer":
						ModuleManager.getModuleByName("chestStealer").toggle();
						break;
						
					case "coordExploit":
						ModuleManager.getModuleByName("coordExploit").toggle();
						break;
						
					case "dupe":
						ModuleManager.getModuleByName("dupe").toggle();
						break;
						
					case "elytraFly":
						ModuleManager.getModuleByName("elytraFly").toggle();
						break;
						
					case "playerClone":
						ModuleManager.getModuleByName("playerClone").toggle();
						break;
					}
				}
				count++;
			}
	}
		
		/*
		 * CLIENT MODULES ------
		 */
		if(clientOn) {
			
			String[] buttons = { "watermark", "arrayList", "info", "inventory", "hey!", "armorHud", "keyStrokes", "discordRp", "clickGui", "tabGui" };	
			
			int count = 0;
			for(String name : buttons) {
				float x = 520 - mc.fontRenderer.getStringWidth(name)/2f;
				float y = 18 + count * 18;
				
				if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRenderer.getStringWidth(name) && mouseY < y + mc.fontRenderer.FONT_HEIGHT) {
					switch(name) {
					case "watermark":
						ModuleManager.getModuleByName("watermark").toggle();
						break;
					
					case "arrayList":
						ModuleManager.getModuleByName("arrayList").toggle();
						break;
						
					case "info":
						ModuleManager.getModuleByName("info").toggle();
						break;
						
					case "inventory":
						ModuleManager.getModuleByName("inventory").toggle();
						break;
						
					case "hey!":
						ModuleManager.getModuleByName("hey!").toggle();
						break;
						
					case "armorHud":
						ModuleManager.getModuleByName("armorHud").toggle();
						break;
						
					case "keyStrokes":
						ModuleManager.getModuleByName("keyStrokes").toggle();
						break;
						
					case "discordRp":
						ModuleManager.getModuleByName("discordRp").toggle();
						break;
						
					case "clickGui":
						break;
						
					case "tabGui":
						break;
					}
				}
				count++;
			}
	}
	}
	
	@Override
	public void initGui() {
		buttonList.clear();
		playerOn = true;
		renderOn = true;
		pvpOn = true;
		exploitsOn = true;
		clientOn = true;
		//buttonList.add(button = new ClickGuiButton(BUTTON, 100, 100, 10, 10, m.getName()));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
