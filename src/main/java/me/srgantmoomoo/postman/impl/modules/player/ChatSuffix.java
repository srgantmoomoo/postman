package me.srgantmoomoo.postman.impl.modules.player;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "fucked lol");
	public BooleanSetting discludePercent = new BooleanSetting("disclude%", this, true);
	
	public ChatSuffix() {
		super ("chatSuffix", "adds postman suffix to all of ur chat msg's.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(mode);
	}

	@SubscribeEvent
	public void onChat(final ClientChatEvent event) {
		if(toggled) {
			for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@", "#")) {
				if (event.getMessage().startsWith(s)) return;
			}
		}
	}
	
	@Override
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
}
