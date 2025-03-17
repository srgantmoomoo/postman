package me.srgantmoomoo.postman.module;

public enum Category {
    PLAYER("player"), MOVEMENT("movement"), PVP("pvp"), EXPLOITS("exploits"), RENDER("render"), HUD("hud"), CLIENT("client"), BOT("bot");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
