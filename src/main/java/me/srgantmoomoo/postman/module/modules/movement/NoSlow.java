package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventCollisionShape;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.shape.VoxelShapes;

// ts genuinely barely fucking works lol
public class NoSlow extends Module {
    public BooleanSetting food = new BooleanSetting("food", this, true);
    public BooleanSetting web = new BooleanSetting("web", this, true);
    public BooleanSetting soulSand = new BooleanSetting("soulSand", this, true);
    public BooleanSetting slimeBlock = new BooleanSetting("slimeBlock", this, true);

    public NoSlow() {
        super("noSlow", "slow? no.", Category.MOVEMENT, 0);
        this.addSettings(food, web, soulSand, slimeBlock);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventCollisionShape event) {
            if(soulSand.isEnabled() && event.getState().isOf(Blocks.SOUL_SAND)) {
                event.setShape(VoxelShapes.fullCube());
            }
        }
    }

    // food handled in MixinClientPlayerEntity
    // slimeblocks in MixinSlimeBlock
    // cobwebs in MixinEntity
}