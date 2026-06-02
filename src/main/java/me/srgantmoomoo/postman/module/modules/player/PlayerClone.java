package me.srgantmoomoo.postman.module.modules.player;

import com.mojang.authlib.GameProfile;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class PlayerClone extends Module {
    private OtherClientPlayerEntity clonedPlayer;

    public PlayerClone() {
        super("playerClone", "clones your player.", Category.PLAYER, 0);
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null || mc.world == null) return;

        clonedPlayer = new OtherClientPlayerEntity(mc.world, mc.player.getGameProfile());
        clonedPlayer.copyPositionAndRotation(mc.player);
        clonedPlayer.headYaw = mc.player.headYaw;
        clonedPlayer.bodyYaw = mc.player.bodyYaw;
        clonedPlayer.setUuid(UUID.randomUUID());
        mc.world.addEntity(clonedPlayer);
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.world == null || clonedPlayer == null) return;

        mc.world.removeEntity(clonedPlayer.getId(), Entity.RemovalReason.DISCARDED);
        clonedPlayer = null;
    }
}