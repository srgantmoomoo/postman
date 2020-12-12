package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.api.event.events.RenderEvent;
import me.srgantmoomoo.api.util.Wrapper;
import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.api.util.render.JTessellator;
import me.srgantmoomoo.api.util.world.GeometryMasks;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.modules.pvp.Surround;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.ModeSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 * Took a lot of Osiris by finz0 for the 2dEsp, probably gonna modify a few more things to make it look a little cooler :/
 */

public class Esp extends Module {
	public BooleanSetting chams = new BooleanSetting("chams", false);
	public ModeSetting entityMode = new ModeSetting("entity", "box", "box", "outline", "2dEsp", "off");
	public ModeSetting storage = new ModeSetting("storage", "fill", "fill", "outline", "off");
	public BooleanSetting mob = new BooleanSetting("mob", false);
	public BooleanSetting item = new BooleanSetting("item", true);
	public NumberSetting range = new NumberSetting("range", 100, 10, 260, 10);
	public NumberSetting lineWidth = new NumberSetting("lineWidth", 3, 0, 10, 1);
	public NumberSetting pRed = new NumberSetting("plyrRed", 0, 0, 250, 10);
	public NumberSetting pGreen = new NumberSetting("plyrGreen", 121, 0, 250, 10);
	public NumberSetting pBlue = new NumberSetting("plyrBlue", 194, 0, 250, 10);
	
