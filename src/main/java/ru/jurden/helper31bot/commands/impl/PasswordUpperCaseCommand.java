package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageAutoDeleteTimerChanged;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.repository.BotRepository;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordUpperCaseCommand extends Command {

    private BotRepository botRepository;

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        PasswordSettings settings = botRepository.getPasswordSettings(chatId);
        settings.setChatId(chatId);
        settings.setUseUpperCase(!settings.isUseUpperCase());
        botRepository.savePasswordSettings(settings);
        message.setText(String.format("UpperCase is toggled to %s", BooleanUtils.toString(settings.isUseUpperCase(), "on", "off")));

        return message;
    }
}
