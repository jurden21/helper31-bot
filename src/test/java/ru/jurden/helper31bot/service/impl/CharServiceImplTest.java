package ru.jurden.helper31bot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.service.CharService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CharServiceImplTest {

    private final static Character LowerCaseSample = 'a';
    private final static Character UpperCaseSample = 'B';
    private final static Character DigitSample = '5';
    private final static Character SpecialSample = '%';

    CharService charService;

    @BeforeEach
    void setUp() {
        charService = new CharServiceImpl();
    }

    @Nested
    class GetCharsTests {

        @Test
        void getCharsLowerCasePresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(true)
                    .setUseUpperCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(26, chars.size());
            Assertions.assertTrue(chars.contains(LowerCaseSample));
            Assertions.assertFalse(chars.contains(UpperCaseSample));
            Assertions.assertFalse(chars.contains(DigitSample));
            Assertions.assertFalse(chars.contains(SpecialSample));
        }

        @Test
        void getCharsUpperCasePresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(false)
                    .setUseUpperCase(true)
                    .setUseDigits(false)
                    .setUseSpecials(false);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(26, chars.size());
            Assertions.assertFalse(chars.contains(LowerCaseSample));
            Assertions.assertTrue(chars.contains(UpperCaseSample));
            Assertions.assertFalse(chars.contains(DigitSample));
            Assertions.assertFalse(chars.contains(SpecialSample));
        }

        @Test
        void getCharsDigitsPresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(false)
                    .setUseUpperCase(false)
                    .setUseDigits(true)
                    .setUseSpecials(false);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(10, chars.size());
            Assertions.assertFalse(chars.contains(LowerCaseSample));
            Assertions.assertFalse(chars.contains(UpperCaseSample));
            Assertions.assertTrue(chars.contains(DigitSample));
            Assertions.assertFalse(chars.contains(SpecialSample));
        }

        @Test
        void getCharsSpecialsPresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(false)
                    .setUseUpperCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(true);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(31, chars.size());
            Assertions.assertFalse(chars.contains(LowerCaseSample));
            Assertions.assertFalse(chars.contains(UpperCaseSample));
            Assertions.assertFalse(chars.contains(DigitSample));
            Assertions.assertTrue(chars.contains(SpecialSample));
        }

        @Test
        void getCharsLowerCaseAndDigitsPresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(true)
                    .setUseUpperCase(false)
                    .setUseDigits(true)
                    .setUseSpecials(false);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(36, chars.size());
            Assertions.assertTrue(chars.contains(LowerCaseSample));
            Assertions.assertFalse(chars.contains(UpperCaseSample));
            Assertions.assertTrue(chars.contains(DigitSample));
            Assertions.assertFalse(chars.contains(SpecialSample));
        }

        @Test
        void getCharsUpperCaseAndSpecialsPresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(false)
                    .setUseUpperCase(true)
                    .setUseDigits(false)
                    .setUseSpecials(true);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(57, chars.size());
            Assertions.assertFalse(chars.contains(LowerCaseSample));
            Assertions.assertTrue(chars.contains(UpperCaseSample));
            Assertions.assertFalse(chars.contains(DigitSample));
            Assertions.assertTrue(chars.contains(SpecialSample));
        }

        @Test
        void getCharsAllPresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(true)
                    .setUseUpperCase(true)
                    .setUseDigits(true)
                    .setUseSpecials(true);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertFalse(chars.isEmpty());
            Assertions.assertEquals(93, chars.size());
            Assertions.assertTrue(chars.contains(LowerCaseSample));
            Assertions.assertTrue(chars.contains(UpperCaseSample));
            Assertions.assertTrue(chars.contains(DigitSample));
            Assertions.assertTrue(chars.contains(SpecialSample));
        }

        @Test
        void getCharsNoOnePresentTest() {
            PasswordSettings passwordSettings = new PasswordSettings()
                    .setUseLowerCase(false)
                    .setUseUpperCase(false)
                    .setUseDigits(false)
                    .setUseSpecials(false);

            List<Character> chars = charService.getChars(passwordSettings);

            Assertions.assertTrue(chars.isEmpty());
        }
    }
}