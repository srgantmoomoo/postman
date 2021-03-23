package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class Clip extends Command {
	
    public Clip() {
		super("clip", "clip thru shit.", "clip h <blocks> | clip v <blocks>", "c");
	}
    
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String start = args[0];
			Entity entity = (Minecraft.getMinecraft().player.isRiding() ? Minecraft.getMinecraft().player.getRidingEntity() : Minecraft.getMinecraft().player);
			final Vec3d faceDirection = direction(Minecraft.getMinecraft().player.rotationYaw);
			
			if(start.equalsIgnoreCase("v")) {
				entity.setPosition(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + Double.parseDouble(args[1]), Minecraft.getMinecraft().player.posZ);
				ModuleManager.addChatMessage("vertically clipped " + args[1] + " blocks");
				
			}else if(start.equalsIgnoreCase("h")) {
				 entity.setPosition(Minecraft.getMinecraft().player.posX + faceDirection.x * Double.parseDouble(args[1]), Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ + faceDirection.z * Double.valueOf(args[1]));
				ModuleManager.addChatMessage("horizontally clipped " + args[1] + " blocks");
			}else
				CommandManager.correctUsageMsg("", getName(), getSyntax());
		}else CommandManager.correctUsageMsg("", getName(), getSyntax());
	}
	
	public static Vec3d direction(float yaw) {
        return new Vec3d(Math.cos(degToRad(yaw + 90f)), 0, Math.sin(degToRad(yaw + 90f)));
    }
	
	public static double degToRad(double deg) {
        return deg * (float) (Math.PI / 180.0f);
    }
}
