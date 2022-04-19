package me.srgantmoomoo.postman.framework.module;
// I got the bot idea from momentum, thanks linus, very based client.
public enum Category {
	PLAYER("player"), RENDER("render"), PVP("pvp"), EXPLOITS("exploits"), MOVEMENT("movement"), HUD("hud"), CLIENT("client"), BOT("bot");
	
	public final String name;
	
	Category(String name) {
		this.name = name;
	}
}
