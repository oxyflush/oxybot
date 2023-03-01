package oxyflush.bot.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import oxyflush.bot.infrastructure.Listener;

public class Oxybot {

    public static void main(String[] args) {
        JDA api = JDABuilder.createDefault(Configuration.BOT_TOKEN)
                .addEventListeners(new Listener())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }
}
