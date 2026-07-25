package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.enums.CommandType;
import ru.jurden.helper31bot.service.PasswordService;
import ru.jurden.helper31bot.service.StepService;
import ru.jurden.helper31bot.service.TextService;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordLengthValueCommand implements Command {

    private final PasswordService passwordService;
    private final TextService textService;
    private final StepService stepService;

    @Override
    public CommandType getCommandType() {
        return CommandType.PASSWORD_LENGTH_VALUE;
    }

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        int length = NumberUtils.toInt(update.getMessage().getText(), PasswordSettings.DEFAULT_LENGTH);
        PasswordSettings passwordSettings = passwordService.setLength(chatId, length);
        message.setText(textService.getPasswordStatusText(passwordSettings));

        stepService.remove(chatId);

        return message;
    }
}
