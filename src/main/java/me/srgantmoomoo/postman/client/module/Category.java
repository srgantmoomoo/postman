package me.srgantmoomoo.postman.client.module;

public enum Category {
	PLAYER("player"), RENDER("render"), PVP("pvp"), EXPLOITS("exploits"), MOVEMENT("movement"), CLIENT("client");
	
	public String name;
	public int moduleIndex;
	
	Category(String name) {
		this.name = name;
	}

}
