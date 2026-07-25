package ru.jurden.helper31bot.commands.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.enums.CommandType;
import ru.jurden.helper31bot.service.TextService;
import ru.jurden.helper31bot.service.UuidService;

@ExtendWith(MockitoExtension.class)
class UuidStatusCommandTest {

    @Mock
    UuidService uuidService;
    @Mock
    TextService textService;

    UuidStatusCommand uuidStatusCommand;

    @BeforeEach
    void setUp() {
        uuidStatusCommand = new UuidStatusCommand(uuidService, textService);
    }

    @Test
    void commandTypeTest() {
        CommandType commandType = uuidStatusCommand.getCommandType();

        Assertions.assertNotNull(commandType);
        Assertions.assertEquals(CommandType.UUID_STATUS, commandType);
    }

    @Test
    void executeTest() {
        Long chatId = 99L;
        UuidSettings uuidSettings = new UuidSettings();

        Chat chat = new Chat();
        chat.setId(chatId);
        Message message = new Message();
        message.setChat(chat);
        Update update = new Update();
        update.setMessage(message);

        Mockito.when(uuidService.getSettings(Mockito.eq(chatId)))
                .thenReturn(uuidSettings);
        Mockito.when(textService.getUuidStatusText(Mockito.eq(uuidSettings)))
                .thenReturn("uuid_status_command_generated_text");

        SendMessage sendMessage = uuidStatusCommand.execute(update);

        Assertions.assertNotNull(sendMessage);
        Assertions.assertEquals("uuid_status_command_generated_text", sendMessage.getText());
    }
}