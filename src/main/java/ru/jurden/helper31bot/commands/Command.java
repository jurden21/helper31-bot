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
                "/help - this help\n" +
                "/password - generate password\n" +
                "/password_status - password generator status\n" +
                "/password_length n - password generator status\n" +
                "/password_uppercase - toggle using uppercase in password generator\n" +
                "/password_lowercase - toggle using lowercase in password generator\n" +
                "/password_digits - toggle using digits in password generator\n" +
                "/password_special - toggle using special chars in password generator\n" +
                "/password_brackets - toggle using brackets in password generator\n" +
                "/password_chars chars - toggle using brackets in password generator\n";
    }

}
