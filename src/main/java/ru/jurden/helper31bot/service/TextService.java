package ru.jurden.helper31bot.service;

import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.entity.UuidSettings;

public interface TextService {
    String getHelpText();
    String getUuidStatusText(UuidSettings settings);
    String getPasswordStatusText(PasswordSettings settings);
}
