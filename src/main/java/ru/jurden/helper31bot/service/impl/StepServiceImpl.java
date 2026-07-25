package ru.jurden.helper31bot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.jurden.helper31bot.service.StepService;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class StepServiceImpl implements StepService {

    private final Set<Long> stepIds = new HashSet<>();

    @Override
    public void add(Long chatId) {
        stepIds.add(chatId);
    }

    @Override
    public boolean check(Long chatId) {
        return stepIds.contains(chatId);
    }

    @Override
    public void remove(Long chatId) {
        stepIds.remove(chatId);
    }
}
