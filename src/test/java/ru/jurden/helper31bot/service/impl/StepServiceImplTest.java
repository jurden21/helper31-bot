package ru.jurden.helper31bot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jurden.helper31bot.service.StepService;

@ExtendWith(MockitoExtension.class)
class StepServiceImplTest {

    StepService stepService;

    @BeforeEach
    void setUp() {
        stepService = new StepServiceImpl();
    }

    @Test
    void checkSuccessTest() {
        boolean check = stepService.check(1L);
        Assertions.assertFalse(check);

        stepService.add(1L);
        check = stepService.check(1L);
        Assertions.assertTrue(check);

        stepService.add(2L);
        check = stepService.check(1L);
        Assertions.assertTrue(check);

        stepService.remove(2L);
        check = stepService.check(1L);
        Assertions.assertTrue(check);

        stepService.remove(1L);
        check = stepService.check(1L);
        Assertions.assertFalse(check);
    }

    @Test
    void checkFailTest() {
        boolean check = stepService.check(1L);
        Assertions.assertFalse(check);

        stepService.add(2L);
        check = stepService.check(1L);
        Assertions.assertFalse(check);
    }
}