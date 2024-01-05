package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.repository.BotRepository;

@Slf4j
@Component
@AllArgsConstructor
public class UuidHyphensCommand extends Command {

    private BotRepository botRepository;

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        UuidSettings settings = botRepository.getUuidSettings(chatId).toggleUseHyphens();
        botRepository.saveUuidSettings(settings);
        message.setText(getUuidStatus(settings));

        return message;
    }
}