package ru.jurden.helper31bot.service;

public interface StepService {
    void add(Long chatId);
    boolean check(Long chatId);
    void remove(Long chatId);
}
