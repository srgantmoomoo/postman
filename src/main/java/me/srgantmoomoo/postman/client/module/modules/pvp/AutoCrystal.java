package me.srgantmoomoo.postman.client.module.modules.pvp;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.api.util.world.JTimer;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

import java.util.Comparator;

import org.lwjgl.input.Keyboard;

/**
 * @Author SrgantMooMoo
 * written on 1/18/2021
 * this was written by me, however, i took a lot of inspiration from a few other clients for this cause ive never written autocrystal before so here are some of the clients i used for help :)
 * - past
 * - gamesense
 * - wurstplus2
 * - salhack
 * also, i'm using some crystalUtils from gamesense listed below.
 */

public class AutoCrystal extends Module {
	public BooleanSetting breakCrystal = new BooleanSetting("breakCrystal", this, true);
	public BooleanSetting antiWeakness = new BooleanSetting("antiWeakness", this, true);
	public BooleanSetting placeCrystal = new BooleanSetting("placeCrystal", this, true);
	public BooleanSetting autoSwitch = new BooleanSetting("autoSwitch", this, true);
	public BooleanSetting raytrace = new BooleanSetting("raytrace", this, false);
	public BooleanSetting rotate = new BooleanSetting("rotate", this, true);
	public BooleanSetting spoofRotations = new BooleanSetting("spoofRotations", this, true);
	public BooleanSetting showDamage = new BooleanSetting("showDamage", this, true);
	public BooleanSetting multiPlace = new BooleanSetting("multiPlace", this, false);
	public BooleanSetting antiSuicide = new BooleanSetting("antiSuicide", this, true);
	public BooleanSetting endCrystalMode = new BooleanSetting("endCrystalMode", this, false);
	public BooleanSetting cancelCrystal = new BooleanSetting("cancelCrystal", this, false);
	public BooleanSetting noGapSwitch = new BooleanSetting("noGapSwitch", this, false);
	
	public NumberSetting facePlaceValue = new NumberSetting("facePlaceValue", this, 8, 0, 36, 1);
	public NumberSetting attackSpeed = new NumberSetting("attackSpeed", this, 16, 0, 20, 1);
	public NumberSetting antiSuicideValue = new NumberSetting("antiSuicideValue", this, 14, 1, 36, 1);
	public NumberSetting maxSelfDmg = new NumberSetting("maxSelfDmg", this, 10, 1, 36, 1);
	public NumberSetting wallsRange = new NumberSetting("wallsRange", this, 3.5, 0.0, 10.0, 0.1);
	public NumberSetting minDmg = new NumberSetting("minDmg", this, 5, 0, 36, 1);
	public NumberSetting minBreakDmg = new NumberSetting("minBreakDmg", this, 5, 0, 36, 1);
	public NumberSetting enemyRange = new NumberSetting("enemyRange", this, 6.0, 0.0, 16.0, 1.0);
	public NumberSetting placeRange = new NumberSetting("placeRange", this, 4.4, 0.0, 6.0, 0.1);
	public NumberSetting breakRange = new NumberSetting("breakRange", this, 4.4, 0.0, 10.0, 0.1);
	
