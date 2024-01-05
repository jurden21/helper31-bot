package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.commands.CommandState;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.repository.BotRepository;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordLengthCommand extends Command {

    private BotRepository botRepository;

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return PasswordSettings.DEFAULT_LENGTH;
        }
    }

    @Override
    public SendMessage execute(Update update) {

        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        if (CommandState.READY.equals(getState())) {
            message.setText("Please enter length:");
            state = CommandState.WAIT_ARG;
            return message;
        }

        int length = Integer.min(Integer.max(parseInt(update.getMessage().getText()),
                PasswordSettings.MIN_LENGTH), PasswordSettings.MAX_LENGTH);

        PasswordSettings settings = botRepository.getPasswordSettings(chatId).setLength(length);
        botRepository.savePasswordSettings(settings);
        message.setText(getPasswordStatus(settings));

        return message;
    }
}
