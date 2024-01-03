package ru.jurden.helper31bot.commands.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.jurden.helper31bot.commands.Command;

@Slf4j
@Component
@AllArgsConstructor
public class HelpCommand extends Command {

    @Override
    public SendMessage execute(Update update) {
        Chat chat = update.getMessage().getChat();
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId());
        message.setParseMode(ParseMode.HTML);

        StringBuilder builder = new StringBuilder();
        builder.append("/help - this help\n");
        builder.append("/password - generate password\n");
        builder.append("/password_status - password generator status\n");
        builder.append("/password_uppercase - toggle using uppercase in password generator\n");

        message.setText(builder.toString());

        return message;
    }

}
