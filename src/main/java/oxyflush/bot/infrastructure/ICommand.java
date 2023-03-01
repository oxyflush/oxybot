package oxyflush.bot.infrastructure;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface ICommand {
    void handle(MessageReceivedEvent event, String commandText);

    String getName();

    String getUsage();

    List<String> getAliases();
}
