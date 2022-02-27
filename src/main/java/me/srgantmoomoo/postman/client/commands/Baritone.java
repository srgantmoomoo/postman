package me.srgantmoomoo.postman.client.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalXZ;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class Baritone extends Command {
    public Baritone() {
        super("baritone", "use baritone api commands.", "baritone stop | baritone goto <x> <z> | baritone mine <block> | baritone farm", "b");
    }
    
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String starter = args[0];
		
			if(starter.equalsIgnoreCase("stop")) {
				if(args.length == 1) {
					BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().cancel();
					BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoal(null);
					Main.INSTANCE.moduleManager.addChatMessage("baritone has now " + ChatFormatting.GREEN + "stopped" + ChatFormatting.GRAY + ".");
				}else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else if(starter.equalsIgnoreCase("goto")) {
				if(args.length == 3) {
					String x = args[1];
					String z = args[2];
					BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalXZ(Integer.parseInt(x), Integer.parseInt(z)));
					Main.INSTANCE.moduleManager.addChatMessage("baritone is now pathing to " + ChatFormatting.GREEN + x + " " + z + ChatFormatting.GRAY + ".");
				}else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else if(starter.equalsIgnoreCase("mine")) {
				if(args.length == 2) {
					String block = args[1];
					try {
						BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mineByName(block);
						Main.INSTANCE.moduleManager.addChatMessage("baritone is now mining " + ChatFormatting.GREEN + block + ChatFormatting.GRAY + ".");
					}catch (Exception e) {
						Main.INSTANCE.moduleManager.addChatMessage("baritone could not find that block. :(");
					}
				}else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else if(starter.equalsIgnoreCase("farm")) {
				if(args.length == 1) {
					BaritoneAPI.getProvider().getPrimaryBaritone().getFarmProcess().farm();
					Main.INSTANCE.moduleManager.addChatMessage("baritone is now " + ChatFormatting.GREEN + "farming" + ChatFormatting.GRAY + ".");
				}else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
		}else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
	}
}