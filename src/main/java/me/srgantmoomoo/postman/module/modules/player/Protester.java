package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Protester extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "clientShitter", "clientShitter", "postmanRespecter", "customMsg");
    public NumberSetting delay = new NumberSetting("delay", this, 20, 0, 100, 1);
    public static String customMsgArg = "";

    private List<String> clients = new ArrayList<>();
    private List<String> respects = new ArrayList<>();
    private Random random = new Random();
    private int tickDelay = 0;

    public Protester() {
        super("protester", "start your own protest!", Category.PLAYER, 0);
        this.addSettings(mode, delay);
    }

    @Override
    public void onEnable() {
        clients.clear();
        respects.clear();
        tickDelay = 0;

        clients.add("theres still people who use impact lol.");
        clients.add("lambda client is genuinley so ugly.");
        clients.add("lol r u really paying for future? what is wrong with you.");
        clients.add("rusherhack is lowk a terrible client.");
        clients.add("what kind of loser would ever use bleachhack.");
        clients.add("using meteor in the big 26? what are doing here guys.");

        respects.add("average nn client enjoyer: uncool... average postman respecter: very_cool");
        respects.add("wtf is that client???? switch to postman!!!");
        respects.add("mailman client is very good!");
        respects.add("psotman above all!");
        respects.add("i just got new postage from my local post office thanks to postman!");
        respects.add("guys, wtf is this discord.... its... its incredible 0_0 https://discord.gg/Jd8EmEuhb5");
        respects.add("postman on tipity top :)");
        respects.add("postman strong.");
        respects.add("postman stronk?");
    }

    @Override
    public void onDisable() {
        clients.clear();
        respects.clear();
    }

    @Override
    public void onEvent(Event e) {
        if(!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null) return;

        tickDelay++;
        if(tickDelay < delay.getValue() * 10) return;
        tickDelay = 0;

        String message = null;
        if(mode.is("clientShitter") && !clients.isEmpty())
            message = clients.get(random.nextInt(clients.size()));
        else if(mode.is("postmanRespecter") && !respects.isEmpty())
            message = respects.get(random.nextInt(respects.size()));
        else if(mode.is("customMsg") && !customMsgArg.isEmpty())
            message = customMsgArg;

        if(message != null)
            mc.player.networkHandler.sendChatMessage(message);
    }
}
