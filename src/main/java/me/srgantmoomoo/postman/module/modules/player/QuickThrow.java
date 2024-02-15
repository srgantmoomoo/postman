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

public class QuickThrow extends Module {

    public QuickThrow() {
        super("quickThrow", "throw items like potions or xp bottles quickly.", Category.PLAYER, 0);
        throwables.add(Items.EXPERIENCE_BOTTLE);
        throwables.add(Items.SPLASH_POTION);
        throwables.add(Items.LINGERING_POTION);
        throwables.add(Items.SNOWBALL);
        throwables.add(Items.EGG);
        throwables.add(Items.ENDER_PEARL);
        throwables.add(Items.ENDER_EYE);
    }

    private final List<Item> throwables = new ArrayList<>();

    /*
    there could be so many instances to check for with this... how do i check if a block can be placed or not??
    maybe the answer is to just check if something is already being thrown, if not then stop.
    in this case, as long as a throwable is held and the mainhand item isnt a block, the cooldown is zero.
     */
    @Override
    public void onEvent(Event e) {
        if(MinecraftClient.getInstance().player == null)
            return;

        if(e instanceof EventTick) {
            if(isHoldingThrowable(throwables) && !(MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof BlockItem))
                MinecraftClient.getInstance().itemUseCooldown = 0;
        }
    }

    private boolean isHoldingThrowable(List<Item> throwables) {
        if(throwables.isEmpty())
            return false;
        else {
            if(MinecraftClient.getInstance().player.isHolding(throwables.get(0))) return true;
            else isHoldingThrowable(throwables.subList(1, throwables.size()));
        }
        return false;
    }

}
