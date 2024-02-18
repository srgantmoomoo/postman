package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventFluidPush;
import me.srgantmoomoo.postman.event.events.EventPushAwayFromEntity;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;

public class NoPush extends Module {
    public BooleanSetting entities = new BooleanSetting("entities", this, true);
    public BooleanSetting fluid = new BooleanSetting("fluid", this, true);

    public NoPush() {
        super("noPush", "prevents you from being pushed by, or pushing other entities.", Category.PLAYER, 0);
        this.addSettings(entities, fluid);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventPushAwayFromEntity) {
            if(entities.isEnabled()) e.setCancelled(true);
        }
        if(e instanceof EventFluidPush) {
            if(fluid.isEnabled()) e.setCancelled(true);
        }
    }

}
