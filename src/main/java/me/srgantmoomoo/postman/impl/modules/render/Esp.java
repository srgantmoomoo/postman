package me.srgantmoomoo.postman.impl.modules.render;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.world.EnumDifficulty;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.backend.util.Wrapper;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.backend.util.render.JTessellator;
import me.srgantmoomoo.postman.backend.util.world.GeometryMasks;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.BlockPos;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

/**
 * rewritten... some utils are used from gamesnse. i'm starting to realize there is more gamesense skid in this client then actually thought.
 * @author SrgantMooMoo
 * @since 3/1/22
 */

//TODO 2d esp's and outline esp's.
public class Esp extends Module {
    public BooleanSetting chams = new BooleanSetting("walls", this, false);
    public ModeSetting entityMode = new ModeSetting("entity", this, "outline", "outline", "fill", "outline & fill", "trace", "fake2d", "glow", "off");
    public ModeSetting storage = new ModeSetting("storage", this, "outline", "outline", "fill", "outline & fill", "off");
    public ModeSetting crystalMode = new ModeSetting("crystal", this, "pretty", "pretty", "glow", "off");

    public BooleanSetting mobs = new BooleanSetting("mobs", this, false);
    public BooleanSetting items = new BooleanSetting("items", this, true);
    public NumberSetting range = new NumberSetting("range", this, 100, 10, 260, 1);
    public NumberSetting lineWidth = new NumberSetting("lineWidth", this, 3, 0, 10, 1);

    public ColorSetting playerColor = new ColorSetting("player", this, new JColor(0, 121, 194, 100));
    public ColorSetting hostileMobColor = new ColorSetting("hostileMob", this, new JColor(255, 0, 0, 100));
    public ColorSetting passiveMobColor = new ColorSetting("passiveMob", this, new JColor(0, 255, 0, 100));
    public ColorSetting itemColor = new ColorSetting("item", this, new JColor(0, 121, 194, 100));

    public ColorSetting chestColor = new ColorSetting("chest", this, new JColor(255, 255, 0, 100));
    public ColorSetting enderChestColor = new ColorSetting("enderChest", this, new JColor(255, 70, 200, 100));
    public ColorSetting shulkerBoxColor = new ColorSetting("shulkerBox", this, new JColor(255, 182, 193, 100));
    public ColorSetting otherColor = new ColorSetting("otherContainers", this, new JColor(150, 150, 150, 100));

    public Esp() {
        super ("esp's", "draws esp's around things.", Keyboard.KEY_NONE, Category.RENDER);
        this.addSettings(entityMode, storage, crystalMode, mobs, items, chams, range, lineWidth, playerColor, passiveMobColor, hostileMobColor, itemColor, chestColor,
                enderChestColor, shulkerBoxColor, otherColor);
    }
    List<Entity> entities;

    JColor playerFillColor;
    JColor playerOutlineColor;
    JColor hostileMobFillColor;
    JColor hostileMobOutlineColor;
    JColor passiveMobFillColor;
    JColor passiveMobOutlineColor;
    JColor itemFillColor;
    JColor itemOutlineColor;
    JColor containerColor;
    JColor containerBox;
    int opacityGradient;

    public void onDisable() {
        if (entities != mc.player) {
            entities.forEach(e -> e.setGlowing(false));
        }
    }

