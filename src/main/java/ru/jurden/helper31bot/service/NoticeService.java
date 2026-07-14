package ru.jurden.helper31bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface NoticeService {
    SendMessage createActionNotification(Update update);
    SendMessage createActionNotification(String text, StackTraceElement[] stackTraceElements);
}
