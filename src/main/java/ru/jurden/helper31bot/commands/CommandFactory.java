package ru.jurden.helper31bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CommandFactory {
    public static final String COMMAND_PREFIX = "/";
    public static final String HELP_COMMAND = COMMAND_PREFIX + "help";
    public static final String PASSWORD_COMMAND = COMMAND_PREFIX + "password";
    public Command getCommand(Update update) {
        return new HelpCommand(update);
    }

}
