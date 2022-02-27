package me.srgantmoomoo.postman.client.commands;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Vanish extends Command {
	private static Entity ridden;
	
    public Vanish() {
		super("vanish", "vanish ridden entities.", "vanish", "v");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 0) {
			if (Minecraft.getMinecraft().player.getRidingEntity() != null && ridden == null) {
				ridden = Minecraft.getMinecraft().player.getRidingEntity();
				
			    Minecraft.getMinecraft().player.dismountRidingEntity();
			    Minecraft.getMinecraft().world.removeEntityFromWorld(ridden.getEntityId());
				Main.INSTANCE.moduleManager.addChatMessage("entity " + ridden.getName() + " removed.");
			}else {
			    if (ridden != null) {
			    	ridden.isDead = false;
			    	
			        Minecraft.getMinecraft().world.addEntityToWorld(ridden.getEntityId(), ridden);
			        Minecraft.getMinecraft().player.startRiding(ridden, true);
					Main.INSTANCE.moduleManager.addChatMessage("entity " + ridden.getName() + " created.");
			        ridden = null;
			    }else {
					Main.INSTANCE.moduleManager.addChatMessage("no entity is being ridden");
			    }
			}
		}else CommandManager.correctUsageMsg(getName(), getSyntax());
	}
}