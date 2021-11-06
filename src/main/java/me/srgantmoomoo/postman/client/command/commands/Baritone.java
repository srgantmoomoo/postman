package me.srgantmoomoo.postman.client.command.commands;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalXZ;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

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
					ModuleManager.addChatMessage("baritone has now " + ChatFormatting.GREEN + "stopped" + ChatFormatting.GRAY + ".");
				}else CommandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else if(starter.equalsIgnoreCase("goto")) {
				if(args.length == 3) {
					String x = args[1];
					String z = args[2];
					BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalXZ(Integer.parseInt(x), Integer.parseInt(z)));
					ModuleManager.addChatMessage("baritone is now pathing to " + ChatFormatting.GREEN + x + " " + z + ChatFormatting.GRAY + ".");
				}else CommandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else if(starter.equalsIgnoreCase("mine")) {
				if(args.length == 2) {
					String block = args[1];
					try {
						BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mineByName(block);
						ModuleManager.addChatMessage("baritone is now mining " + ChatFormatting.GREEN + block + ChatFormatting.GRAY + ".");
					}catch (Exception e) {
						ModuleManager.addChatMessage("baritone could not find that block. :(");
					}
				}else CommandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else if(starter.equalsIgnoreCase("farm")) {
				if(args.length == 1) {
					BaritoneAPI.getProvider().getPrimaryBaritone().getFarmProcess().farm();
					ModuleManager.addChatMessage("baritone is now " + ChatFormatting.GREEN + "farming" + ChatFormatting.GRAY + ".");
				}else CommandManager.correctUsageMsg(getName(), getSyntax());
			}
			
			else CommandManager.correctUsageMsg(getName(), getSyntax());
		}else CommandManager.correctUsageMsg(getName(), getSyntax());
	}
}