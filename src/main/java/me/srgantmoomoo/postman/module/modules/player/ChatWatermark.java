package me.srgantmoomoo.postman.module.modules.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.event.events.PacketEvent;
import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.common.MinecraftForge;

public class ChatWatermark extends Module {
	
	public ChatWatermark() {
		super ("chatWatermark", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings();
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setup(){

		ArrayList<String> Separators = new ArrayList<>();
		Separators.add(">>");
		Separators.add("<<");
		Separators.add("|");

	}

	@EventHandler
	private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
		if (event.getPacket() instanceof CPacketChatMessage){
			if (((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage) event.getPacket()).getMessage().startsWith("."))
				return;
			String Separator2 = null;
				Separator2 = " \u300b";
				
			String old = ((CPacketChatMessage) event.getPacket()).getMessage();
			String suffix = Separator2 + toUnicode(Refrence.NAME);
			String s = old + suffix;
			if (s.length() > 255) return;
			((CPacketChatMessage) event.getPacket()).message = s;
		}
	});

	public void onEnable(){
		Main.EVENT_BUS.subscribe(this);
	}

	public void onDisable(){
		Main.EVENT_BUS.unsubscribe(this);
	}

	public String toUnicode(String s){
		return s.toLowerCase()
				.replace("a", "\u1d00")
				.replace("b", "\u0299")
				.replace("c", "\u1d04")
				.replace("d", "\u1d05")
				.replace("e", "\u1d07")
				.replace("f", "\ua730")
				.replace("g", "\u0262")
				.replace("h", "\u029c")
				.replace("i", "\u026a")
				.replace("j", "\u1d0a")
				.replace("k", "\u1d0b")
				.replace("l", "\u029f")
				.replace("m", "\u1d0d")
				.replace("n", "\u0274")
				.replace("o", "\u1d0f")
				.replace("p", "\u1d18")
				.replace("q", "\u01eb")
				.replace("r", "\u0280")
				.replace("s", "\ua731")
				.replace("t", "\u1d1b")
				.replace("u", "\u1d1c")
				.replace("v", "\u1d20")
				.replace("w", "\u1d21")
				.replace("x", "\u02e3")
				.replace("y", "\u028f")
				.replace("z", "\u1d22");
	}
}
