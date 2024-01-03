package ru.jurden.helper31bot.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.jurden.helper31bot.commands.CommandFactory;
import ru.jurden.helper31bot.config.BotConfig;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.NoticeService;

@Slf4j
@Component
@RequiredArgsConstructor
public final class Bot extends TelegramLongPollingBot {

    final BotConfig botConfig;
    final BotRepository botRepository;
    final CommandFactory commandFactory;
    final NoticeService noticeService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            botRepository.saveRequest(update.getMessage());
            execute(noticeService.createNotification(update));
            execute(commandFactory.getCommand(update).execute(update));
        } catch (TelegramApiException e) {
            log.error("TelegramApiException:", e);
        } catch (Exception e) {
            log.error("Exception:", e);
        }

    }

}
