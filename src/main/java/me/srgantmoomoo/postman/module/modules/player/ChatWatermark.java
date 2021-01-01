package me.srgantmoomoo.postman.module.modules.player;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatWatermark extends Module {
	public boolean on;
	
	public ChatWatermark() {
		super ("chatSuffix", "if this is interfering with commands, turn it off", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings();
	}

	@SubscribeEvent
	public void onChat(final ClientChatEvent event)
	{
		if(on) {
		for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@"))
		{
			if (event.getMessage().startsWith(s)) return;
		}
		event.setMessage(event.getMessage() + " " + "\\u007c" + " " + "\u1d18\u1d0f\u0073\u1d1b\u1d0d\u1d00\u0274"); 
	}
	}
	public void onEnable() {
		super.onEnable();
		on = true;
	}
	
	public void onDisbale() {
		super.onDisable();
		on = false;
	}
}
