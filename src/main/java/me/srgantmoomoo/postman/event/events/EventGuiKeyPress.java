package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;

public class EventGuiKeyPress extends Event {
    private int key;
    private int scanCode;

    public EventGuiKeyPress(int key, int scanCode) {
        this.key = key;
        this.scanCode = scanCode;
    }

    public int getKey() {
        return key;
    }

    public int getScanCode() {
        return scanCode;
    }
}