	public Esp() {
		super ("esp's", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(entityMode, storage, chams, mob, item, range, lineWidth, pRed, pGreen, pBlue);
	}
	private static final Minecraft mc = Wrapper.getMinecraft();

    JColor playerColor;
    JColor mobColor;
    JColor mainIntColor;
    JColor containerColor;
    JColor containerBox;
    int opacityGradient;

    public void onWorldRender(RenderEvent event) {
    	
    	//add mobs and items too 2dEsp
  	 /* if(entityMode.getMode().equals("2dEsp")) {
       	 if ((mc.getRenderManager()).options == null)
  		      return; 
  		    float viewerYaw = (mc.getRenderManager()).playerViewY;
  		 mc.world.loadedEntityList.stream().filter(entity -> entity != mc.player).forEach(e -> {
  		          JTessellator.prepare();
  		          GlStateManager.pushMatrix();
  		          Vec3d pos = Surround.getInterpolatedPos(e, mc.getRenderPartialTicks());
  		          GlStateManager.translate(pos.x - (mc.getRenderManager()).renderPosX, pos.y - (mc.getRenderManager()).renderPosY, pos.z - (mc.getRenderManager()).renderPosZ);
  		          GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
  		          GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
  		          
  		          GL11.glEnable(2848);
  		          if (e instanceof net.minecraft.entity.player.EntityPlayer) {
  		        	    playerColor = new JColor((int) pRed.getValue(), (int) pGreen.getValue(), (int) pBlue.getValue(), opacityGradient);
  		        	    GlStateManager.glLineWidth((float) lineWidth.getValue());
  		        	 playerColor.glColor();
  		              GL11.glBegin(2);
  		              GL11.glVertex2d(-e.width, 0.0D);
  		              GL11.glVertex2d(-e.width, (e.height / 4.0F));
  		              GL11.glVertex2d(-e.width, 0.0D);
  		              GL11.glVertex2d((-e.width / 4.0F * 2.0F), 0.0D);
  		              GL11.glEnd();
  		              GL11.glBegin(2);
  		              GL11.glVertex2d(-e.width, e.height);
  		              GL11.glVertex2d((-e.width / 4.0F * 2.0F), e.height);
  		              GL11.glVertex2d(-e.width, e.height);
  		              GL11.glVertex2d(-e.width, (e.height / 2.5F * 2.0F));
  		              GL11.glEnd();
  		              GL11.glBegin(2);
  		              GL11.glVertex2d(e.width, e.height);
  		              GL11.glVertex2d((e.width / 4.0F * 2.0F), e.height);
  		              GL11.glVertex2d(e.width, e.height);
  		              GL11.glVertex2d(e.width, (e.height / 2.5F * 2.0F));
  		              GL11.glEnd();
  		              GL11.glBegin(2);
  		              GL11.glVertex2d(e.width, 0.0D);
  		              GL11.glVertex2d((e.width / 4.0F * 2.0F), 0.0D);
  		              GL11.glVertex2d(e.width, 0.0D);
  		              GL11.glVertex2d(e.width, (e.height / 4.0F));
  		              GL11.glEnd();

  		       } 
  		          JTessellator.release();
  		          GlStateManager.popMatrix();
  		        });
   } */
    	
        mc.world.loadedEntityList.stream().filter(entity -> entity != mc.player).filter(entity -> rangeEntityCheck(entity)).forEach(entity -> {
            defineEntityColors(entity);
            if (entityMode.getMode().equals("box") && entity instanceof EntityPlayer) {
            	JTessellator.playerEsp(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), playerColor);
            }
            if (mob.isEnabled() && entityMode.getMode().equals("box")){
                if (entity instanceof EntityCreature || entity instanceof EntitySlime) {
                    JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), 2, mobColor);
                }
            }
            if (item.isEnabled() && entity instanceof EntityItem){
            	JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), 2, mainIntColor);
            }
        });
        
        if (storage.getMode().equals("outline")) {
            mc.world.loadedTileEntityList.stream().filter(tileEntity -> rangeTileCheck(tileEntity)).forEach(tileEntity -> {
                if (tileEntity instanceof TileEntityChest){
                    containerColor = new JColor(255, 255, 0, opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
                if (tileEntity instanceof TileEntityEnderChest){
                    containerColor = new JColor(180, 70, 200, opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
                if (tileEntity instanceof TileEntityShulkerBox){
                    containerColor = new JColor(255, 182, 193, opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
                if(tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper){
                    containerColor = new JColor(150, 150, 150, opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
            });
        }
        
        if (storage.getMode().equals("fill")) {
            mc.world.loadedTileEntityList.stream().filter(tileEntity -> rangeTileCheck(tileEntity)).forEach(tileEntity -> {
                if (tileEntity instanceof TileEntityChest){
                    containerColor = new JColor(255, 255, 0, opacityGradient);
                    containerBox = new JColor(255, 255, 0, 50);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawStorageBox(tileEntity.getPos(),1, containerBox);
                }
                if (tileEntity instanceof TileEntityEnderChest){
                    containerColor = new JColor(180, 70, 200, opacityGradient);
                    containerBox = new JColor(255, 70, 200, 50);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawStorageBox(tileEntity.getPos(),1, containerBox);
                }
                if (tileEntity instanceof TileEntityShulkerBox){
                    containerColor = new JColor(255, 182, 193, opacityGradient);
                    containerBox = new JColor(255, 182, 193, 50);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawBox(tileEntity.getPos(),1, containerBox);
                }
                if(tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper){
                    containerColor = new JColor(150, 150, 150, opacityGradient);
                    containerBox = new JColor(150, 150, 150, 50);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawBox(tileEntity.getPos(),1, containerBox);
                }
            });
        }
    }
    
    private void drawStorageBox(BlockPos blockPos, int width, JColor color) {
		JTessellator.drawStorageBox(blockPos, 0.88, color, GeometryMasks.Quad.ALL);
    }
    
    private void drawBox(BlockPos blockPos, int width, JColor color) {
		JTessellator.drawBox(blockPos, 1, color, GeometryMasks.Quad.ALL);
   }

    public void onDisable(){
    }

    private void defineEntityColors(Entity entity) {
        //should have everything covered here, mob categorizing is weird
        if (entity instanceof EntityPlayer){
                playerColor = new JColor((int) pRed.getValue(), (int) pGreen.getValue(), (int) pBlue.getValue(), opacityGradient);
            }

        if (entity instanceof EntityMob){
            mobColor = new JColor(255, 0, 0, opacityGradient);
        }
        else if (entity instanceof EntityAnimal){
            mobColor = new JColor(0, 255, 0, opacityGradient);
        }
        else {
            mobColor = new JColor(255, 165, 0, opacityGradient);
        }

        if (entity instanceof EntitySlime){
            mobColor = new JColor(255, 0, 0, opacityGradient);
        }

        if (entity != null) {
            mainIntColor = new JColor(0, 121, 194, opacityGradient);
        }
        }
    //boolean range check and opacity gradient

    private boolean rangeEntityCheck(Entity entity) {
        if (entity.getDistance(mc.player) > range.getValue()){
            return false;
        }

        if (entity.getDistance(mc.player) >= 180){
            opacityGradient = 50;
        }
        else if (entity.getDistance(mc.player) >= 130 && entity.getDistance(mc.player) < 180){
            opacityGradient = 100;
        }
        else if (entity.getDistance(mc.player) >= 80 && entity.getDistance(mc.player) < 130){
            opacityGradient = 150;
        }
        else if (entity.getDistance(mc.player) >= 30 && entity.getDistance(mc.player) < 80){
            opacityGradient = 200;
        }
        else {
            opacityGradient = 255;
        }

        return true;
    }

    private boolean rangeTileCheck(TileEntity tileEntity) {
        //the range value has to be squared for this
        if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) > range.getValue() * range.getValue()){
            return false;
        }

        if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 32400){
            opacityGradient = 50;
        }
        else if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 16900 && tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) < 32400){
            opacityGradient = 100;
        }
        else if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 6400 && tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) < 16900){
            opacityGradient = 150;
        }
        else if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 900 && tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) < 6400){
            opacityGradient = 200;
        }
        else {
            opacityGradient = 255;
        }

        return true;
    }
}

