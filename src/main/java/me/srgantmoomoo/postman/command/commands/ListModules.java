package me.srgantmoomoo.postman.command.commands;

import com.sun.jna.platform.unix.X11;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
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

        for(int i = 0; i < 8; i++) {
            Category category = Category.values()[i];
            Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.BOLD + category.getName() + " modules", false);
            for(Module module : Main.INSTANCE.moduleManager.getModulesInCategory(category)) {
                Main.INSTANCE.commandManager.sendClientChatMessage((module.isModuleEnabled() ? Formatting.GREEN : Formatting.RED) + module.getName() + Formatting.GRAY + " - " + module.getDescription() + "\n", false);
            }
        }
    }
}
