package ru.jurden.helper31bot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
}