package ru.jurden.helper31bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.enums.CommandType;

public interface Command {
    CommandType getCommandType();
    SendMessage execute(Update update);
}
