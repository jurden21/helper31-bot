package ru.jurden.helper31bot.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import ru.jurden.helper31bot.commands.CommandFactory;
import ru.jurden.helper31bot.config.BotConfig;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.service.NoticeService;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    public Helper31Bot(BotConfig botConfig, BotRepository botRepository, CommandFactory commandFactory, NoticeService noticeService) {
        this.botConfig = botConfig;
        this.botRepository = botRepository;
        this.commandFactory = commandFactory;
        this.noticeService = noticeService;
        List<BotCommand> list = new ArrayList<>();
        list.add(new BotCommand("/help", "help"));
        list.add(new BotCommand("/uuid", "generate uuid"));
        list.add(new BotCommand("/uuid_status", "uuid generator status"));
        list.add(new BotCommand("/password", "password generator"));
        list.add(new BotCommand("/password_status", "password generator status"));
        try {
            execute(new SetMyCommands(list, new BotCommandScopeDefault(), null));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            botRepository.saveRequest(update.getMessage());
            execute(noticeService.createNotification(update));
            execute(commandFactory.getCommand(update).execute(update));
        } catch (Exception e) {
            try {
                execute(noticeService.createNotification(
                        "<code>" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "</code>"));
            } catch (Exception s) {
                log.error("Exception:", s);
            }
        }

    }

}
