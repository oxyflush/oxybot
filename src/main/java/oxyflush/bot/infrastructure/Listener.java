package oxyflush.bot.infrastructure;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import oxyflush.bot.main.Configuration;

public class Listener extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Don't respond to other bots or webhook messages.
        if (event.getAuthor().isBot() || !event.getChannel().asTextChannel().getName().equals("bot-lane") || event.isWebhookMessage()) {
            return;
        }

        String content = event.getMessage().getContentRaw();
        if(content.startsWith(Configuration.COMMAND_PREFIX)) {
            manager.handle(event);
        }
    }
}
