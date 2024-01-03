package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.repository.BotRepository;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordStatusCommand extends Command {

    private BotRepository botRepository;

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        PasswordSettings settings = botRepository.getPasswordSettings(chatId);
        settings.setChatId(chatId);
        message.setText(String.format(
                "Length: %d\nUpperCase: %s\nLowerCase: %s\nDigits: %s\nSpecial: %s\nBrackets: %s\nChars: %s",
                settings.getLength(),
                BooleanUtils.toString(settings.isUseUpperCase(), "on", "off"),
                BooleanUtils.toString(settings.isUseLowerCase(), "on", "off"),
                BooleanUtils.toString(settings.isUseDigits(), "on", "off"),
                BooleanUtils.toString(settings.isUseSpecial(), "on", "off"),
                BooleanUtils.toString(settings.isUseBrackets(), "on", "off"),
                settings.getChars()
        ));

        return message;
    }
}
