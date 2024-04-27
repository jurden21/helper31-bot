package ru.jurden.helper31bot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.jurden.helper31bot.commands.CommandFactory;
import ru.jurden.helper31bot.config.BotConfig;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.NoticeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public final class Helper31Bot extends TelegramLongPollingBot {

    final BotConfig botConfig;
    final BotRepository botRepository;
    final CommandFactory commandFactory;
    final NoticeService noticeService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    public Helper31Bot(BotConfig botConfig, BotRepository botRepository, CommandFactory commandFactory, NoticeService noticeService) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.botRepository = botRepository;
        this.commandFactory = commandFactory;
        this.noticeService = noticeService;
        List<BotCommand> list = new ArrayList<>();
        list.add(new BotCommand("/help", "help"));
        list.add(new BotCommand("/uuid", "generate uuid"));
        list.add(new BotCommand("/uuid_status", "show uuid status"));
        list.add(new BotCommand("/password", "generate password"));
        list.add(new BotCommand("/password_status", "show password status"));
        try {
            execute(new SetMyCommands(list, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error while setting up commands: {}", e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            botRepository.saveRequest(update.getMessage());
            execute(noticeService.createNotification(update));
            execute(commandFactory.getCommand(update).execute(update));
        } catch (Exception e) {
            log.error("Error while processing request", e);
            try {
                execute(noticeService.createNotification(e.getMessage(), e.getStackTrace()));
            } catch (Exception s) {
                log.error("Error while sending message about error", s);
            }
        }

    }

}
