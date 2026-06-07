package ru.jurden.helper31bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.UuidService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UuidServiceImpl implements UuidService {

    private final BotRepository botRepository;

    @Override
    public UuidSettings getSettings(Long chatId) {
        UuidSettings settings = botRepository.getUuidSettings(chatId);

        log.info("getSettings for chatId={} to {}", chatId, settings);
        return settings;
    }

    @Override
    public UuidSettings toggleUseHyphens(Long chatId) {
        UuidSettings settings = botRepository.getUuidSettings(chatId);
        settings.setUseHyphens(!settings.isUseHyphens());
        botRepository.saveUuidSettings(settings);

        log.info("toggleUseHyphens for chatId={} to {}", chatId, settings);
        return settings;
    }

    @Override
    public UuidSettings toggleUseUpperCase(Long chatId) {
        UuidSettings settings = botRepository.getUuidSettings(chatId);
        settings.setUseUpperCase(!settings.isUseUpperCase());
        botRepository.saveUuidSettings(settings);

        log.info("toggleUseUpperCase for chatId={} to {}", chatId, settings);
        return settings;
    }

    @Override
    public UuidSettings toggleUseBraces(Long chatId) {
        UuidSettings settings = botRepository.getUuidSettings(chatId);
        settings.setUseBraces(!settings.isUseBraces());
        botRepository.saveUuidSettings(settings);

        log.info("toggleUseBraces for chatId={} to {}", chatId, settings);
        return settings;
    }

    @Override
    public String generateUuid(Long chatId) {
        UuidSettings settings = botRepository.getUuidSettings(chatId);
        String uuid = UUID.randomUUID().toString();

        if (!settings.isUseHyphens()) {
            uuid = uuid.replace("-", "");
        }
        if (settings.isUseUpperCase()) {
            uuid = uuid.toUpperCase();
        }
        if (settings.isUseBraces()) {
            uuid = String.format("{%s}", uuid);
        }

        log.info("generateUuid for chatId={} uuid={} to {}", chatId, uuid, settings);
        return uuid;
    }
}
