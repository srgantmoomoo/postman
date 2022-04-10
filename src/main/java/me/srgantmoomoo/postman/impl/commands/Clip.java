package me.srgantmoomoo.postman.impl.commands;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class Clip extends Command {
    public Clip() {
		super("clip", "clip horizontally or vertically through blocks.", "clip h <blocks> | clip v <blocks>", "c");
	}
    
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			String start = args[0];
			Entity entity = (Minecraft.getMinecraft().player.isRiding() ? Minecraft.getMinecraft().player.getRidingEntity() : Minecraft.getMinecraft().player);
			final Vec3d faceDirection = direction(Minecraft.getMinecraft().player.rotationYaw);

			assert entity != null;
			if(start.equalsIgnoreCase("v")) {
				entity.setPosition(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + Double.parseDouble(args[1]), Minecraft.getMinecraft().player.posZ);
				Main.INSTANCE.commandManager.sendClientChatMessage("vertically clipped " + args[1] + " blocks", true);
			}else if(start.equalsIgnoreCase("h")) {
				entity.setPosition(Minecraft.getMinecraft().player.posX + faceDirection.x * Double.parseDouble(args[1]), Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ + faceDirection.z * Double.valueOf(args[1]));
				Main.INSTANCE.commandManager.sendClientChatMessage("horizontally clipped " + args[1] + " blocks", true);
			}else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}
	
	public static Vec3d direction(float yaw) {
        return new Vec3d(Math.cos(degToRad(yaw + 90f)), 0, Math.sin(degToRad(yaw + 90f)));
    }
	
	public static double degToRad(double deg) {
        return deg * (float) (Math.PI / 180.0f);
    }

}
