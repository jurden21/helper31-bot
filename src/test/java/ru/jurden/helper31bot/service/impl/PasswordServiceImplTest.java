package ru.jurden.helper31bot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.CharService;
import ru.jurden.helper31bot.service.PasswordService;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class PasswordServiceImplTest {

    @Mock
    BotRepository botRepository;
    @Mock
    CharService charService;

    PasswordService passwordService;

    @BeforeEach
    void setUp() {
        passwordService = new PasswordServiceImpl(botRepository, charService);
    }

    @Nested
    class GetSettingsTests {

        @Test
        void getSettingsTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(true)
                    .setUseDigits(false)
                    .setUseSpecials(true)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.getSettings(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertTrue(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertTrue(settings.isUseSpecials());
        }
    }

    @Nested
    class UseUpperCaseTests {

        @Test
        void toggleUseUpperCaseOnTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseUpperCase(chatId);

            Assertions.assertTrue(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }

        @Test
        void toggleUseUpperCaseOffTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(true)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseUpperCase(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }
    }

    @Nested
    class UseLowerCaseTests {

        @Test
        void toggleUseLowerCaseOnTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseLowerCase(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertTrue(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }

        @Test
        void toggleUseLowerCaseOffTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(true)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseLowerCase(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }
    }

    @Nested
    class UseDigitsTests {

        @Test
        void toggleUseDigitsOnTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseDigits(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertTrue(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }

        @Test
        void toggleUseDigitsOffTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(true)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseDigits(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }
    }

    @Nested
    class UseSpecialsTests {

        @Test
        void toggleUseSpecialsOnTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseSpecials(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertTrue(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }

        @Test
        void toggleUseSpecialsOffTest() {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(true)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.toggleUseSpecials(chatId);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(20, settings.getLength());
        }
    }

    @Nested
    class LengthTests {

        private static Stream<Arguments> provideLengths() {
            return Stream.of(
                    Arguments.of(PasswordServiceImpl.MIN_LENGTH - 1, PasswordServiceImpl.MIN_LENGTH),
                    Arguments.of(PasswordServiceImpl.MIN_LENGTH, PasswordServiceImpl.MIN_LENGTH),
                    Arguments.of(PasswordServiceImpl.MIN_LENGTH + 1, PasswordServiceImpl.MIN_LENGTH + 1),
                    Arguments.of(PasswordServiceImpl.MAX_LENGTH - 1, PasswordServiceImpl.MAX_LENGTH - 1),
                    Arguments.of(PasswordServiceImpl.MAX_LENGTH, PasswordServiceImpl.MAX_LENGTH),
                    Arguments.of(PasswordServiceImpl.MAX_LENGTH + 1, PasswordServiceImpl.MAX_LENGTH)
            );
        }

        @ParameterizedTest
        @MethodSource("provideLengths")
        void setLengthTest(int a, int b) {
            long chatId = 1L;
            PasswordSettings settings = new PasswordSettings()
                    .setChatId(chatId)
                    .setUseUpperCase(false)
                    .setUseLowerCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false)
                    .setLength(20);

            Mockito.when(botRepository.getPasswordSettings(chatId))
                    .thenReturn(settings);

            settings = passwordService.setLength(chatId, a);

            Assertions.assertFalse(settings.isUseUpperCase());
            Assertions.assertFalse(settings.isUseLowerCase());
            Assertions.assertFalse(settings.isUseDigits());
            Assertions.assertFalse(settings.isUseSpecials());
            Assertions.assertEquals(b, settings.getLength());
        }
    }
}