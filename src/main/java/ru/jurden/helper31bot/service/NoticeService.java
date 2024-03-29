package ru.jurden.helper31bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.config.BotConfig;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class NoticeService {

    final BotConfig botConfig;

    public SendMessage createNotification(Update update) {
        SendMessage message = new SendMessage();
        String userName = update.getMessage().getChat().getUserName();
        message.setChatId(botConfig.getLogChatId());
        message.setText(
                String.format("%s (%s %s) [%d]: %s",
                        Objects.isNull(userName) ? userName : "@" + userName,
                        update.getMessage().getChat().getFirstName(),
                        update.getMessage().getChat().getLastName(),
                        update.getMessage().getChat().getId(),
                        update.getMessage().getText()
                ));
        return message;
    }

    public SendMessage createNotification(String text) {
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(botConfig.getLogChatId());
        message.setText(text);
        return message;
    }
}
