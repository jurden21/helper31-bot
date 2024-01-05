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
    private final UuidHyphensCommand uuidHyphensCommand;
    private final UuidUpperCaseCommand uuidUpperCaseCommand;
    private final UuidBracesCommand uuidBracesCommand;
    private final PasswordCommand passwordCommand;
    private final PasswordStatusCommand passwordStatusCommand;
    private final PasswordLengthCommand passwordLengthCommand;
    private final PasswordUpperCaseCommand passwordUpperCaseCommand;
    private final PasswordLowerCaseCommand passwordLowerCaseCommand;
    private final PasswordDigitsCommand passwordDigitsCommand;

    public static final String COMMAND_PREFIX = "/";
    public static final String UUID_COMMAND = COMMAND_PREFIX + "uuid";
    public static final String UUID_STATUS_COMMAND = COMMAND_PREFIX + "uuid_status";
    public static final String UUID_HYPHENS_COMMAND = COMMAND_PREFIX + "uuid_hyphens";
    public static final String UUID_UPPERCASE_COMMAND = COMMAND_PREFIX + "uuid_uppercase";
    public static final String UUID_BRACES_COMMAND = COMMAND_PREFIX + "uuid_braces";
    public static final String PASSWORD_COMMAND = COMMAND_PREFIX + "password";
    public static final String PASSWORD_STATUS_COMMAND = COMMAND_PREFIX + "password_status";
    public static final String PASSWORD_LENGTH_COMMAND = COMMAND_PREFIX + "password_length";
    public static final String PASSWORD_UPPERCASE_COMMAND = COMMAND_PREFIX + "password_uppercase";
    public static final String PASSWORD_LOWERCASE_COMMAND = COMMAND_PREFIX + "password_lowercase";
    public static final String PASSWORD_DIGITS_COMMAND = COMMAND_PREFIX + "password_digits";
    public static final String PASSWORD_SPECIAL_COMMAND = COMMAND_PREFIX + "password_special";

    public Command getCommand(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();

            if (UUID_COMMAND.equals(command)) {
                resetStates();
                return uuidCommand;
            }
            if (UUID_STATUS_COMMAND.equals(command)) {
                resetStates();
                return uuidStatusCommand;
            }
            if (UUID_HYPHENS_COMMAND.equals(command)) {
                resetStates();
                return uuidHyphensCommand;
            }
            if (UUID_UPPERCASE_COMMAND.equals(command)) {
                resetStates();
                return uuidUpperCaseCommand;
            }
            if (UUID_BRACES_COMMAND.equals(command)) {
                resetStates();
                return uuidBracesCommand;
            }

            if (PASSWORD_COMMAND.equals(command)) {
                resetStates();
                return passwordCommand;
            }
            if (PASSWORD_STATUS_COMMAND.equals(command)) {
                resetStates();
                return passwordStatusCommand;
            }
            if (PASSWORD_LENGTH_COMMAND.equals(command)) {
                resetStates();
                return passwordLengthCommand;
            }
            if (PASSWORD_UPPERCASE_COMMAND.equals(command)) {
                resetStates();
                return passwordUpperCaseCommand;
            }
            if (PASSWORD_LOWERCASE_COMMAND.equals(command)) {
                resetStates();
                return passwordLowerCaseCommand;
            }
            if (PASSWORD_DIGITS_COMMAND.equals(command)) {
                resetStates();
                return passwordDigitsCommand;
            }
            if (PASSWORD_SPECIAL_COMMAND.equals(command)) {
                resetStates();
                return null;
            }

            if (passwordLengthCommand.isWaiting()) {
                return passwordLengthCommand;
            }
        }

        resetStates();
        return helpCommand;
    }

    private void resetStates() {
        passwordLengthCommand.resetState();
    }

}
