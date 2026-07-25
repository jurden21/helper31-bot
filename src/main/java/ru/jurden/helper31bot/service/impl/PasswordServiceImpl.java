package ru.jurden.helper31bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.CharService;
import ru.jurden.helper31bot.service.PasswordService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 256;

    private final BotRepository botRepository;
    private final CharService charService;

    @Override
    public PasswordSettings getSettings(Long chatId) {
        PasswordSettings passwordSettings = botRepository.getPasswordSettings(chatId);

        log.info("getSettings for chatId={} to {}", chatId, passwordSettings);
        return passwordSettings;
    }

    @Override
    public PasswordSettings toggleUseUpperCase(Long chatId) {
        PasswordSettings passwordSettings = botRepository.getPasswordSettings(chatId);
        passwordSettings.setUseUpperCase(!passwordSettings.isUseUpperCase());
        botRepository.savePasswordSettings(passwordSettings);

        log.info("toggleUseUpperCase for chatId={} to {}", chatId, passwordSettings);
        return passwordSettings;
    }

    @Override
    public PasswordSettings toggleUseLowerCase(Long chatId) {
        PasswordSettings passwordSettings = botRepository.getPasswordSettings(chatId);
        passwordSettings.setUseLowerCase(!passwordSettings.isUseLowerCase());
        botRepository.savePasswordSettings(passwordSettings);

        log.info("toggleUseLowerCase for chatId={} to {}", chatId, passwordSettings);
        return passwordSettings;
    }

    @Override
    public PasswordSettings toggleUseDigits(Long chatId) {
        PasswordSettings passwordSettings = botRepository.getPasswordSettings(chatId);
        passwordSettings.setUseDigits(!passwordSettings.isUseDigits());
        botRepository.savePasswordSettings(passwordSettings);

        log.info("toggleUseDigits for chatId={} to {}", chatId, passwordSettings);
        return passwordSettings;
    }

    @Override
    public PasswordSettings toggleUseSpecials(Long chatId) {
        PasswordSettings passwordSettings = botRepository.getPasswordSettings(chatId);
        passwordSettings.setUseSpecials(!passwordSettings.isUseSpecials());
        botRepository.savePasswordSettings(passwordSettings);

        log.info("toggleUseSpecials for chatId={} to {}", chatId, passwordSettings);
        return passwordSettings;
    }

    @Override
    public PasswordSettings setLength(Long chatId, int length) {
        PasswordSettings passwordSettings = botRepository.getPasswordSettings(chatId);

        length = Math.min(Math.max(length, MIN_LENGTH), MAX_LENGTH);

        passwordSettings.setLength(length);
        botRepository.savePasswordSettings(passwordSettings);

        log.info("setLength for chatId={} to {}", chatId, passwordSettings);
        return passwordSettings;
    }

    @Override
    public String generatePassword(Long chatId) {
        PasswordSettings settings = botRepository.getPasswordSettings(chatId);
        List<Character> chars = charService.getChars(settings);
        Random random = new Random();

        if (CollectionUtils.isEmpty(chars)) {
            return "Please turn on any category of chars";
        }

        return Stream
                .iterate(1, n -> n + 1)
                .limit(settings.getLength())
                .map(n -> chars.get(random.nextInt(chars.size())).toString())
                .collect(Collectors.joining())
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
