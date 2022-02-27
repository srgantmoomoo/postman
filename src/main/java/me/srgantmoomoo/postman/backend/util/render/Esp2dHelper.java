package me.srgantmoomoo.postman.backend.util.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import me.srgantmoomoo.postman.client.modules.pvp.Surround;
import me.srgantmoomoo.postman.client.modules.render.Esp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;

/*
 * original code in this is from finz0's Osiris.
 * SrgantMooMoo 12/11/20.
 * ...put this here to help fix my shitty code, in no way is this the best way to do it xD
 */
public class Esp2dHelper extends Module {
	
	public Esp2dHelper() {
		super("Esp2dHelper", "eeeeeeeEsp2dHelper", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = true;
	}
	public Esp esp = (Esp)ModuleManager.getModuleByName("esp's");
	
	JColor ppColor;
	int opacityGradient;
	
	 public void onWorldRender(RenderEvent event) {
			Minecraft mc = Minecraft.getMinecraft();
	    	//add mobs and items to 2dEsp
		  if (ModuleManager.getModuleByName("esp's") != null && esp.isToggled() && esp.entityMode.is("2dEsp")) {
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
	  		        	  ppColor = new JColor(((Esp) ModuleManager.getModuleByName("esp's")).playerColor.getValue());
	  		        	  GlStateManager.glLineWidth((float) esp.lineWidth.getValue());
	  		        	  ppColor.glColor();
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
		  }
	 }
	 
	   public boolean rangeEntityCheck(Entity entity) {
	        if (entity.getDistance(mc.player) > esp.range.getValue()){
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

	    public boolean rangeTileCheck(TileEntity tileEntity) {
	        //the range value has to be squared for this
	        if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) > esp.range.getValue() * esp.range.getValue()){
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