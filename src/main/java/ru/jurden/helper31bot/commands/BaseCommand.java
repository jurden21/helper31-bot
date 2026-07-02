package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.enums.CommandState;

@Getter
public abstract class BaseCommand {

    protected CommandState state = CommandState.READY;

    public abstract SendMessage execute(Update update);

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
                        String.format("<code>Special:   %s</code>  (/password_special)%n", BooleanUtils.toString(settings.isUseSpecials(), "ON", "OFF")) +
                        String.format("%nGenerate: /password");
    }
}
