package ru.jurden.helper31bot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
public class HelpCommand extends Command {

    public HelpCommand(Update update) {
        super("help", "Print help", update);
    }

    @Override
    public SendMessage execute() {
        Chat chat = getUpdate().getMessage().getChat();
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId());
        message.setParseMode(ParseMode.HTML);

        StringBuilder builder = new StringBuilder();
        builder.append("/help - this help\n");
        builder.append("/password - generate password\n");
        builder.append("/password help - generate password help\n");

        message.setText(builder.toString());

        return message;
    }

}
