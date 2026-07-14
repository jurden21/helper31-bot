package ru.jurden.helper31bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.config.BotConfig;
import ru.jurden.helper31bot.service.NoticeService;
import ru.jurden.helper31bot.service.TextService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final BotConfig botConfig;
    private final TextService textService;

    @Override
    public SendMessage createActionNotification(Update update) {
        String text = textService.getActionNotificationText(update.getMessage());

        SendMessage message = createMessage();
        message.setText(text);
        return message;
    }

    @Override
    public SendMessage createActionNotification(String error, StackTraceElement[] stackTrace) {
        String text = textService.getErrorNotificationText(error, stackTrace);

        SendMessage message = createMessage();
        message.setParseMode(ParseMode.HTML);
        message.setText(text);
        return message;
    }

    private SendMessage createMessage() {
        SendMessage message = new SendMessage();
        message.setChatId(botConfig.getLogChatId());
        return message;
    }
}
