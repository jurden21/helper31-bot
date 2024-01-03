package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public abstract class Command {
    abstract public SendMessage execute(Update update);
}
