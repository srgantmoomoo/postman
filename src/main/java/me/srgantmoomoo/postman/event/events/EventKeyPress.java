package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;

// posted in MixinKeyboard
public class EventKeyPress extends Event<EventKeyPress> {
    private int key;
    private int scanCode;

    public EventKeyPress(int key, int scanCode) {
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
