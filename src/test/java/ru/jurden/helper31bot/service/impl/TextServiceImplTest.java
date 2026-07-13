package ru.jurden.helper31bot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.service.TextService;

@ExtendWith(SpringExtension.class)
class TextServiceImplTest {

    TextService textService;

    @BeforeEach
    void setUp() {
        textService = new TextServiceImpl();
    }

    @Nested
    class GetUuidStatusTextTests {

        @Test
        void getUuidStatusTextWithUseHyphens() {
            UuidSettings uuidSettings = new UuidSettings()
                    .setUseHyphens(true)
                    .setUseUpperCase(false)
                    .setUseBraces(false);

            String text = textService.getUuidStatusText(uuidSettings);

            Assertions.assertTrue(text.contains("Hyphens:   ON"));
            Assertions.assertTrue(text.contains("UpperCase: OFF"));
            Assertions.assertTrue(text.contains("Braces:    OFF"));
        }

        @Test
        void getUuidStatusTextWithUseUpperCase() {
            UuidSettings uuidSettings = new UuidSettings()
                    .setUseHyphens(false)
                    .setUseUpperCase(true)
                    .setUseBraces(false);

            String text = textService.getUuidStatusText(uuidSettings);

            Assertions.assertTrue(text.contains("Hyphens:   OFF"));
            Assertions.assertTrue(text.contains("UpperCase: ON"));
            Assertions.assertTrue(text.contains("Braces:    OFF"));
        }

        @Test
        void getUuidStatusTextWithUseBraces() {
            UuidSettings uuidSettings = new UuidSettings()
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(true);

            String text = textService.getUuidStatusText(uuidSettings);

            Assertions.assertTrue(text.contains("Hyphens:   OFF"));
            Assertions.assertTrue(text.contains("UpperCase: OFF"));
            Assertions.assertTrue(text.contains("Braces:    ON"));
        }
    }

    @Nested
    class GetPasswordStatusTextTests {

        @Test
        void getPasswordStatusTextWithUseUpperCase() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setLength(23)
                    .setUseLowerCase(true)
                    .setUseUpperCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false);

            String text = textService.getPasswordStatusText(passwordSettings);

            Assertions.assertTrue(text.contains("Length:    23"));
            Assertions.assertTrue(text.contains("LowerCase: ON"));
            Assertions.assertTrue(text.contains("UpperCase: OFF"));
            Assertions.assertTrue(text.contains("Digits:    OFF"));
            Assertions.assertTrue(text.contains("Special:   OFF"));
        }

        @Test
        void getPasswordStatusTextWithUseLowerCase() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setLength(23)
                    .setUseLowerCase(false)
                    .setUseUpperCase(true)
                    .setUseDigits(false)
                    .setUseSpecials(false);

            String text = textService.getPasswordStatusText(passwordSettings);

            Assertions.assertTrue(text.contains("Length:    23"));
            Assertions.assertTrue(text.contains("LowerCase: OFF"));
            Assertions.assertTrue(text.contains("UpperCase: ON"));
            Assertions.assertTrue(text.contains("Digits:    OFF"));
            Assertions.assertTrue(text.contains("Special:   OFF"));
        }

        @Test
        void getPasswordStatusTextWithUseDigits() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setLength(23)
                    .setUseLowerCase(false)
                    .setUseUpperCase(false)
                    .setUseDigits(true)
                    .setUseSpecials(false);

            String text = textService.getPasswordStatusText(passwordSettings);

            Assertions.assertTrue(text.contains("Length:    23"));
            Assertions.assertTrue(text.contains("LowerCase: OFF"));
            Assertions.assertTrue(text.contains("UpperCase: OFF"));
            Assertions.assertTrue(text.contains("Digits:    ON"));
            Assertions.assertTrue(text.contains("Special:   OFF"));
        }

        @Test
        void getPasswordStatusTextWithUseSpecials() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setLength(23)
                    .setUseLowerCase(false)
                    .setUseUpperCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(true);

            String text = textService.getPasswordStatusText(passwordSettings);

            Assertions.assertTrue(text.contains("Length:    23"));
            Assertions.assertTrue(text.contains("LowerCase: OFF"));
            Assertions.assertTrue(text.contains("UpperCase: OFF"));
            Assertions.assertTrue(text.contains("Digits:    OFF"));
            Assertions.assertTrue(text.contains("Special:   ON"));
        }
    }

    @Nested
    class GetActionNotificationTextTests {

        @Test
        void getActionNotificationTextInvalidMassage() {
            Message message = null;

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("Invalid message", text);
        }

        @Test
        void getActionNotificationTextInvalidMassageChat() {
            Message message = new Message();

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("Invalid message", text);
        }

        @Test
        void getActionNotificationTextMessageAllFields() {
            Chat chat = new Chat();
            chat.setUserName("username");
            chat.setFirstName("firstname");
            chat.setLastName("lastname");
            chat.setId(100L);

            Message message = new Message();
            message.setChat(chat);
            message.setText("text");

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("@username (firstname lastname) [100]: text", text);
        }

        @Test
        void getActionNotificationTextMessageWithUsername() {
            Chat chat = new Chat();
            chat.setUserName("username");

            Message message = new Message();
            message.setChat(chat);

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("@username (<no firstname> <no lastname>) [null]: null", text);
        }

        @Test
        void getActionNotificationTextMessageWithFirstName() {
            Chat chat = new Chat();
            chat.setFirstName("firstname");

            Message message = new Message();
            message.setChat(chat);

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("<no username> (firstname <no lastname>) [null]: null", text);
        }

        @Test
        void getActionNotificationTextMessageWithLastName() {
            Chat chat = new Chat();
            chat.setLastName("lastname");

            Message message = new Message();
            message.setChat(chat);

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("<no username> (<no firstname> lastname) [null]: null", text);
        }

        @Test
        void getActionNotificationTextMessageWithText() {
            Chat chat = new Chat();

            Message message = new Message();
            message.setChat(chat);
            message.setText("text");

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("<no username> (<no firstname> <no lastname>) [null]: text", text);
        }

        @Test
        void getActionNotificationTextMessageWithChatId() {
            Chat chat = new Chat();
            chat.setId(100L);

            Message message = new Message();
            message.setChat(chat);

            String text = textService.getActionNotificationText(message);

            Assertions.assertEquals("<no username> (<no firstname> <no lastname>) [100]: null", text);
        }
    }
}