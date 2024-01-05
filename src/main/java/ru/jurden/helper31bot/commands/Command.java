package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.entity.PasswordSettings;

@Getter
public abstract class Command {
    abstract public SendMessage execute(Update update);

    protected String getStatus(PasswordSettings settings) {
        return
                String.format("Length: %d (/password_length n)\n", settings.getLength()) +
                        String.format("UpperCase: %s (/password_uppercase)\n", BooleanUtils.toString(settings.isUseUpperCase(), "ON", "OFF")) +
                        String.format("LowerCase: %s (/password_lowercase)\n", BooleanUtils.toString(settings.isUseLowerCase(), "ON", "OFF")) +
                        String.format("Digits: %s (/password_digits)\n", BooleanUtils.toString(settings.isUseDigits(), "ON", "OFF")) +
                        String.format("Special: %s (/password_special)\n", BooleanUtils.toString(settings.isUseSpecial(), "ON", "OFF")) +
                        String.format("Brackets: %s (/password_brackets)\n", BooleanUtils.toString(settings.isUseBrackets(), "ON", "OFF")) +
                        String.format("Chars: %s (/password_chars)\n", settings.getChars());
    }

    protected String getHelp() {
        return
                "Useful tools. Use command /help for additional information.\n\n" +

                        "<b>UUID generator</b>\n" +
                        "/uuid - generate uuid\n" +
                        "/uuid_status - uuid generator status\n" +
                        "/uuid_hyphens - toggle using hyphens\n" +
                        "/uuid_uppercase - toggle using uppercase\n" +
                        "/uuid_braces - toggle using braces\n" +

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
