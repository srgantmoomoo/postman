package me.srgantmoomoo.postman.module;

public enum Category {
	PLAYER("player"), RENDER("render"), PVP("pvp"), EXPLOITS("exploits"), CLIENT("client");
	
	public String name;
	public int moduleIndex;
	
	Category(String name) {
		this.name = name;
	}

}
