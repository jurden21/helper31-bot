package ru.jurden.helper31bot.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.repository.BotRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordCommand extends Command {

    private final BotRepository botRepository;

    public String generatePassword(long chatId) {
        PasswordSettings passwordSettings = botRepository.loadPasswordSettings(chatId);
        List<Character> chars = passwordSettings.getChars();
        Random random = new Random();
        return Stream
                .iterate(1, n -> n + 1)
                .limit(passwordSettings.getLength())
                .map(n -> chars.get(random.nextInt(chars.size())).toString())
                .collect(Collectors.joining());
    }

    @Override
    public SendMessage execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(generatePassword(update.getMessage().getChatId()));
        return message;
    }
}
