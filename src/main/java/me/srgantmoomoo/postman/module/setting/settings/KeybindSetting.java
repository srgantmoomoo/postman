package me.srgantmoomoo.postman.module.setting.settings;

import com.lukflug.panelstudio.setting.IKeybindSetting;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.setting.Setting;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableTextContent;

public class KeybindSetting extends Setting implements IKeybindSetting {
    private int key;

    public KeybindSetting(int key) { // no parent.
        setName("keybind");
        this.key = key;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey (int key) {
        this.key = key;

        if(Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.saveSettings();
            } catch (Exception ignored) {}
        }
    }

    @Override
    public String getKeyName() {
        String translationKey= InputUtil.Type.KEYSYM.createFromCode(getKey()).getTranslationKey();
        String translation=new TranslatableTextContent(translationKey, null, TranslatableTextContent.EMPTY_ARGUMENTS).toString();
        if (!translation.equals(translationKey)) return translation;
        return InputUtil.Type.KEYSYM.createFromCode(getKey()).getLocalizedText().getString();
    }
}
