package me.srgantmoomoo.postman.module.modules.client;
	
import java.util.Comparator;
import java.util.Objects;
	
import org.lwjgl.input.Keyboard;
	
import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.api.util.world.EntityUtil;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
	
public class TargetHud extends Module {
	public NumberSetting posX = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting posY = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	public NumberSetting range = new NumberSetting("range", this, 100, 0, 260, 10);
	boolean on;
		
	public TargetHud() {
		super("targetHud", "classic hud", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(posX, posY, range);
	}
		FontRenderer fr = mc.fontRenderer;
	
	    JColor outlineColor;
	    JColor backgroundColor;
	    JColor nameColor;
	    JColor healthColor;
	    TextFormatting playercolor;
	    String playerinfo;
	    float ping;
	    public static EntityPlayer targetPlayer;
	    
	    public void onEnable() {
	    	super.onEnable();
	    	on = true;
	    }
	    
	    public void onDisable() {
	    	super.onDisable();
	    	on = false;
	    }
	
	    @SubscribeEvent
		public void renderOverlay(RenderGameOverlayEvent event) {
			if(on) {
			if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
				
	        if (mc.world != null && mc.player.ticksExisted >= 10) {
	            backgroundColor = new JColor(255,255,255);
	            outlineColor = new JColor(255,255,255);
	
	            EntityPlayer entityPlayer = (EntityPlayer) mc.world.loadedEntityList.stream()
	                    .filter(entity -> IsValidEntity(entity))
	                    .map(entity -> (EntityLivingBase) entity)
	                    .min(Comparator.comparing(c -> mc.player.getDistance(c)))
	                    .orElse(null);
	
	            if (entityPlayer == null)
	                return;
	
	            if (entityPlayer != null) {
	                String playerName = entityPlayer.getName();
	                int playerHealth = (int) (entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount());
	                findNameColor(playerName);
	                findHealthColor(playerHealth);
	
	                //player model
	                drawEntityPlayer(entityPlayer, (int) posX.getValue() + 35, (int) posY.getValue() + 87 - (entityPlayer.isSneaking()?10:0));
	
	                //box
	                drawTargetBox();
	
	                //player name
	                fr.drawStringWithShadow(TextFormatting.ITALIC + playerName, (float) posX.getValue() + 61, (float) posY.getValue() + 33, 0xff79c2ec);
	
	                //health + absorption
	                fr.drawStringWithShadow("health:", (int) posX.getValue() + 61, (int) posY.getValue() + 43, 0xff79c2ec);
	                fr.drawStringWithShadow(playerHealth + "", (int) posX.getValue() + 96,  (int) posY.getValue() + 43, playerHealth >= 15 ? 0xff00ff00 : 0xffe6000);
	                
	                //distance
	                fr.drawStringWithShadow("Distance:", (int) posX.getValue() + 61, (int) posY.getValue() + 53, 0xff79c2ec);
	                fr.drawStringWithShadow((int) entityPlayer.getDistance(mc.player) + "", (int) posX.getValue() + 108, (int) posY.getValue() + 53, entityPlayer.getDistance(mc.player) < 6 ? 0xffe60000 : 0xff00ff00);
	                
	                //ping
	                ping = getPing(entityPlayer);
	                fr.drawStringWithShadow("ping:", (int) posX.getValue() + 61, (int) posY.getValue() + 63, 0xff79c2ec);
	                fr.drawStringWithShadow(ping + "", (int) posX.getValue() + 85, (int) posY.getValue() + 63, ping > 100 ? 0xffe60000 : 0xff00ff00);
	
	                //status effects
	                drawStatusEffects(entityPlayer, (int) posX.getValue(), (int) posY.getValue());
	
	                //armor + items
	                drawItemTextures(entityPlayer, (int) posX.getValue() + 51, (int) posY.getValue() + 83);
	
	                //player info
	                drawPlayerInfo(entityPlayer, (int) posX.getValue() + 61, (int) posY.getValue() + 73);
	            		}
	        		}
				}
			}
	    }
	
	    public void drawTargetBox(){
	        Gui.drawRect((int) posX.getValue() + 10, (int) posY.getValue() + 23, (int) posX.getValue() + 155, (int) posY.getValue() + 93, backgroundColor.getRGB());
	    }
	
	    public void drawEntityPlayer(EntityPlayer entityPlayer, int x, int y){
	        targetPlayer = entityPlayer;
	        GlStateManager.pushMatrix();
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        GuiInventory.drawEntityOnScreen(x, y, 30, 28, 60, entityPlayer);
	        GlStateManager.popMatrix();
	    }
	
