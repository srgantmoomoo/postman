package me.srgantmoomoo.postman.client.module;
// i got the bot idea from momentum, thanks linus, very based client.
public enum Category {
	PLAYER("player"), RENDER("render"), PVP("pvp"), EXPLOITS("exploits"), MOVEMENT("movement"), HUD("hud"), CLIENT("client"), BOT("bot");
	
	public String name;
	public int moduleIndex;
	
	Category(String name) {
		this.name = name;
	}

}
