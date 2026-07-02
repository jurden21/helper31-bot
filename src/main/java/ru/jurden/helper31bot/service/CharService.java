package ru.jurden.helper31bot.service;

import ru.jurden.helper31bot.entity.PasswordSettings;

import java.util.List;

public interface CharService {
    List<Character> getChars(PasswordSettings passwordSettings);
}
