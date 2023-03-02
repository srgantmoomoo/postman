package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;
import net.minecraft.network.Packet;

public class EventPacket extends Event<EventPacket> {
    private final Packet packet;

    public EventPacket(Packet packet) {
        super();
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public static class Receive extends EventPacket {
        public Receive(Packet packet) {
            super(packet);
        }
    }

    public static class Send extends EventPacket {
        public Send(Packet packet) {
            super(packet);
        }
    }

    public static class PostReceive extends EventPacket {
        public PostReceive(Packet packet) {
            super(packet);
        }
    }

    public static class PostSend extends EventPacket {
        public PostSend(Packet packet) {
            super(packet);
        }
    }
}
