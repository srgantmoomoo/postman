package me.srgantmoomoo.postman;

import com.lukflug.panelstudio.layout.ChildUtil.ChildMode;

import com.lukflug.panelstudio.base.*;
import com.lukflug.panelstudio.component.IResizable;
import com.lukflug.panelstudio.component.IScrollSize;
import com.lukflug.panelstudio.hud.HUDGUI;
import com.lukflug.panelstudio.layout.*;
import com.lukflug.panelstudio.mc20.MinecraftHUDGUI;
import com.lukflug.panelstudio.mc20.MinecraftGUI;
import com.lukflug.panelstudio.popup.*;
import com.lukflug.panelstudio.setting.*;
import com.lukflug.panelstudio.theme.*;
import com.lukflug.panelstudio.widget.*;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.modules.ClickGuiModule;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.function.*;

public class ClickGui extends MinecraftHUDGUI {
    private GUIInterface inter;
    private HUDGUI gui;
    private static final int WIDTH = 120, HEIGHT = 12, DISTANCE = 6, BORDER = 2;

    ClickGuiModule clickGuiModule;

    public ClickGui() {
        clickGuiModule = (ClickGuiModule) Main.INSTANCE.moduleManager.getModule("clickGui");
        IClient client = Category.getClient();
        inter = new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return "postman";
            }
        };

        ITheme theme=new OptimizedTheme(new RainbowTheme(new ThemeScheme(), ()->false,()->false,()->150,9,3,": "+Formatting.GRAY));

        IToggleable guiToggle=new SimpleToggleable(false);
        IToggleable hudToggle=new SimpleToggleable(false);

        gui=new HUDGUI(inter,theme.getDescriptionRenderer(),(IPopupPositioner)new MousePositioner(new Point(10,10)),guiToggle,hudToggle);

        Supplier<Animation> animation=()->new SettingsAnimation(() -> 5, inter::getTime);

        BiFunction<Context,Integer,Integer> scrollHeight=(context, componentHeight)->Math.min(componentHeight,Math.max(HEIGHT*4,ClickGui.this.height-context.getPos().y-HEIGHT));
        PopupTuple popupType=new PopupTuple(new PanelPositioner(new Point(0,0)),false,new IScrollSize() {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return scrollHeight.apply(context,componentHeight);
            }
        });

        IntFunction<IResizable> resizable= width->new IResizable() {
            Dimension size=new Dimension(width,320);

            @Override
            public Dimension getSize() {
                return new Dimension(size);
            }

            @Override
            public void setSize (Dimension size) {
                // no resizing
            }
        };

        Function<IResizable,IScrollSize> resizableHeight= size->new IScrollSize() {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return size.getSize().height;
            }
        };

        IntPredicate keybindKey= scancode->scancode== GLFW.GLFW_KEY_DELETE;
        IntPredicate charFilter=character->{
            return character>=' ';
        };
        ITextFieldKeys keys=new ITextFieldKeys() {
            @Override
            public boolean isBackspaceKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_BACKSPACE;
            }

            @Override
            public boolean isDeleteKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_DELETE;
            }

            @Override
            public boolean isInsertKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_INSERT;
            }

            @Override
            public boolean isLeftKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_LEFT;
            }

            @Override
            public boolean isRightKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_RIGHT;
            }

            @Override
            public boolean isHomeKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_HOME;
            }

            @Override
            public boolean isEndKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_END;
            }

            @Override
            public boolean isCopyKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_C;
            }

            @Override
            public boolean isPasteKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_V;
            }

            @Override
            public boolean isCutKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_X;
            }

            @Override
            public boolean isAllKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_A;
            }
        };

        // Normal generator
        IComponentGenerator generator=new ComponentGenerator(keybindKey,charFilter,keys);

        // Classic Panel
        IComponentAdder classicPanelAdder=new PanelAdder(gui,false,()->true, title->"classicPanel_"+title) {
            @Override
            protected IResizable getResizable (int width) {
                return resizable.apply(width);
            }

            @Override
            protected IScrollSize getScrollSize (IResizable size) {
                return resizableHeight.apply(size);
            }
        };
        ILayout classicPanelLayout=new PanelLayout(WIDTH,new Point(DISTANCE,DISTANCE),(WIDTH+DISTANCE)/2,HEIGHT+DISTANCE,animation, level->ChildMode.DOWN, level->ChildMode.DOWN,popupType);
        classicPanelLayout.populateGUI(classicPanelAdder,generator,client,theme);
    }

    @Override
    protected HUDGUI getGUI() {
        return gui;
    }

    @Override
    protected MinecraftGUI.GUIInterface getInterface() {
        return inter;
    }

    @Override
    protected int getScrollSpeed() {
        return 6;
    }

    private class ThemeScheme implements IColorScheme {
        private String camelCase(String name) {
            String firstLetter = name.substring(0, 1).toLowerCase();
            name = name.substring(1);
            name = firstLetter + name;
            name = name.replaceAll(" ", "");
            return name;
        }

        @Override
        public void createSetting (ITheme theme, String name, String description, boolean hasAlpha, boolean allowsRainbow, Color color, boolean rainbow) {
            clickGuiModule.addSettings(new ColorSetting(camelCase(name), clickGuiModule, color, rainbow));
        }

        @Override
        public Color getColor (String name) {
            return clickGuiModule.getSettings().filter(s -> s.getDisplayName().equals(camelCase(name))).filter(s -> s instanceof ColorSetting).map(s -> (ColorSetting) s).findFirst().orElse(null).getValue();
        }
    }

}
