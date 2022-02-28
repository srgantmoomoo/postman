package me.srgantmoomoo.postman.impl.modules.player;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.GameProfile;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.world.GameType;

/*
 * Taken from gamesense, edited a little by @SrgantMooMoo on November 6th, 2020.
 */
public class PlayerClone extends Module {
	
	public PlayerClone() {
		super ("playerClone", "cloneeee.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	private EntityOtherPlayerMP clonedPlayer;
	
	@Override
	public void onEnable() { 
		Minecraft mc = Minecraft.getMinecraft();

        if (mc.player == null || mc.player.isDead){
            disable();
            return;
        }

    	clonedPlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("fdee323e-7f0c-4c15-8d1c-0f277442342a"), "ThePostman"));
        clonedPlayer.copyLocationAndAnglesFrom(mc.player);
        clonedPlayer.rotationYawHead = mc.player.rotationYawHead;
        clonedPlayer.rotationYaw = mc.player.rotationYaw;
        clonedPlayer.rotationPitch = mc.player.rotationPitch;
        clonedPlayer.setGameType(GameType.SURVIVAL);
        clonedPlayer.setHealth(20);
        mc.world.addEntityToWorld(-1234, clonedPlayer);
        clonedPlayer.onLivingUpdate();
    }

	@Override
    public void onDisable() {
		Minecraft mc = Minecraft.getMinecraft();

        if (mc.world != null) {
            mc.world.removeEntityFromWorld(-1234);
        }
    }
}