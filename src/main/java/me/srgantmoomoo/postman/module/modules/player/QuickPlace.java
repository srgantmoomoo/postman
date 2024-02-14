package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class QuickPlace extends Module {

    public QuickPlace() {
        super("quickPlace", "desc", Category.PLAYER, 0);
        throwables.add(Items.EXPERIENCE_BOTTLE);
        throwables.add(Items.SPLASH_POTION);
        throwables.add(Items.LINGERING_POTION);
        throwables.add(Items.SNOWBALL);
        throwables.add(Items.EGG);
        throwables.add(Items.ENDER_PEARL);
        throwables.add(Items.ENDER_EYE);
    }

    private final List<Item> throwables = new ArrayList<>();

    // the reverse of quickthrow lol
    public void onEvent(Event e) {
        if(MinecraftClient.getInstance().player == null)
            return;

        if(e instanceof EventTick) {
            if((MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof BlockItem
                    || MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof BlockItem)
                    && !isMainHandThrowable(throwables)) {
                MinecraftClient.getInstance().itemUseCooldown = 0;
            }
        }
    }

    private boolean isMainHandThrowable(List<Item> throwables) {
        if(throwables.isEmpty())
            return false;
        else {
            if(MinecraftClient.getInstance().player.getMainHandStack().getItem() == throwables.get(0)) return true;
            else isMainHandThrowable(throwables.subList(1, throwables.size()));
        }
        return false;
    }

}
