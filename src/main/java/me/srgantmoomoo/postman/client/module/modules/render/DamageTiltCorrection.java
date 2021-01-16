package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.math.MathHelper;

public class DamageTiltCorrection extends Module {
	
	public DamageTiltCorrection() {
		super ("damageTiltCorrection", "fixes camera tilt when damaged.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onUpdate(Entity entity, double x, double y, double z) {
			if(entity != null) {
				EntityPlayer player = Minecraft.getMinecraft().player;
				if(player != null && entity.equals(player)) {
					//Set the value
					float result = (float)(MathHelper.atan2(player.motionZ - z, player.motionX - x) * (180D / Math.PI) - (double)player.rotationYaw);
					
					if(Float.isFinite(result))
						player.attackedAtYaw = result;
				}
			}
		}
}