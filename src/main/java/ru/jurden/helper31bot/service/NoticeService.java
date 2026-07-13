package ru.jurden.helper31bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.config.BotConfig;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final BotConfig botConfig;
    private final TextService textService;

    public SendMessage createNotification(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(botConfig.getLogChatId());
        message.setText(textService.getActionNotificationText(update.getMessage()));
        return message;
    }

    public SendMessage createNotification(String text, StackTraceElement[] stackTraceElements) {
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(botConfig.getLogChatId());
        message.setText(String.format("<code>%s%n%s</code>", text, Arrays.toString(stackTraceElements)));
        return message;
    }
}
