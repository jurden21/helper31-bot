package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public abstract class Command {

    private final String name;
    private final String description;
    private final Update update;

    public Command(String name, String description, Update update) {
        this.name = name;
        this.description = description;
        this.update = update;
    }

    abstract public SendMessage execute();

}
