package ru.jurden.helper31bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.entity.UuidSettings;

public interface TextService {
    String getHelpText();
    String getUuidStatusText(UuidSettings settings);
    String getPasswordStatusText(PasswordSettings settings);
    String getActionNotificationText(Message message);
    String getErrorNotificationText(String error, StackTraceElement[] stackTrace);
    String getUuidText(String uuid);
}
