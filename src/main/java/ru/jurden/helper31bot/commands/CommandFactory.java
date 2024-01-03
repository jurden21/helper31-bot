package ru.jurden.helper31bot.commands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.impl.HelpCommand;
import ru.jurden.helper31bot.commands.impl.PasswordCommand;
import ru.jurden.helper31bot.commands.impl.PasswordUpperCaseCommand;

@Component
@AllArgsConstructor
public class CommandFactory {

    private final HelpCommand helpCommand;
    private final PasswordCommand passwordCommand;
    private final PasswordUpperCaseCommand passwordUpperCaseCommand;

    public static final String COMMAND_PREFIX = "/";
    public static final String HELP_COMMAND = COMMAND_PREFIX + "help";
    public static final String PASSWORD_COMMAND = COMMAND_PREFIX + "password";
    public static final String PASSWORD_UPPERCASE_COMMAND = COMMAND_PREFIX + "password_uppercase";

    public Command getCommand(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String[] arguments = update.getMessage().getText().split("\\s+");
            String command = arguments.length > 0 ? arguments[0] : "";

            if (PASSWORD_COMMAND.equals(command)) {
                return passwordCommand;
            }
            if (PASSWORD_UPPERCASE_COMMAND.equals(command)) {
                return passwordUpperCaseCommand;
            }
        }

        return helpCommand;
    }

}
