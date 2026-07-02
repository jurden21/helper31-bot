package ru.jurden.helper31bot.commands;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.enums.CommandState;

@Getter
public abstract class BaseCommand {

    protected CommandState state = CommandState.READY;

    public abstract SendMessage execute(Update update);

    public void resetState() {
        state = CommandState.READY;
    }

    public boolean isWaiting() {
        return state != CommandState.READY;
    }
}
