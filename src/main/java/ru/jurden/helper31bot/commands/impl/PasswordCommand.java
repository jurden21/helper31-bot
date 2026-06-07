package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.service.PasswordService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@AllArgsConstructor
public class PasswordCommand extends Command {

    private final PasswordService passwordService;

    public String generatePassword(long chatId) {
        PasswordSettings settings = passwordService.getSettings(chatId);
        List<Character> charList = settings.getCharList();
        if (CollectionUtils.isEmpty(charList)) {
            return "Please turn on any category of chars";
        }
        Random random = new Random();
        return Stream
                .iterate(1, n -> n + 1)
                .limit(settings.getLength())
                .map(n -> charList.get(random.nextInt(charList.size())).toString())
                .collect(Collectors.joining())
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);

        String text = String.format("<code>%s</code>", generatePassword(chatId));
        message.setText(text);

        return message;
    }
}
