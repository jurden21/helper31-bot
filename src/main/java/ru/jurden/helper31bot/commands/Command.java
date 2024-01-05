package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.entity.UuidSettings;

@Getter
public abstract class Command {
    abstract public SendMessage execute(Update update);

    protected String getUuidStatus(UuidSettings settings) {
        return
                "<b>UUID Generator Settings</b>\n" +
                        String.format("<code>Hyphens:   %s</code>  (/uuid_hyphens)\n", BooleanUtils.toString(settings.isUseHyphens(), "ON", "OFF")) +
                        String.format("<code>UpperCase: %s</code>  (/uuid_uppercase)\n", BooleanUtils.toString(settings.isUseUpperCase(), "ON", "OFF")) +
                        String.format("<code>Braces:    %s</code>  (/uuid_braces)\n", BooleanUtils.toString(settings.isUseBraces(), "ON", "OFF"));

    }

    protected String getPasswordStatus(PasswordSettings settings) {
        return
                "<b>Password Generator Settings</b>\n" +
                        String.format("<code>Length:    %d</code>  (/password_length)\n", settings.getLength()) +
                        String.format("<code>UpperCase: %s</code>  (/password_uppercase)\n", BooleanUtils.toString(settings.isUseUpperCase(), "ON", "OFF")) +
                        String.format("<code>LowerCase: %s</code>  (/password_lowercase)\n", BooleanUtils.toString(settings.isUseLowerCase(), "ON", "OFF")) +
                        String.format("<code>Digits:    %s</code>  (/password_digits)\n", BooleanUtils.toString(settings.isUseDigits(), "ON", "OFF")) +
                        String.format("<code>Special:   %s</code>  (/password_special)\n", BooleanUtils.toString(settings.isUseSpecial(), "ON", "OFF")) +
                        String.format("<code>Brackets:  %s</code>  (/password_brackets)\n", BooleanUtils.toString(settings.isUseBrackets(), "ON", "OFF")) +
                        String.format("<code>Chars:     %s</code>  (/password_chars)\n", settings.getChars());
    }

    protected String getHelp() {
        return
                "Useful tools. Use command /help for additional information.\n\n" +

                        "<b>UUID generator</b>\n" +
                        "/uuid - generate uuid\n" +
                        "/uuid_status - uuid generator status\n" +
                        "/uuid_hyphens - toggle using hyphens\n" +
                        "/uuid_uppercase - toggle using uppercase\n" +
                        "/uuid_braces - toggle using braces\n\n" +

                        "<b>Password Generator</b>\n" +
                        "/password - generate password\n" +
                        "/password_status - password generator status\n" +
                        "/password_length - password generator status\n" +
                        "/password_uppercase - toggle using uppercase\n" +
                        "/password_lowercase - toggle using lowercase\n" +
                        "/password_digits - toggle using digits\n" +
                        "/password_special - toggle using special chars\n" +
                        "/password_brackets - toggle using brackets\n" +
                        "/password_chars - TBA\n\n" +

                        "Bot owner: @unknown31bot";
    }

}
