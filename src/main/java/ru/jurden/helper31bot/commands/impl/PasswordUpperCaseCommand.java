package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.enums.CommandType;
import ru.jurden.helper31bot.service.PasswordService;
import ru.jurden.helper31bot.service.TextService;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordUpperCaseCommand implements Command {

    private final PasswordService passwordService;
    private final TextService textService;

    @Override
    public CommandType getCommandType() {
        return CommandType.PASSWORD_UPPERCASE;
    }

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        PasswordSettings passwordSettings = passwordService.toggleUseUpperCase(chatId);
        message.setText(textService.getPasswordStatusText(passwordSettings));

        return message;
    }
}