	    public void drawPlayerInfo(EntityPlayer entityPlayer, int x, int y) {
	
	        if (entityPlayer.inventory.armorItemInSlot(2).getItem().equals(Items.ELYTRA)) {
	            playerinfo = "ayo watch yo jet";
	            playercolor = TextFormatting.LIGHT_PURPLE;
	        }
	        else if (entityPlayer.inventory.armorItemInSlot(2).getItem().equals(Items.DIAMOND_CHESTPLATE)) {
	            playerinfo = "threat";
	            playercolor = TextFormatting.RED;
	        }
	        else if (entityPlayer.inventory.armorItemInSlot(3).getItem().equals(Items.AIR)) {
	            playerinfo = "i suck ass";
	            playercolor = TextFormatting.GREEN;
	        }
	        else {
	            playerinfo = "None";
	            playercolor = TextFormatting.WHITE;
	        }
	
	        ping = getPing(entityPlayer);
	        fr.drawStringWithShadow(playercolor + playerinfo, x, y, 0xffffffff);
	    }
	
	    //having more than one of these displayed at once makes things too crowded
	    JColor statusColor = new JColor(255, 255, 255, 255);
	    public void drawStatusEffects(EntityPlayer entityPlayer, int x, int y){
	        int inX = x + 71;
	        int inY = y + 55;
	
	        entityPlayer.getActivePotionEffects().forEach(potionEffect -> {
	            findPotionColor(potionEffect);
	
	            if (potionEffect.getPotion() == MobEffects.WEAKNESS) {
	                fr.drawStringWithShadow(TextFormatting.RESET + "i have weakness!", inX, inY, 0xffffffff);
	            }
	            else if (potionEffect.getPotion() == MobEffects.INVISIBILITY){
	                fr.drawStringWithShadow(TextFormatting.RESET + "im invisible!", inX, inY, 0xffffffff);
	            }
	            else if (potionEffect.getPotion() == MobEffects.STRENGTH){
	                fr.drawStringWithShadow(TextFormatting.RESET + "i have strength!", inX, inY, 0xffffffff);
	            }
	        });
	    }
	
	    private static final RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
	    public void drawItemTextures(EntityPlayer entityPlayer, int x, int y){
	        GlStateManager.pushMatrix();
	        RenderHelper.enableGUIStandardItemLighting();
	
	        int iteration = 0;
	        for (ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
	            iteration++;
	            if (itemStack.isEmpty()) continue;
	            int inX = x - 90 + (9 - iteration) * 20 + 2;
	
	            itemRender.zLevel = 200F;
	            itemRender.renderItemAndEffectIntoGUI(itemStack, inX, y);
	            itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, itemStack, inX, y, "");
	            itemRender.zLevel = 0F;
	        }
	
	        RenderHelper.disableStandardItemLighting();
	        mc.getRenderItem().zLevel = 0.0F;
	        GlStateManager.popMatrix();
	    }
	
	    public void findPotionColor(PotionEffect potionEffect){
	        if (potionEffect.getPotion() == MobEffects.STRENGTH){
	            statusColor = new JColor(135, 0, 25, 255);
	        }
	        else if (potionEffect.getPotion() == MobEffects.WEAKNESS){
	            statusColor = new JColor(185, 65, 185, 255);
	        }
	        else if (potionEffect.getPotion() == MobEffects.INVISIBILITY){
	            statusColor = new JColor(90, 90, 90, 255);
	        }
	    }
	
	    public void findNameColor(String playerName){
	            nameColor = new JColor(255, 255, 255, 255);
	        }
	
	    public void findHealthColor(int health){
	        if (health >= 15){
	            healthColor = new JColor(0, 255, 0, 255);
	        }
	        else if (health >= 5 && health < 15){
	            healthColor = new JColor(255, 255, 0, 255);
	        }
	        else {
	            healthColor = new JColor(255, 0, 0, 255);
	        }
	    }
	
	    private boolean IsValidEntity (Entity e){
	        if (!(e instanceof EntityPlayer)) {
	            return false;
	        }
	
	        if (e instanceof EntityPlayer) {
	            return e != mc.player;
	        }
	
	        return true;
	    }
	
	    public float getPing (EntityPlayer player){
	        float ping = 0;
	        try { ping = EntityUtil.clamp(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1, 300.0f); }
	        catch (NullPointerException ignored) {}
	        return ping;
	    }
	
	    public static boolean isRenderingEntity(EntityPlayer entityPlayer){
	        if (targetPlayer == entityPlayer){
	            return true;
	        }
	
	        return false;
	    }
	}
