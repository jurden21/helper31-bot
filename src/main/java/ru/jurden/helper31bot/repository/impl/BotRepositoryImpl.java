package ru.jurden.helper31bot.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.repository.query.BotQuery;

@Repository
@RequiredArgsConstructor
public class BotRepositoryImpl implements BotRepository {

    public final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveRequest(Message message) {
        jdbcTemplate.update(BotQuery.SAVE_REQUEST.query(),
                new MapSqlParameterSource()
                        .addValue("sender_id", message.getChat().getId())
                        .addValue("user_name", message.getChat().getUserName())
                        .addValue("last_name", message.getChat().getLastName())
                        .addValue("first_name", message.getChat().getFirstName())
                        .addValue("location", message.getChat().getLocation())
                        .addValue("command", message.getText()));
    }

}
