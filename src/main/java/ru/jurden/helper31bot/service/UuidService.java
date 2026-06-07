package ru.jurden.helper31bot.service;

import ru.jurden.helper31bot.entity.UuidSettings;

public interface UuidService {
    UuidSettings getSettings(Long chatId);
    UuidSettings toggleUseHyphens(Long chatId);
    UuidSettings toggleUseUpperCase(Long chatId);
    UuidSettings toggleUseBraces(Long chatId);
    String generateUuid(Long chatId);
}
