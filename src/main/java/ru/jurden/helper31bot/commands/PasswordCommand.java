package ru.jurden.helper31bot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class PasswordCommand extends Command {

    public PasswordCommand(Update update) {
        super("password", "Generate password", update);
    }

    public String generatePassword(long chatId) {
        PasswordSettings passwordSettings = new PasswordSettings();
        List<Character> chars = passwordSettings.getChars();
        Random random = new Random();
        return Stream
                .iterate(1, n -> n + 1)
                .limit(passwordSettings.getLength())
                .map(n -> chars.get(random.nextInt(chars.size())).toString())
                .collect(Collectors.joining());
    }

    @Override
    public SendMessage execute() {
        SendMessage message = new SendMessage();
        message.setChatId(getUpdate().getMessage().getChatId());
        message.setText(generatePassword(getUpdate().getMessage().getChatId()));
        return message;
    }
}
