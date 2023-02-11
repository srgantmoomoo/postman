package me.srgantmoomoo.postman.event;

public class Event<T> {
    private boolean cancelled;
    private Type type;
    private Direction direction;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isPre() {
        if(type == null)
            return false;
        return type == Type.PRE;
    }

    public boolean isIncoming() {
        if(direction == null)
            return false;
        return direction == Direction.INCOMING;
    }

    public boolean isOutgoing() {
        if(direction == null)
            return false;
        return direction == Direction.OUTGOING;
    }
}
