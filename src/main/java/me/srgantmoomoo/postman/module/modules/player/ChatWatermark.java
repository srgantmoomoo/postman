package me.srgantmoomoo.postman.module.modules.player;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatWatermark extends Module {
	
	public ChatWatermark() {
		super ("chatSuffix", "if this is interfering with commands, turn it off", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings();
	}

	@SubscribeEvent
	public void onChat(final ClientChatEvent event)
	{
		for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@"))
		{
			if (event.getMessage().startsWith(s)) return;
		}

		event.setMessage(event.getMessage() + " ｜ (postman)"); 
	}
}
