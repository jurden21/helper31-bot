package ru.jurden.helper31bot.commands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.impl.*;

@Component
@AllArgsConstructor
public class CommandFactory {

    private final HelpCommand helpCommand;
    private final UuidCommand uuidCommand;
    private final UuidStatusCommand uuidStatusCommand;
    private final PasswordCommand passwordCommand;
    private final PasswordStatusCommand passwordStatusCommand;
    private final PasswordUpperCaseCommand passwordUpperCaseCommand;
    private final PasswordLowerCaseCommand passwordLowerCaseCommand;
    private final PasswordDigitsCommand passwordDigitsCommand;

    public static final String COMMAND_PREFIX = "/";
    public static final String GUID_COMMAND = COMMAND_PREFIX + "uuid";
    public static final String GUID_STATUS_COMMAND = COMMAND_PREFIX + "uuid_status";
    public static final String PASSWORD_COMMAND = COMMAND_PREFIX + "password";
    public static final String PASSWORD_STATUS_COMMAND = COMMAND_PREFIX + "password_status";
    public static final String PASSWORD_UPPERCASE_COMMAND = COMMAND_PREFIX + "password_uppercase";
    public static final String PASSWORD_LOWERCASE_COMMAND = COMMAND_PREFIX + "password_lowercase";
    public static final String PASSWORD_DIGITS_COMMAND = COMMAND_PREFIX + "password_digits";

    public Command getCommand(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();

            if (GUID_COMMAND.equals(command)) {
                return uuidCommand;
            }
            if (GUID_STATUS_COMMAND.equals(command)) {
                return uuidStatusCommand;
            }

            if (PASSWORD_COMMAND.equals(command)) {
                return passwordCommand;
            }
            if (PASSWORD_STATUS_COMMAND.equals(command)) {
                return passwordStatusCommand;
            }
            if (PASSWORD_UPPERCASE_COMMAND.equals(command)) {
                return passwordUpperCaseCommand;
            }
            if (PASSWORD_LOWERCASE_COMMAND.equals(command)) {
                return passwordLowerCaseCommand;
            }
            if (PASSWORD_DIGITS_COMMAND.equals(command)) {
                return passwordDigitsCommand;
            }
        }

        return helpCommand;
    }

}
