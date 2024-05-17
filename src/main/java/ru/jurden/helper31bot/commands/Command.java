package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.enums.CommandState;

@Getter
public abstract class Command {

    protected CommandState state = CommandState.READY;

    public abstract SendMessage execute(Update update);

    protected String getUuidStatus(UuidSettings settings) {
        return
                "<b>UUID Generator Settings</b>\n" +
                        String.format("<code>Hyphens:   %s</code>  (/uuid_hyphens)%n", BooleanUtils.toString(settings.isUseHyphens(), "ON", "OFF")) +
                        String.format("<code>UpperCase: %s</code>  (/uuid_uppercase)%n", BooleanUtils.toString(settings.isUseUpperCase(), "ON", "OFF")) +
                        String.format("<code>Braces:    %s</code>  (/uuid_braces)%n", BooleanUtils.toString(settings.isUseBraces(), "ON", "OFF")) +
                        String.format("%nGenerate: /uuid");
    }

    public void resetState() {
        state = CommandState.READY;
    }

    public boolean isWaiting() {
        return state != CommandState.READY;
    }

    protected String getPasswordStatus(PasswordSettings settings) {
        return
                "<b>Password Generator Settings</b>\n" +
                        String.format("<code>Length:    %d</code>  (/password_length)%n", settings.getLength()) +
                        String.format("<code>UpperCase: %s</code>  (/password_uppercase)%n", BooleanUtils.toString(settings.isUseUpperCase(), "ON", "OFF")) +
                        String.format("<code>LowerCase: %s</code>  (/password_lowercase)%n", BooleanUtils.toString(settings.isUseLowerCase(), "ON", "OFF")) +
                        String.format("<code>Digits:    %s</code>  (/password_digits)%n", BooleanUtils.toString(settings.isUseDigits(), "ON", "OFF")) +
                        String.format("<code>Special:   %s</code>  (/password_special)%n", BooleanUtils.toString(settings.isUseSpecial(), "ON", "OFF")) +
                        String.format("%nGenerate: /password");
    }

    protected String getHelp() {
        return """
                 Useful tools. Use command /help for additional information.
                 \s
                 <b>UUID generator</b>
                 /uuid - generate uuid
                 /uuid_status - uuid generator status
                 /uuid_hyphens - toggle using hyphens
                 /uuid_uppercase - toggle using uppercase
                 /uuid_braces - toggle using braces
                 \s
                 <b>Password Generator</b>
                 /password - generate password
                 /password_status - password generator status
                 /password_length - password generator status
                 /password_uppercase - toggle using uppercase
                 /password_lowercase - toggle using lowercase
                 /password_digits - toggle using digits
                 /password_special - toggle using special chars
                 \s
                 Bot owner: @yrdn71
                """;
    }

}
