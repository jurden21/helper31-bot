package ru.jurden.helper31bot.service;

import ru.jurden.helper31bot.entity.PasswordSettings;

public interface PasswordService {
    PasswordSettings getSettings(Long chatId);
    PasswordSettings toggleUseUpperCase(Long chatId);
    PasswordSettings toggleUseLowerCase(Long chatId);
    PasswordSettings toggleUseDigits(Long chatId);
    PasswordSettings toggleUseSpecials(Long chatId);
    PasswordSettings setLength(Long chatId, int length);
}
