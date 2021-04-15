package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalXZ;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Baritone extends Command {
    public Baritone() {
        super("baritone", "use baritone api commands.", "baritone goto <x> <z> | baritone mine <block>", "b");
    }
    
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length >= 0) {
			String starter = args[0];
		
			if(starter.equalsIgnoreCase("goto")) {
				String x = args[1];
				String z = args[2];
				BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalXZ(Integer.parseInt(x), Integer.parseInt(z)));
				ModuleManager.addChatMessage("baritone is now pathing to " + ChatFormatting.GREEN + x + " " + z + ".");
			}
			
			if(starter.equalsIgnoreCase("mine")) {
				String block = args[1];
			}
			
		}else CommandManager.correctUsageMsg("", getName(), getSyntax());
	}
}