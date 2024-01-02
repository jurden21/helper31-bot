package ru.jurden.helper31bot.repository;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotRepository {
    void saveRequest(Message message);
}
