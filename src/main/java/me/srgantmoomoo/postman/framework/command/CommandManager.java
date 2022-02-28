package me.srgantmoomoo.postman.framework.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.commands.*;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class CommandManager {
	
	public List<Command> commands = new ArrayList<Command>();
	public String prefix = ",";
	
	public CommandManager() {
		MinecraftForge.EVENT_BUS.register(this);
		Main.EVENT_BUS.subscribe(this);

		commands.add(new Prefix());
		commands.add(new Toggle());
		commands.add(new Bind());
		commands.add(new Baritone());
		commands.add(new Friend());
		commands.add(new AutoCope());
		commands.add(new Protester());
		commands.add(new MobOwner());
		commands.add(new Clip());
		commands.add(new Vanish());
	}
	
	@EventHandler
    public Listener<ClientChatEvent> listener = new Listener<>(event -> {
        String message = event.getMessage();
        
        if(!message.startsWith(prefix))
        	return;
        
        event.setCanceled(true);
        message = message.substring(prefix.length());

        if(message.split(" ").length > 0) {
        	boolean commandFound = false;
        	String commandName = message.split(" ")[0];
			if(commandName.equals("") || commandName.equals("help")) {
				ChatFormatting GRAY = ChatFormatting.GRAY;
				ChatFormatting BOLD = ChatFormatting.BOLD;
				ChatFormatting RESET = ChatFormatting.RESET;
				sendClientChatMessage("\n" + GRAY + "" + BOLD + "i love postman <3" + "\n" + RESET, false);
				sendCommandDescriptions();
				sendClientChatMessage("\n" + RESET + GRAY + BOLD + "i hate postman." + "\n", false);
			}else {
				for (Command c : commands) {
					if (c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
						c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
						commandFound = true;
						break;
					}
				}
				if (!commandFound) {
					sendClientChatMessage(ChatFormatting.DARK_RED + "command does not exist, use " + ChatFormatting.ITALIC + prefix + "help " + ChatFormatting.RESET + "" + ChatFormatting.DARK_RED + "for help.", true);
				}
			}
        }
    });

	//TODO find a better color for syntax or something lol.
	private void sendCommandDescriptions() {
		ChatFormatting GRAY = ChatFormatting.GRAY;
		ChatFormatting RED = ChatFormatting.RED;
		ChatFormatting ITALIC = ChatFormatting.ITALIC;
		for(Command c : Main.INSTANCE.commandManager.commands) {
			sendClientChatMessage(c.name + " - " + GRAY + c.description + RED + ITALIC + " [" + c.syntax + "]", false);
		}
	}
	
	@SubscribeEvent
	public void openChatScreen(KeyInputEvent e) {
		if (prefix.length() == 1) {
            final char key = Keyboard.getEventCharacter();
            if (prefix.charAt(0) == key) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
				assert Minecraft.getMinecraft().currentScreen != null;
				((GuiChat) Minecraft.getMinecraft().currentScreen).inputField.setText(prefix);
            }
        }
	}
	
	public void setCommandPrefix(String pre) {
        prefix = pre;
        
        if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
    }

	public void sendClientChatMessage(String message, boolean prefix) {
		String messageWithPrefix = ChatFormatting.GRAY + "@" + ChatFormatting.ITALIC + Reference.NAME + ChatFormatting.RESET + ": " + message;

		if(prefix)
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(messageWithPrefix));
		else
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
	
	public void sendCorrectionMessage(String name, String syntax) {
		String correction = "correct usage of " + name + " command -> " + prefix + syntax + ".";
		String message = ChatFormatting.GRAY + "@" + ChatFormatting.ITALIC + Reference.NAME + ChatFormatting.RESET + ": " + correction;
		
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
}