	public ModeSetting handBreak = new ModeSetting("handBreak", this, "main", "main", "offhand", "both");
	public ModeSetting breakMode = new ModeSetting("breakMode", this, "all", "all", "smart", "own");
	public ModeSetting hudDisplay = new ModeSetting("hudDisplay", this, "mode", "mode", "none");
	public ModeSetting breakType = new ModeSetting("breakType", this, "swing", "swing", "packet");
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(0, 255, 0, 255));

	public AutoCrystal() {
		super ("autoCrystal", "best ca on the block", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(breakMode,handBreak,breakType,breakCrystal,placeCrystal,attackSpeed,breakRange,placeRange,wallsRange,enemyRange,antiWeakness,antiSuicide,antiSuicideValue,autoSwitch,noGapSwitch
				,multiPlace,endCrystalMode,cancelCrystal,minDmg,minBreakDmg,maxSelfDmg,facePlaceValue,rotate,spoofRotations,raytrace,showDamage,hudDisplay,color);
	}
	private BlockPos renderBlock;
	private EnumFacing enumFacing;
	private Entity renderEnt;
	
	public boolean active = false;
	private static boolean togglePitch = false;
	
	private double showDamageText;
	
	JTimer timer = new JTimer();
	
	@Override
	public void onEnable() {
		super.onEnable();
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	
	}
	
	public void onUpdate() {
		
	}
	
	private void breakLogic() {
		if (antiSuicide.isEnabled() && (mc.player.getHealth() + mc.player.getAbsorptionAmount() <= antiSuicideValue.getValue()))
			return;
		
		 EntityEnderCrystal crystal = mc.world.loadedEntityList.stream()
                 .filter(entity -> entity instanceof EntityEnderCrystal)
                 .filter(e -> mc.player.getDistance(e) <= breakRange.getValue())
                 .map(entity -> (EntityEnderCrystal) entity)
                 .min(Comparator.comparing(c -> mc.player.getDistance(c)))
                 .orElse(null);
		 
		 if(breakCrystal.isEnabled() && crystal !=null) {
			 if (!mc.player.canEntityBeSeen(crystal) && mc.player.getDistance(crystal) > wallsRange.getValue())
	                return;
			 
			 if(timer.getTimePassed() / 50 >= 20 - attackSpeed.getValue()) {
				 timer.reset();
				 active=true;
				 
				 if(rotate.isEnabled()) {
					 lookAtPacket(crystal.posX, crystal.posY, crystal.posZ, mc.player);
				 }
				 
				 if(breakType.is("swing")) {
					 breakCrystal(crystal);
				 }
				 if(breakType.is("packet")) {
					 mc.player.connection.sendPacket(new CPacketUseEntity(crystal));
					 swingArm();
				 }
				 
				 if(cancelCrystal.isEnabled()) {
					 crystal.setDead();
					 mc.world.removeAllEntities();
					 mc.world.getLoadedEntityList();
				 }
				 
				 active=false;
			 }
		 }
		 else {
			 resetRotation();
			 
			 active=false;
		 }
	}
	
	private void placeLogic() {
		
	}
	
	private void breakCrystal(EntityEnderCrystal crystal) {
        mc.playerController.attackEntity(mc.player, crystal);

        swingArm();
    }
	
	private void swingArm() {
        if (handBreak.getMode().equalsIgnoreCase("both") && mc.player.getHeldItemOffhand() != null) {
            mc.player.swingArm(EnumHand.MAIN_HAND);
            mc.player.swingArm(EnumHand.OFF_HAND);
        }
        else if (handBreak.getMode().equalsIgnoreCase("offhand") && mc.player.getHeldItemOffhand() != null) {
            mc.player.swingArm(EnumHand.OFF_HAND);
        }
        else {
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
	
	/*
	 * Crystal Utils from gamesense
	 */
	
	private static void resetRotation() {
        if (isSpoofingAngles) {
            yaw = mc.player.rotationYaw;
            pitch = mc.player.rotationPitch;
            isSpoofingAngles = false;
        }
    }
	
	private static boolean isSpoofingAngles;
    private static double yaw;
    private static double pitch;
	
	public static double[] calculateLookAt(double px, double py, double pz, EntityPlayer me) {
        double dirx = me.posX - px;
        double diry = me.posY - py;
        double dirz = me.posZ - pz;

        double len = Math.sqrt(dirx*dirx + diry*diry + dirz*dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        pitch = pitch * 180.0d / Math.PI;
        yaw = yaw * 180.0d / Math.PI;

        yaw += 90f;

        return new double[]{yaw,pitch};
    }
	
	private static void setYawAndPitch(float yaw1, float pitch1) {
        yaw = yaw1;
        pitch = pitch1;
        isSpoofingAngles = true;
    }
	
	private void lookAtPacket(double px, double py, double pz, EntityPlayer me) {
        double[] v = calculateLookAt(px, py, pz, me);
        setYawAndPitch((float) v[0], (float) v[1]);
    }

}