package oxyflush.bot.commands.test;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import oxyflush.bot.infrastructure.ICommand;

import java.util.List;

public class PingPongCommand implements ICommand {
    @Override
    public void handle(MessageReceivedEvent event, String commandText) {
        if (commandText.equals("ping")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessageFormat("Current bot ping is: %s", event.getJDA().getGatewayPing()).queue();
        }
        else if (commandText.equals("pong")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("U stupid").queue();
        }
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return List.of("pong");
    }
}
