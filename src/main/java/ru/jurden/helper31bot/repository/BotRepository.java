package ru.jurden.helper31bot.repository;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.jurden.helper31bot.commands.PasswordSettings;

public interface BotRepository {
    void saveRequest(Message message);
    PasswordSettings loadPasswordSettings(long chatId);
}
