package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.enums.CommandType;
import ru.jurden.helper31bot.service.StepService;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordLengthCommand implements Command {

    private final StepService stepService;

    @Override
    public CommandType getCommandType() {
        return CommandType.PASSWORD_LENGTH;
    }

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        message.setText("Please enter length:");

        stepService.add(chatId);

        return message;
    }
}
