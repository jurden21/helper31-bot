package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.repository.BotRepository;

@Slf4j
@Component
@AllArgsConstructor
public class UuidCommand extends Command {

    private BotRepository botRepository;

    public String generate(long chatId) {
        return botRepository.getUuidSettings(chatId).generate();
    }

    @Override
    public SendMessage execute(Update update) {
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.MARKDOWNV2);
        message.setChatId(update.getMessage().getChatId());
        message.setText("`" + generate(update.getMessage().getChatId()) + "`");
        return message;
    }
}
