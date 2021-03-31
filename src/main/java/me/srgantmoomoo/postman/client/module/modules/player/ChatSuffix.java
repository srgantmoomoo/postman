package me.srgantmoomoo.postman.client.module.modules.player;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
	
	public ChatSuffix() {
		super ("chatSuffix", "adds postman suffix to all of ur chat msg's.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings();
	}

	@SubscribeEvent
	public void onChat(final ClientChatEvent event) {
		if(toggled) {
			for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@")) {
				if (event.getMessage().startsWith(s)) return;
			}
			event.setMessage(event.getMessage() + " " + "ᕦᴘᴏѕᴛຕ" + "\u1d00" + "η " + "ѕᴛяᴏηɢᕤ");
		}
	}
	
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
}
