package ru.jurden.helper31bot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.impl.HelpCommand;
import ru.jurden.helper31bot.commands.impl.PasswordLengthValueCommand;
import ru.jurden.helper31bot.service.StepService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandProcessor {

    private final StepService stepService;
    private final List<Command> commands;

    private final HelpCommand helpCommand;
    private final PasswordLengthValueCommand passwordLengthValueCommand;

    public Command getCommand(Update update) {
        Long chatId = update.getMessage().getChatId();

        if (!update.hasMessage()) {
            stepService.remove(chatId);
            return helpCommand;
        }

        if (update.getMessage().hasText()) {
            String text = update.getMessage().getText();

            for (Command command : commands) {
                if (command.getCommandType().getCommand().equals(text)) {
                    stepService.remove(chatId);
                    return command;
                }
            }
            if (stepService.check(chatId)) {
                return passwordLengthValueCommand;
            }
        }

        stepService.remove(chatId);
        return helpCommand;
    }
}
