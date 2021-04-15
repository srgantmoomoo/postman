package me.srgantmoomoo.postman.client.command.commands;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalXZ;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class GoTo extends Command {
    public GoTo() {
        super("goto", "uses baritone to pathfind.", "goto <x> <z>", "go");
    }
    
	@Override
	public void onCommand(String[] args, String command) {
		String x = args[0];
		String y= args[1];
		if(args.length > 0) {
			BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalXZ(Integer.parseInt(x), Integer.parseInt(y)));
			ModuleManager.addChatMessage("baritone is now pathing to... x y z");
		}else CommandManager.correctUsageMsg("", getName(), getSyntax());
	}
}