    @Override
    public void onWorldRender(RenderEvent event) {

        mc.world.loadedEntityList.stream().filter(entity -> entity != mc.player).filter(this::rangeEntityCheck).forEach(entity -> {
            defineEntityColors(entity);

            // readable code :thumbs_up:
            // glow esp disabling stuff.
            if(entityMode.is("glow")) {
                if(!mobs.isEnabled() && (entity instanceof EntityCreature || entity instanceof EntityAnimal || entity instanceof EntitySlime))
                    entity.setGlowing(false);
                if(!items.isEnabled() && entity instanceof EntityItem)
                    entity.setGlowing(false);
            }else {
                if(!(entity instanceof EntityEnderCrystal))
                    entity.setGlowing(false);
            }
            if(!crystalMode.is("glow") && entity instanceof EntityEnderCrystal)
                entity.setGlowing(false);

            // entity esp's
            if(entityMode.is("outline")) {
                if(entity instanceof EntityPlayer) {
                    JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), playerOutlineColor);
                }
                if(mobs.isEnabled()) {
                    if(mc.world.getDifficulty().equals(EnumDifficulty.PEACEFUL))
                        return;
                    if(entity instanceof EntityAnimal) {
                        JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), passiveMobOutlineColor);
                    }else if(entity instanceof EntityCreature || entity instanceof EntitySlime) {
                        JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), hostileMobOutlineColor);
                    }
                }
                if(items.isEnabled() && entity instanceof EntityItem) {
                    JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), 2, itemOutlineColor);
                }
            }else if(entityMode.is("fill")) {
                if(entity instanceof EntityPlayer) {
                    JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), playerFillColor, GeometryMasks.Quad.ALL);
                }
                if(mobs.isEnabled()) {
                    if(mc.world.getDifficulty().equals(EnumDifficulty.PEACEFUL))
                        return;
                    if(entity instanceof EntityAnimal) {
                        JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), passiveMobFillColor, GeometryMasks.Quad.ALL);
                    }else if(entity instanceof EntityCreature || entity instanceof EntitySlime) {
                        JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), hostileMobFillColor, GeometryMasks.Quad.ALL);
                    }
                }
                if(items.isEnabled() && entity instanceof EntityItem) {
                    JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), itemFillColor, GeometryMasks.Quad.ALL);
                }
            }else if(entityMode.is("outline & fill")) {
                if(entity instanceof EntityPlayer) {
                    JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), playerOutlineColor);
                    JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), playerFillColor, GeometryMasks.Quad.ALL);
                }
                if(mobs.isEnabled()) {
                    if(mc.world.getDifficulty().equals(EnumDifficulty.PEACEFUL))
                        return;
                    if(entity instanceof EntityAnimal) {
                        JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), passiveMobOutlineColor);
                        JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), passiveMobFillColor, GeometryMasks.Quad.ALL);
                    }else if(entity instanceof EntityCreature || entity instanceof EntitySlime) {
                        JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), hostileMobOutlineColor);
                        JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), hostileMobFillColor, GeometryMasks.Quad.ALL);
                    }
                }
                if(items.isEnabled() && entity instanceof EntityItem) {
                    JTessellator.drawBoundingBox(entity.getEntityBoundingBox(), (float) lineWidth.getValue(), itemOutlineColor);
                    JTessellator.drawFillBox(entity.getEntityBoundingBox(), (float)lineWidth.getValue(), itemFillColor, GeometryMasks.Quad.ALL);
                }
            }else if(entityMode.is("fake2d")) { //TODO 2d fucks with nametags. & only works for players.
                if(entity instanceof EntityPlayer)
                    JTessellator.draw2dEsp(entity, (mc.getRenderManager()).playerViewY, (float)lineWidth.getValue(), playerFillColor);
                if(entity instanceof EntityAnimal)
                    JTessellator.draw2dEsp(entity, (mc.getRenderManager()).playerViewY, (float)lineWidth.getValue(), passiveMobFillColor);
                if(entity instanceof EntityCreature || entity instanceof EntitySlime)
                    JTessellator.draw2dEsp(entity, (mc.getRenderManager()).playerViewY, (float)lineWidth.getValue(), hostileMobFillColor);
                if(entity instanceof EntityItem)
                    JTessellator.draw2dEsp(entity, (mc.getRenderManager()).playerViewY, (float)lineWidth.getValue(), itemFillColor);
            }else if(entityMode.is("glow")) {
                if(entity instanceof EntityPlayer)
                    entity.setGlowing(true);
                if(mobs.isEnabled() && (entity instanceof EntityCreature || entity instanceof EntitySlime || entity instanceof EntityAnimal)) // don't need to seperate hostile and passive cause they all glow the same color.
                    entity.setGlowing(true);
                if(items.isEnabled() && entity instanceof EntityItem)
                    entity.setGlowing(true);
            }

            if(entity instanceof EntityEnderCrystal) {
                if(crystalMode.is("glow"))
                    entity.setGlowing(true);
            }

            // outline esp is under MixinRendererLivingBase.
        });

        //TODO i really don't feeling like rewriting this hell right now... so i'll save it for another time. i'll tweak it a little bit for now tho... just some easy stuff.
        mc.world.loadedTileEntityList.stream().filter(this::rangeTileCheck).forEach(tileEntity -> {

            if(storage.is("outline")) {
                if(tileEntity instanceof TileEntityChest) {
                    containerColor = new JColor(chestColor.getValue(), opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
                if(tileEntity instanceof TileEntityEnderChest) {
                    containerColor = new JColor(enderChestColor.getValue(), opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
                if(tileEntity instanceof TileEntityShulkerBox) {
                    containerColor = new JColor(shulkerBoxColor.getValue(), opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
                if(tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper) {
                    containerColor = new JColor(otherColor.getValue(), opacityGradient);
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                }
            }else if(storage.is("outline & fill")) {
                if(tileEntity instanceof TileEntityChest) {
                    containerColor = new JColor(chestColor.getValue(), opacityGradient);
                    containerBox = new JColor(chestColor.getValue());
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawStorageBox(tileEntity.getPos(), 1, containerBox);
                }
                if(tileEntity instanceof TileEntityEnderChest) {
                    containerColor = new JColor(enderChestColor.getValue(), opacityGradient);
                    containerBox = new JColor(enderChestColor.getValue());
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawStorageBox(tileEntity.getPos(), 1, containerBox);
                }
                if(tileEntity instanceof TileEntityShulkerBox) {
                    containerColor = new JColor(shulkerBoxColor.getValue(), opacityGradient);
                    containerBox = new JColor(shulkerBoxColor.getValue());
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawBox(tileEntity.getPos(), 1, containerBox);
                }
                if(tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper) {
                    containerColor = new JColor(otherColor.getValue(), opacityGradient);
                    containerBox = new JColor(otherColor.getValue());
                    JTessellator.drawBoundingBox(mc.world.getBlockState(tileEntity.getPos()).getSelectedBoundingBox(mc.world, tileEntity.getPos()), 2, containerColor);
                    drawBox(tileEntity.getPos(), 1, containerBox);
                }
            }else if(storage.is("fill")) {
                if(tileEntity instanceof TileEntityChest) {
                    containerBox = new JColor(chestColor.getValue());
                    drawStorageBox(tileEntity.getPos(), 1, containerBox);
                }
                if(tileEntity instanceof TileEntityEnderChest) {
                    containerBox = new JColor(enderChestColor.getValue());
                    drawStorageBox(tileEntity.getPos(), 1, containerBox);
                }
                if(tileEntity instanceof TileEntityShulkerBox) {
                    containerBox = new JColor(shulkerBoxColor.getValue());
                    drawBox(tileEntity.getPos(), 1, containerBox);
                }
                if(tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper) {
                    containerBox = new JColor(otherColor.getValue());
                    drawBox(tileEntity.getPos(), 1, containerBox);
                }
            }
        });
    }

    private void drawStorageBox(BlockPos blockPos, int width, JColor color) {
		JTessellator.drawStorageBox(blockPos, 0.88, color, GeometryMasks.Quad.ALL);
    }

    private void drawBox(BlockPos blockPos, int width, JColor color) {
		JTessellator.drawBox(blockPos, 1, color, GeometryMasks.Quad.ALL);
   }

   // these r from gamesense.
    private void defineEntityColors(Entity entity) {
        if (entity instanceof EntityPlayer) {
            playerFillColor = new JColor(playerColor.getValue());
            playerOutlineColor = new JColor(playerColor.getValue(), opacityGradient);
        }

        if(entity instanceof EntityMob || entity instanceof EntitySlime) {
            hostileMobFillColor = new JColor(hostileMobColor.getColor());
            hostileMobOutlineColor = new JColor(hostileMobColor.getValue(), opacityGradient);
        }
        else if (entity instanceof EntityAnimal) {
            passiveMobFillColor = new JColor(passiveMobColor.getValue());
        	passiveMobOutlineColor = new JColor(passiveMobColor.getValue(), opacityGradient);
        }
        else {
            passiveMobFillColor = new JColor(passiveMobColor.getValue());
            passiveMobOutlineColor = new JColor(passiveMobColor.getValue(), opacityGradient);
        }

        if(entity instanceof EntityItem) {
            itemFillColor = new JColor(itemColor.getValue());
            itemOutlineColor = new JColor(itemColor.getValue(), opacityGradient);
        }
    }

    private boolean rangeEntityCheck(Entity entity) {
        if (entity.getDistance(mc.player) > range.getValue()) {
            return false;
        }

        if (entity.getDistance(mc.player) >= 180) {
            opacityGradient = 50;
        }
        else if (entity.getDistance(mc.player) >= 130 && entity.getDistance(mc.player) < 180) {
            opacityGradient = 100;
        }
        else if (entity.getDistance(mc.player) >= 80 && entity.getDistance(mc.player) < 130) {
            opacityGradient = 150;
        }
        else if (entity.getDistance(mc.player) >= 30 && entity.getDistance(mc.player) < 80) {
            opacityGradient = 200;
        }
        else {
            opacityGradient = 255;
        }

        return true;
    }

    private boolean rangeTileCheck(TileEntity tileEntity) {
        //the range value has to be squared for this
        if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) > range.getValue() * range.getValue()){
            return false;
        }

        if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 32400){
            opacityGradient = 50;
        }
        else if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 16900 && tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) < 32400){
            opacityGradient = 100;
        }
        else if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 6400 && tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) < 16900){
            opacityGradient = 150;
        }
        else if (tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) >= 900 && tileEntity.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) < 6400){
            opacityGradient = 200;
        }
        else {
            opacityGradient = 255;
        }

        return true;
    }
}
