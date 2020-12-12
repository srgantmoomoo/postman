package me.srgantmoomoo.api.util.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.modules.pvp.Surround;
import me.srgantmoomoo.postman.module.modules.render.Esp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
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
	
	JColor ppColor;
	
	 public void onWorldRender(RenderEvent event) {
			Minecraft mc = Minecraft.getMinecraft();
	    	//add mobs and items to 2dEsp
		  if (ModuleManager.getModuleByName("esp's") != null && ModuleManager.getModuleByName("esp's").isToggled() && ((Esp) ModuleManager.getModuleByName("esp's")).entityMode.getMode().equals("2dEsp")) {
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
	  		        	    ppColor = new JColor((int) ((Esp) ModuleManager.getModuleByName("esp's")).pRed.getValue(), (int) ((Esp) ModuleManager.getModuleByName("esp's")).pGreen.getValue(), (int) ((Esp) ModuleManager.getModuleByName("esp's")).pBlue.getValue());
	  		        	    GlStateManager.glLineWidth((float) ((Esp) ModuleManager.getModuleByName("esp's")).lineWidth.getValue());
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
}	  	  