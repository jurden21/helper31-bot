package ru.jurden.helper31bot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.config.BotConfig;

@Component
@RequiredArgsConstructor
public class CommandNotifier {

    final BotConfig botConfig;

    public SendMessage createNotification(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(botConfig.getLogChatId());
        message.setText(
                String.format("log: %s (%s %s) [%d]: %s",
                        update.getMessage().getChat().getUserName(),
                        update.getMessage().getChat().getFirstName(),
                        update.getMessage().getChat().getLastName(),
                        update.getMessage().getChat().getId(),
                        update.getMessage().getText()
                ));
        return message;
    }
}
