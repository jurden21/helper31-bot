package ru.jurden.helper31bot.commands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class CommandFactory {

    private HelpCommand helpCommand;
    private PasswordCommand passwordCommand;

    public static final String COMMAND_PREFIX = "/";
    public static final String HELP_COMMAND = COMMAND_PREFIX + "help";
    public static final String PASSWORD_COMMAND = COMMAND_PREFIX + "password";

    public Command getCommand(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String[] arguments = messageText.split("\\s+");
            String command = arguments.length > 0 ? arguments[0] : "";
            String arg1 = arguments.length > 1 ? arguments[1] : "";

            if (PASSWORD_COMMAND.equals(command)) {


                return passwordCommand;
            }
        }

        return helpCommand;
    }

}
