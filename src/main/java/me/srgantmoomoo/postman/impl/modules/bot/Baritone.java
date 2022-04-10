package me.srgantmoomoo.postman.impl.modules.bot;

import org.lwjgl.input.Keyboard;

import baritone.api.BaritoneAPI;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;

public class Baritone extends Module {
	public BooleanSetting renderPath = new BooleanSetting("renderPath", this, true);
	public ColorSetting pathColor = new ColorSetting("pathColor", this, new JColor(Reference.POSTMAN_COLOR, 255));
	public BooleanSetting renderGoal = new BooleanSetting("renderGoal", this, true);
	public ColorSetting goalColor = new ColorSetting("goalColor", this, new JColor(Reference.POSTMAN_COLOR, 255));
	
	public BooleanSetting placeBlocks = new BooleanSetting("placeBlocks", this, true);
	public BooleanSetting breakBlocks = new BooleanSetting("breakBlocks", this, true);
	public BooleanSetting avoidDanger = new BooleanSetting("avoidDanger", this, true);
	public BooleanSetting sprint = new BooleanSetting("sprint", this, true);
	public BooleanSetting parkour = new BooleanSetting("parkour", this, true);
	public BooleanSetting waterBucket = new BooleanSetting("waterBucket", this, true);
	public BooleanSetting lava = new BooleanSetting("lava", this, false);
	public BooleanSetting water = new BooleanSetting("water", this, true);
	public BooleanSetting downward = new BooleanSetting("downward", this, true);
	public BooleanSetting jumpAtBuildLimit = new BooleanSetting("jumpAtBuildLimit", this, true);
	
	public Baritone() {
		super("baritone", "use <prefix>b for baritone commands.", Keyboard.KEY_NONE, Category.BOT);
		this.addSettings(renderPath, pathColor, renderGoal, goalColor, placeBlocks, breakBlocks, avoidDanger, sprint, parkour, waterBucket, lava, water, downward, jumpAtBuildLimit);
		toggled = true;
	}

    @Override
    public void onDisable() {
    	if(mc.player == null || mc.world == null) return;
        enable();
    }

    @Override
    public void onUpdate() {
    	BaritoneAPI.getSettings().renderPath.value = renderPath.isEnabled();
        BaritoneAPI.getSettings().colorCurrentPath.value = new JColor(pathColor.getValue());
        BaritoneAPI.getSettings().renderGoal.value = renderGoal.isEnabled();
        BaritoneAPI.getSettings().colorGoalBox.value = new JColor(goalColor.getValue());
        
        BaritoneAPI.getSettings().allowPlace.value = placeBlocks.isEnabled();
        BaritoneAPI.getSettings().allowBreak.value = breakBlocks.isEnabled();
        BaritoneAPI.getSettings().avoidance.value = avoidDanger.isEnabled();
        BaritoneAPI.getSettings().allowSprint.value = sprint.isEnabled();
        BaritoneAPI.getSettings().allowParkour.value = parkour.isEnabled();
        BaritoneAPI.getSettings().allowWaterBucketFall.value = waterBucket.isEnabled();
        BaritoneAPI.getSettings().assumeWalkOnLava.value = lava.isEnabled();
        BaritoneAPI.getSettings().okIfWater.value = water.isEnabled();
        BaritoneAPI.getSettings().allowDownward.value = downward.isEnabled();
        BaritoneAPI.getSettings().allowJumpAt256.value = jumpAtBuildLimit.isEnabled();
    }
}