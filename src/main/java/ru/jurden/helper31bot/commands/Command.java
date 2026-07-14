package ru.jurden.helper31bot.commands;

import ru.jurden.helper31bot.enums.CommandState;
import ru.jurden.helper31bot.enums.CommandType;

public interface Command {
    CommandType getCommandType();
    CommandState getCommandState();
}
