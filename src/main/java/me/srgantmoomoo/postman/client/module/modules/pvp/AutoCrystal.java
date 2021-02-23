package me.srgantmoomoo.postman.client.module.modules.pvp;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.srgantmoomoo.postman.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.api.util.render.JTessellator;
import me.srgantmoomoo.postman.api.util.world.JTimer;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.notification.Notification;
import me.srgantmoomoo.postman.client.notification.NotificationManager;
import me.srgantmoomoo.postman.client.notification.NotificationType;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.Potion;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	//redo
	public BooleanSetting breakCrystal = new BooleanSetting("brkCrystal", this, true);
	public NumberSetting breakSpeed = new NumberSetting("brkSpeed", this, 20, 0, 20, 1);
	public ModeSetting breakType = new ModeSetting("brkType", this, "packet", "swing", "packet");
	public ModeSetting breakHand = new ModeSetting("brkHand", this, "both", "main", "offhand", "both");
	public ModeSetting breakMode = new ModeSetting("brkMode", this, "all", "all", "smart", "own");
	public NumberSetting breakRange = new NumberSetting("brkRange", this, 4.4, 0.0, 10.0, 0.1);
	
	public BooleanSetting placeCrystal = new BooleanSetting("plcCrystal", this, true);
	public NumberSetting placeRange = new NumberSetting("plcRange", this, 4.4, 0.0, 6.0, 0.1);
	public NumberSetting facePlaceValue = new NumberSetting("facePlcVal", this, 8, 0, 36, 1);
	
	public BooleanSetting raytrace = new BooleanSetting("raytrace", this, true);
	public BooleanSetting outline = new BooleanSetting("outline", this, false);
	public BooleanSetting showDamage = new BooleanSetting("showDamage", this, true);
	
	public NumberSetting maxSelfDmg = new NumberSetting("maxSelfDmg", this, 10, 0, 36, 1);
	public NumberSetting wallsRange = new NumberSetting("wallsRange", this, 3.5, 0.0, 10.0, 0.1);
	public NumberSetting minDmg = new NumberSetting("minDmg", this, 5, 0, 36, 1);
	public NumberSetting enemyRange = new NumberSetting("enemyRange", this, 6.0, 0.0, 16.0, 1.0);
	
	public BooleanSetting switchToCrystal = new BooleanSetting("switchToCrystal", this, false);
	public BooleanSetting cancelCrystal = new BooleanSetting("cancelCrystal", this, true);
	public BooleanSetting rotate = new BooleanSetting("rotate", this, true);
	public BooleanSetting spoofRotations = new BooleanSetting("spoofRotations", this, true);
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(0, 255, 0, 255));

	public AutoCrystal() {
		super ("autoCrystal", "best ca on the block.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(breakCrystal,placeCrystal,breakMode,breakType,breakHand,breakSpeed,breakRange,placeRange,cancelCrystal,switchToCrystal,rotate,spoofRotations,minDmg,maxSelfDmg,wallsRange
				,enemyRange,facePlaceValue,raytrace,outline,showDamage,color);
	}
	
	private boolean switchCooldown = false;
	private BlockPos renderBlock;
	private EnumFacing enumFacing;
	private Entity renderEnt;
	
	private final ArrayList<BlockPos> PlacedCrystals = new ArrayList<BlockPos>();
	public boolean active = false;
	boolean offHand = false;
	private static boolean togglePitch = false;
	
	JTimer timer = new JTimer();
	
	@Override
	public void onEnable() {
		NotificationManager.show(new Notification(NotificationType.INFO, "autoCrystal", "autoCrystal enabled", 1));
		Main.EVENT_BUS.subscribe(this);
		PlacedCrystals.clear();
		active = false;
	}
	
	@Override
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
		renderBlock = null;
        renderEnt = null;
        resetRotation();
        PlacedCrystals.clear();
        active = false;
	}
	
	public void onUpdate() {
		if(mc.player == null || mc.world == null)
			return;
		
		implementLogic();
	}
	
	private void implementLogic() {
		breakLogic();
		placeLogic();
	}
	
	private void breakLogic() {
		 EntityEnderCrystal crystal = mc.world.loadedEntityList.stream()
                 .filter(entity -> entity instanceof EntityEnderCrystal)
                 .filter(e -> mc.player.getDistance(e) <= breakRange.getValue())
                 .filter(e -> crystalCheck(e))
                 .map(entity -> (EntityEnderCrystal) entity)
                 .min(Comparator.comparing(c -> mc.player.getDistance(c)))
                 .orElse(null);
		 
		 if(breakCrystal.isEnabled() && crystal !=null) {
			 if (!mc.player.canEntityBeSeen(crystal) && mc.player.getDistance(crystal) > wallsRange.getValue())
	                return;
			 
			 if(timer.getTimePassed() / 50 >= 20 - breakSpeed.getValue()) {
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
		 int crystalSlot = mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL ? mc.player.inventory.currentItem : -1;
	        if (crystalSlot == -1) {
	            for (int l = 0; l < 9; ++l) {
	                if (mc.player.inventory.getStackInSlot(l).getItem() == Items.END_CRYSTAL) {
	                    if (mc.player.getHeldItem(EnumHand.OFF_HAND).getItem() != Items.END_CRYSTAL) {
	                        crystalSlot = l;
	                        break;
	                    }
	                }
	            }
	        }
		
		if(mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) offHand=true;
		else offHand=false;
		
		List<BlockPos> blocks = findCrystalBlocks();
		List<Entity> entities = new ArrayList<>();
		
		entities.addAll(mc.world.playerEntities.stream().collect(Collectors.toList()));
		
		BlockPos blockPos1 = null;
		double damage = 0.5D;
		
		if(!placeCrystal.isEnabled())
			return;
		
		if (!offHand && mc.player.inventory.currentItem != crystalSlot) {
            if (this.switchToCrystal.isEnabled()) {
                    mc.player.inventory.currentItem = crystalSlot;
                    resetRotation();
                    this.switchCooldown = true;
            }
            return;
        }
		
		for(Entity entity : entities) {
			if(entity == mc.player || ((EntityLivingBase)entity).getHealth() <= 0) continue;
			
			for(BlockPos blockPos : blocks) {
				double b = entity.getDistanceSq(blockPos);
				
				if(b >= Math.pow(enemyRange.getValue(), 2))
					continue;
				
				double d = calculateDamage(blockPos.getX() + 0.5D, blockPos.getY() + 1, blockPos.getZ() + 0.5D, entity);
				
				if(d < minDmg.getValue() && ((EntityLivingBase)entity).getHealth() + ((EntityLivingBase) entity).getAbsorptionAmount() > facePlaceValue.getValue()) 
					continue;
				
				if (d > damage) {
                    double self = calculateDamage(blockPos.getX() + 0.5D, blockPos.getY() + 1, blockPos.getZ() + 0.5D, mc.player);

                    if ((self > d && !(d < ((EntityLivingBase) entity).getHealth())) || self - 0.5D > mc.player.getHealth()) continue;

                    if (self > maxSelfDmg.getValue()) 
                    	continue;

                    damage = d;
                    blockPos1 = blockPos;
                    renderEnt = entity;
                }	
			}
		}
		
		if (damage == 0.5D) {
            renderBlock = null;
            renderEnt = null;
            resetRotation();
            return;
        }
		
		renderBlock = blockPos1;

		if(timer.getTimePassed() / 50 >= 20 - breakSpeed.getValue()) {

                if (rotate.isEnabled()) {
                    lookAtPacket(blockPos1.getX() + 0.5D, blockPos1.getY() - 0.5D, blockPos1.getZ() + 0.5D, mc.player);
                }

                RayTraceResult result = mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(blockPos1.getX() + 0.5D, blockPos1.getY() - 0.5D, blockPos1.getZ() + 0.5D));

                if (raytrace.isEnabled()) {
                    if (result == null || result.sideHit == null) {
                        enumFacing = null;
                        renderBlock = null;
                        resetRotation();
                        return;
                    } else {
                        enumFacing = result.sideHit;
                    }
                }
                
                if (this.switchCooldown) {
                    this.switchCooldown = false;
                    return;
                }
                
                if (blockPos1 != null) {
                    if (raytrace.isEnabled() && enumFacing != null) {
                        mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(blockPos1, enumFacing, offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0, 0, 0));
                    } else if (blockPos1.getY() == 255) {
                        mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(blockPos1, EnumFacing.DOWN, offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0, 0, 0));
                    } else {
                        mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(blockPos1, EnumFacing.UP, offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0, 0, 0));
                    }
                    mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
                    PlacedCrystals.add(blockPos1);
                }
                
                if (isSpoofingAngles) {
                    if (togglePitch) {
                        mc.player.rotationPitch += 0.0004;
                        togglePitch = false;
                    } else {
                        mc.player.rotationPitch -= 0.0004;
                        togglePitch = true;
                }
            }
            if (!placeCrystal.isEnabled()) return;
            timer.reset();
        }
		
	}
	
	public void onWorldRender(RenderEvent event) {
        if (this.renderBlock != null) {
        	JTessellator.drawBox(this.renderBlock,1, new JColor(color.getValue()), 255);
        	if(outline.isEnabled()) JTessellator.drawBoundingBox(this.renderBlock, 1, 1.00f, new JColor(color.getValue(),255));
        }

        if(showDamage.isEnabled()) {
            if (this.renderBlock != null && this.renderEnt != null) {
                double d = calculateDamage(renderBlock.getX() + .5, renderBlock.getY() + 1, renderBlock.getZ() + .5, renderEnt);
                String[] damageText=new String[1];
                damageText[0]=(Math.floor(d) == d ? (int) d : String.format("%.1f", d)) + "";
                JTessellator.drawNametag(renderBlock.getX()+0.5,renderBlock.getY()+0.5,renderBlock.getZ()+0.5,damageText,new JColor(255,255,255),1);
            }
        }
    }
	
	private void breakCrystal(EntityEnderCrystal crystal) {
        mc.playerController.attackEntity(mc.player, crystal);

        swingArm();
    }
	
	private void swingArm() {
        if (breakHand.getMode().equalsIgnoreCase("both") && mc.player.getHeldItemOffhand() != null) {
            mc.player.swingArm(EnumHand.MAIN_HAND);
            mc.player.swingArm(EnumHand.OFF_HAND);
        }
        else if (breakHand.getMode().equalsIgnoreCase("offhand") && mc.player.getHeldItemOffhand() != null) {
            mc.player.swingArm(EnumHand.OFF_HAND);
        }
        else {
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
	
	@EventHandler
    private final Listener<PacketEvent.Send> packetSendListener = new Listener<>(event -> {
        Packet packet = event.getPacket();
        if (packet instanceof CPacketPlayer && spoofRotations.isEnabled()) {
            if (isSpoofingAngles) {
                ((CPacketPlayer) packet).yaw = (float) yaw;
                ((CPacketPlayer) packet).pitch = (float) pitch;
            }
        }
    });

    @EventHandler
    private final Listener<PacketEvent.Receive> packetReceiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();
            if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                for (Entity e : Minecraft.getMinecraft().world.loadedEntityList) {
                    if (e instanceof EntityEnderCrystal) {
                        if (e.getDistance(packet.getX(), packet.getY(), packet.getZ()) <= 6.0f) {
                            e.setDead();
                        }
                    }
                }
            }
        }
    });
	
	/*
	 * Crystal Utils from gamesense
	 */
    
    private boolean crystalCheck(Entity crystal) {

        if (!(crystal instanceof EntityEnderCrystal)) {
            return false;
        }

        if (breakMode.getMode().equalsIgnoreCase("All")) {
            return true;
        }
        else if (breakMode.getMode().equalsIgnoreCase("Own")) {
            for (BlockPos pos : new ArrayList<>(PlacedCrystals)) {
                if (pos != null && pos.getDistance((int)crystal.posX, (int)crystal.posY, (int)crystal.posZ) <= 3.0) {
                    return true;
                }
            }
        }
        else if (breakMode.getMode().equalsIgnoreCase("Smart")) {
            EntityLivingBase target = renderEnt != null ? (EntityLivingBase) renderEnt : GetNearTarget(crystal);

            if (target == null || target == mc.player) {
                return false;
            }

            float targetDmg = calculateDamage(crystal.posX + 0.5, crystal.posY + 1, crystal.posZ + 0.5, target);

            return targetDmg >= minDmg.getValue() || (targetDmg > minDmg.getValue()) && target.getHealth() > facePlaceValue.getValue();
        }

        return false;
    }
    
    private boolean validTarget(Entity entity) {
        if (entity == null)
            return false;

        if (!(entity instanceof EntityLivingBase))
            return false;

        if (entity.isDead || ((EntityLivingBase) entity).getHealth() <= 0.0F)
            return false;

        if (entity instanceof EntityPlayer) {
            return entity != mc.player;
        }

        return false;
    }
    
    private EntityLivingBase GetNearTarget(Entity distanceTarget) {
        return mc.world.loadedEntityList.stream()
                .filter(entity -> validTarget(entity))
                .map(entity -> (EntityLivingBase) entity)
                .min(Comparator.comparing(entity -> distanceTarget.getDistance(entity)))
                .orElse(null);
    }
	
	private static float getDamageMultiplied(float damage) {
        int diff = mc.world.getDifficulty().getId();
        return damage * (diff == 0 ? 0 : (diff == 2 ? 1 : (diff == 1 ? 0.5f : 1.5f)));
    }
	
	public static float calculateDamage(double posX, double posY, double posZ, Entity entity) {
        float doubleExplosionSize = 12.0F;
        double distancedsize = entity.getDistance(posX, posY, posZ) / (double) doubleExplosionSize;
        Vec3d vec3d = new Vec3d(posX, posY, posZ);
        double blockDensity = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        double v = (1.0D - distancedsize) * blockDensity;
        float damage = (float) ((int) ((v * v + v) / 2.0D * 7.0D * (double) doubleExplosionSize + 1.0D));
        double finald = 1.0D;

        if (entity instanceof EntityLivingBase) {
            finald = getBlastReduction((EntityLivingBase) entity, getDamageMultiplied(damage), new Explosion(mc.world, null, posX, posY, posZ, 6F, false, true));
        }
        return (float) finald;
    }
	
	public static float getBlastReduction(EntityLivingBase entity, float damage, Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer) entity;
            DamageSource ds = DamageSource.causeExplosionDamage(explosion);
            damage = CombatRules.getDamageAfterAbsorb(damage, (float) ep.getTotalArmorValue(), (float) ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());

            int k = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
            float f = MathHelper.clamp(k, 0.0F, 20.0F);
            damage *= 1.0F - f / 25.0F;

            if (entity.isPotionActive(Potion.getPotionById(11))) {
                damage = damage - (damage / 4);
            }
            damage = Math.max(damage, 0.0F);
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb(damage, (float) entity.getTotalArmorValue(), (float) entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return damage;
    }
	
	public boolean canPlaceCrystal(BlockPos blockPos) {
        BlockPos boost = blockPos.add(0, 1, 0);
        BlockPos boost2 = blockPos.add(0, 2, 0);
            return (mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK
                    || mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN)
                    && mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost)).isEmpty()
                    && mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost2)).isEmpty();
    }
	
	public List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
        List<BlockPos> circleblocks = new ArrayList<>();
        int cx = loc.getX();
        int cy = loc.getY();
        int cz = loc.getZ();
        for (int x = cx - (int) r; x <= cx + r; x++) {
            for (int z = cz - (int) r; z <= cz + r; z++) {
                for (int y = (sphere ? cy - (int) r : cy); y < (sphere ? cy + r : cy + h); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < r * r && !(hollow && dist < (r - 1) * (r - 1))) {
                        BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
	
	public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }
	
	private List<BlockPos> findCrystalBlocks() {
        NonNullList<BlockPos> positions = NonNullList.create();
        positions.addAll(getSphere(getPlayerPos(), (float)placeRange.getValue(), (int)placeRange.getValue(), false, true, 0).stream().filter(this::canPlaceCrystal).collect(Collectors.toList()));
        return positions;
    }
	
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