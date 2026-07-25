package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.enums.CommandType;
import ru.jurden.helper31bot.service.TextService;
import ru.jurden.helper31bot.service.UuidService;

@Slf4j
@Component
@AllArgsConstructor
public class UuidBracesCommand implements Command {

    private final UuidService uuidService;
    private final TextService textService;

    @Override
    public CommandType getCommandType() {
        return CommandType.UUID_BRACES;
    }

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        UuidSettings uuidSettings = uuidService.toggleUseBraces(chatId);
        message.setText(textService.getUuidStatusText(uuidSettings));

        return message;
    }
}
