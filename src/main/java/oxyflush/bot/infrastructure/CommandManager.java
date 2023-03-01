package oxyflush.bot.infrastructure;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.Nullable;
import oxyflush.bot.commands.test.PingPongCommand;
import oxyflush.bot.main.Configuration;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager {
    private final Map<String, ICommand> aliases_to_commands = new HashMap<String, ICommand>();

    public CommandManager() {
        addCommand(new PingPongCommand());
    }

    private void addCommand(ICommand command) {
        Set<String> command_aliases = new HashSet<String>(command.getAliases());
        command_aliases.add(command.getName());

        for(String command_alias : command_aliases) {
            if(aliases_to_commands.containsKey(command_alias)) {
                throw new IllegalArgumentException("A command with this name is already present.");
            }
            else {
                aliases_to_commands.put(command_alias.toLowerCase(), command);
            }
        }
    }

    @Nullable
    private ICommand getCommand(String command_name) {
        if(!aliases_to_commands.containsKey(command_name.toLowerCase())) {
            return null;
        }

        return aliases_to_commands.get(command_name.toLowerCase());
    }

    void handle(MessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw().replaceFirst(
                "(?i)" + Pattern.quote(Configuration.COMMAND_PREFIX), ""
        ).split("\\s+", 2);

        String command_name = split[0].toLowerCase();
        ICommand command = this.getCommand(command_name);

        if (command != null) {
            command.handle(event, split[0]);
        }
    }
}
