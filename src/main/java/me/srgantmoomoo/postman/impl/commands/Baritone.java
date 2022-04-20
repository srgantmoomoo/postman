package me.srgantmoomoo.postman.impl.commands;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalXZ;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;

public class Baritone extends Command {
    public Baritone() {
        super("baritone", "use baritone api commands.", "baritone stop | baritone goto <x> <z> | baritone mine <block> | baritone farm", "b");
    }
    
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("stop")) {
				BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().cancel();
				BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoal(null);
				Main.INSTANCE.commandManager.sendClientChatMessage("baritone has now " + RED + "stopped" + GRAY + ".", true);
			}else if(args[0].equalsIgnoreCase("farm")) {
				BaritoneAPI.getProvider().getPrimaryBaritone().getFarmProcess().farm();
				Main.INSTANCE.commandManager.sendClientChatMessage("baritone is now " + GREEN + "farming" + GRAY + ".", true);
			}else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());

		}else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("mine")) {
				String block = args[1];
				try {
					BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mineByName(block);
					Main.INSTANCE.commandManager.sendClientChatMessage("baritone is now mining " + GREEN + block + GRAY + ".", true);
				}catch (Exception e) {
					Main.INSTANCE.commandManager.sendClientChatMessage("baritone could not find that block. :(", true);
				}
			}else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());

		}else if(args.length == 3) {
			if(args[0].equalsIgnoreCase("goto")) {
				String x = args[1];
				String z = args[2];
				BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalXZ(Integer.parseInt(x), Integer.parseInt(z)));
				Main.INSTANCE.commandManager.sendClientChatMessage("baritone is now pathing to " + GREEN + x + " " + z + GRAY + ".", true);
			}else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());

	}

}