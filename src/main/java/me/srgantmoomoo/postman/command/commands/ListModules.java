package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

public class ListModules extends Command {

    public ListModules() {
        super("listModules", "list the modules in postman.", "listmodules", "list");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        for(Category category : Category.values()) {
            Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.BOLD + category.getName() + " modules", false);
            for(Module module : Main.INSTANCE.moduleManager.getModulesInCategory(category)) {
                Main.INSTANCE.commandManager.sendClientChatMessage((module.isModuleEnabled() ? Formatting.GREEN : Formatting.RED) + module.getName() + Formatting.WHITE + " - " + module.getDescription().substring(0, module.getDescription().length() - 1) + Formatting.GRAY + ".", false);
            }
        }
    }
}
