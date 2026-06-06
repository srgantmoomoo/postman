package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventPacket;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class NoKnockback extends Module {
    public NumberSetting playerKnockback = new NumberSetting("player", this, 0, 0, 100, 1);
    public NumberSetting explosionKnockback = new NumberSetting("explosion", this, 0, 0, 100, 1);

    public NoKnockback() {
        super("noKnockback", "take no knockback when taking damage.", Category.PLAYER, 0);
        this.addSettings(playerKnockback, explosionKnockback);
    }

    @Override
    public void onEvent(Event e) {
        if(MinecraftClient.getInstance().player == null)
            return;

        if(e instanceof EventPacket.Receive) {
            if(((EventPacket.Receive) e).getPacket() instanceof EntityVelocityUpdateS2CPacket p) {
                if(p.getEntityId() == MinecraftClient.getInstance().player.getId()) {
                    double velocity = playerKnockback.getValue() / 100;
                    double velocityX = (p.getVelocityX() / 8000d - MinecraftClient.getInstance().player.getVelocity().x) * velocity;
                    double velocityY = (p.getVelocityY() / 8000d - MinecraftClient.getInstance().player.getVelocity().y) * velocity;
                    double velocityZ = (p.getVelocityZ() / 8000d - MinecraftClient.getInstance().player.getVelocity().z) * velocity;
                    p.velocityX = (int) (velocityX * 8000 + MinecraftClient.getInstance().player.getVelocity().x * 8000);
                    p.velocityY = (int) (velocityY * 8000 + MinecraftClient.getInstance().player.getVelocity().y * 8000);
                    p.velocityZ = (int) (velocityZ * 8000 + MinecraftClient.getInstance().player.getVelocity().z * 8000);
                }
            }
            if(((EventPacket) e).getPacket() instanceof ExplosionS2CPacket p1) {
                p1.playerKnockback().ifPresent(knockback -> {
                    double velocity = explosionKnockback.getValue() / 100;
                    p1.playerKnockback = Optional.of(new Vec3d(
                            knockback.x * velocity,
                            knockback.y * velocity,
                            knockback.z * velocity
                    ));
                });
            }
        }
    }

}
