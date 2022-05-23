package me.srgantmoomoo.postman.impl.modules.hud;

import java.awt.Color;
import java.awt.Point;

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
import net.minecraft.tileentity.TileEntityChest;

public class Dubs extends HudModule {
    private DubsCounterList list = new DubCounterList();

    public ColorSetting color = new ColorSetting("color", this, new JColor(Reference.POSTMAN_COLOR, 255));
    public BooleanSetting sort = new BooleanSetting("sortRight", this, false);

    public Dubs() {
        super("dubs", "shows how many chests are nearby.", new Point(75, 82), Category.HUD);
        this.addSettings(sort, color);
    }

    public void onRender() {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);

        long chest = mc.world.loadedTileEntityList.stream()
                .filter(e -> e instanceof TileEntityChest).count();
    }

    @Override
    public void populate (Theme theme) {
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
            return "dubs " + chests;
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
