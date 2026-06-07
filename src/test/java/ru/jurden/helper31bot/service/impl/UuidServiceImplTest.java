package ru.jurden.helper31bot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.UuidService;

@ExtendWith(MockitoExtension.class)
class UuidServiceImplTest {

    @Mock
    BotRepository botRepository;

    UuidService uuidService;

    @BeforeEach
    void setUp() {
        uuidService = new UuidServiceImpl(botRepository);
    }

    @Nested
    class GetSettingsTests {

        @Test
        void getSettingsTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.getSettings(chatId);

            Assertions.assertFalse(settings.isUseHyphens());
            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseBraces());
        }
    }

    @Nested
    class UseHyphensTests {

        @Test
        void toggleUseHyphensOnTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.toggleUseHyphens(chatId);

            Assertions.assertTrue(settings.isUseHyphens());
            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseBraces());
        }

        @Test
        void toggleUseHyphensOffTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(true)
                    .setUseUpperCase(false)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.toggleUseHyphens(chatId);

            Assertions.assertFalse(settings.isUseHyphens());
            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseBraces());
        }
    }

    @Nested
    class UseUpperCaseTests {

        @Test
        void toggleUseUpperCaseOnTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.toggleUseUpperCase(chatId);

            Assertions.assertFalse(settings.isUseHyphens());
            Assertions.assertTrue(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseBraces());
        }

        @Test
        void toggleUseUpperCaseOffTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(true)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.toggleUseUpperCase(chatId);

            Assertions.assertFalse(settings.isUseHyphens());
            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseBraces());
        }
    }

    @Nested
    class UseBracesTests {

        @Test
        void toggleUseBracesOnTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.toggleUseBraces(chatId);

            Assertions.assertFalse(settings.isUseHyphens());
            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertTrue(settings.isUseBraces());
        }

        @Test
        void toggleUseBracesOffTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(true);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            settings = uuidService.toggleUseBraces(chatId);

            Assertions.assertFalse(settings.isUseHyphens());
            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseBraces());
        }
    }

    @Nested
    class GenerateUidTests {

        @Test
        void generateUuidUseHyphensUseUppercaseUseBracesTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(true)
                    .setUseUpperCase(true)
                    .setUseBraces(true);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            String uuid = uuidService.generateUuid(chatId);

            Assertions.assertNotNull(uuid);
            Assertions.assertTrue(uuid.contains("-"));
            Assertions.assertEquals(uuid.toUpperCase(), uuid);
            Assertions.assertEquals('{', uuid.charAt(0));
            Assertions.assertEquals('}', uuid.charAt(uuid.length() - 1));
        }

        @Test
        void generateUuidDontUseHyphensDontUseUppercaseUseBracesTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(false)
                    .setUseBraces(true);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            String uuid = uuidService.generateUuid(chatId);

            Assertions.assertNotNull(uuid);
            Assertions.assertFalse(uuid.contains("-"));
            Assertions.assertEquals(uuid.toLowerCase(), uuid);
            Assertions.assertEquals('{', uuid.charAt(0));
            Assertions.assertEquals('}', uuid.charAt(uuid.length() - 1));
        }

        @Test
        void generateUuidDontUseHyphensUseUppercaseDontUseBracesTest() {
            long chatId = 1L;
            UuidSettings settings = new UuidSettings()
                    .setChatId(chatId)
                    .setUseHyphens(false)
                    .setUseUpperCase(true)
                    .setUseBraces(false);

            Mockito.when(botRepository.getUuidSettings(chatId))
                    .thenReturn(settings);

            String uuid = uuidService.generateUuid(chatId);

            Assertions.assertNotNull(uuid);
            Assertions.assertFalse(uuid.contains("-"));
            Assertions.assertEquals(uuid.toUpperCase(), uuid);
            Assertions.assertNotEquals('{', uuid.charAt(0));
            Assertions.assertNotEquals('}', uuid.charAt(uuid.length() - 1));
        }
    }
}