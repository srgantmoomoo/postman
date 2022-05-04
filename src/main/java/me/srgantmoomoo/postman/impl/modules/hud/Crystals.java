package me.srgantmoomoo.postman.impl.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.HudModule;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.awt.*;


public class Crystals extends HudModule {
    private final CrystalsCounterList list = new CrystalsCounterList();

    public ColorSetting color = new ColorSetting("color", this, new JColor(Reference.POSTMAN_COLOR, 255));
    public BooleanSetting sort = new BooleanSetting("sortRight", this, false);

    public Crystals() {
        super("crystals", "shows how many crystals u have in ur inventory.", new Point(75, 82), Category.HUD);
        this.addSettings(sort, color);
    }

    public void onRender() {
        list.crystals = mc.player.inventory.mainInventory.stream()
                .filter(itemStack -> itemStack.getItem() == Items.END_CRYSTAL)
                .mapToInt(ItemStack::getCount).sum();
        if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL)
            list.crystals++;
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, list);
    }

    private class CrystalsCounterList implements HUDList {
        public int crystals = 0;

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return "crystals " + crystals;
        }

        @Override
        public Color getItemColor(int index) {
            return color.getValue();
        }

        @Override
        public boolean sortUp() {
            return false;
        }

        @Override
        public boolean sortRight() {
            return sort.isEnabled();
        }
    }
}
