package ru.jurden.helper31bot.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.impl.*;
import ru.jurden.helper31bot.enums.CommandType;
import ru.jurden.helper31bot.service.StepService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CommandProcessorTest {

    @Mock
    StepService stepService;
    @Mock
    HelpCommand helpCommand;
    @Mock
    UuidCommand uuidCommand;
    @Mock
    UuidStatusCommand uuidStatusCommand;
    @Mock
    UuidHyphensCommand uuidHyphensCommand;
    @Mock
    UuidUpperCaseCommand uuidUpperCaseCommand;
    @Mock
    UuidBracesCommand uuidBracesCommand;
    @Mock
    PasswordCommand passwordCommand;
    @Mock
    PasswordStatusCommand passwordStatusCommand;
    @Mock
    PasswordLengthCommand passwordLengthCommand;
    @Mock
    PasswordLengthValueCommand passwordLengthValueCommand;
    @Mock
    PasswordUpperCaseCommand passwordUpperCaseCommand;
    @Mock
    PasswordLowerCaseCommand passwordLowerCaseCommand;
    @Mock
    PasswordDigitsCommand passwordDigitsCommand;
    @Mock
    PasswordSpecialCommand passwordSpecialCommand;

    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        List<Command> commands = List.of(
                helpCommand,
                uuidCommand,
                uuidStatusCommand,
                uuidHyphensCommand,
                uuidUpperCaseCommand,
                uuidBracesCommand,
                passwordCommand,
                passwordStatusCommand,
                passwordLengthCommand,
                passwordLengthValueCommand,
                passwordUpperCaseCommand,
                passwordLowerCaseCommand,
                passwordDigitsCommand,
                passwordSpecialCommand
        );

//        Mockito.when(uuidStatusCommand.getCommandType())
//                .thenReturn(CommandType.UUID_STATUS);
//        Mockito.when(uuidHyphensCommand.getCommandType())
//                .thenReturn(CommandType.UUID_HYPHENS);
//        Mockito.when(uuidUpperCaseCommand.getCommandType())
//                .thenReturn(CommandType.UUID_UPPERCASE);
//        Mockito.when(uuidBracesCommand.getCommandType())
//                .thenReturn(CommandType.UUID_BRACES);
//        Mockito.when(passwordCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD);
//        Mockito.when(passwordStatusCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_STATUS);
//        Mockito.when(passwordLengthCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_LENGTH);
//        Mockito.when(passwordLengthValueCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_LENGTH_VALUE);
//        Mockito.when(passwordUpperCaseCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_UPPERCASE);
//        Mockito.when(passwordLowerCaseCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_LOWERCASE);
//        Mockito.when(passwordDigitsCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_DIGITS);
//        Mockito.when(passwordSpecialCommand.getCommandType())
//                .thenReturn(CommandType.PASSWORD_SPECIAL);

        commandProcessor = new CommandProcessor(
                stepService,
                commands,
                helpCommand,
                passwordLengthValueCommand
        );
    }

    @Test
    void uuidCommand() {
        String command = "/uuid";
        Update update = createUpdate(command);

        Mockito.when(helpCommand.getCommandType())
                .thenReturn(CommandType.HELP);
        Mockito.when(uuidCommand.getCommandType())
                .thenReturn(CommandType.UUID);

        Command actualResult = commandProcessor.getCommand(update);

        Assertions.assertEquals(uuidCommand, actualResult);
    }

    Update createUpdate(String text) {
        Chat chat = new Chat();
        chat.setId(99L);

        Message message = new Message();
        message.setChat(chat);
        message.setText(text);

        Update update = new Update();
        update.setMessage(message);

        return update;
    }
}