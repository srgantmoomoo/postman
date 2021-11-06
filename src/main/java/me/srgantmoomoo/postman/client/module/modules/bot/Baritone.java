package me.srgantmoomoo.postman.client.module.modules.bot;

import baritone.api.BaritoneAPI;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import org.lwjgl.input.Keyboard;

public class Baritone extends Module {
	public final BooleanSetting renderPath = new BooleanSetting("renderPath", this, true);
	public final ColorSetting pathColor = new ColorSetting("pathColor", this, new JColor(Reference.POSTMAN_COLOR, 255));
	public final BooleanSetting renderGoal = new BooleanSetting("renderGoal", this, true);
	public final ColorSetting goalColor = new ColorSetting("goalColor", this, new JColor(Reference.POSTMAN_COLOR, 255));
	
	public final BooleanSetting placeBlocks = new BooleanSetting("placeBlocks", this, true);
	public final BooleanSetting breakBlocks = new BooleanSetting("breakBlocks", this, true);
	public final BooleanSetting avoidDanger = new BooleanSetting("avoidDanger", this, true);
	public final BooleanSetting sprint = new BooleanSetting("sprint", this, true);
	public final BooleanSetting parkour = new BooleanSetting("parkour", this, true);
	public final BooleanSetting waterBucket = new BooleanSetting("waterBucket", this, true);
	public final BooleanSetting lava = new BooleanSetting("lava", this, false);
	public final BooleanSetting water = new BooleanSetting("water", this, true);
	public final BooleanSetting downward = new BooleanSetting("downward", this, true);
	public final BooleanSetting jumpAtBuildLimit = new BooleanSetting("jumpAtBuildLimit", this, true);
	
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