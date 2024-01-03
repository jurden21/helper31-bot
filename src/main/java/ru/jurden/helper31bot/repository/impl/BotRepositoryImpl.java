package ru.jurden.helper31bot.repository.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.repository.BotRepository;
import ru.jurden.helper31bot.repository.query.BotQuery;

import java.sql.Types;

@Repository
@RequiredArgsConstructor
public class BotRepositoryImpl implements BotRepository {

    public final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveRequest(Message message) {
        jdbcTemplate.update(
                BotQuery.SAVE_REQUEST.query(),
                new MapSqlParameterSource()
                        .addValue("sender_id", message.getChat().getId(), Types.NUMERIC)
                        .addValue("user_name", message.getChat().getUserName(), Types.VARCHAR)
                        .addValue("last_name", message.getChat().getLastName(), Types.VARCHAR)
                        .addValue("first_name", message.getChat().getFirstName(), Types.VARCHAR)
                        .addValue("location", message.getChat().getLocation(), Types.VARCHAR)
                        .addValue("command", message.getText(), Types.VARCHAR)
        );
    }

    @Override
    public PasswordSettings getPasswordSettings(long chatId) {
        PasswordSettings passwordSettings = new PasswordSettings();
        jdbcTemplate.query(
                BotQuery.GET_PASSWORD_SETTINGS.query(),
                new MapSqlParameterSource()
                        .addValue("chat_id", chatId, Types.NUMERIC),
                (rs) -> {
                    passwordSettings
                            .setChatId(rs.getLong("chat_id"))
                            .setLength(rs.getInt("length"))
                            .setUseUpperCase(rs.getBoolean("use_upper_case"))
                            .setUseLowerCase(rs.getBoolean("use_lower_case"))
                            .setUseDigits(rs.getBoolean("use_digits"))
                            .setUseSpecial(rs.getBoolean("use_special"))
                            .setUseBrackets(rs.getBoolean("use_brackets"))
                            .setChars(rs.getString("chars"));
                });
        return passwordSettings;
    }

    @Override
    public void savePasswordSettings(PasswordSettings passwordSettings) {
        jdbcTemplate.update(
                BotQuery.SAVE_PASSWORD_SETTINGS.query(),
                new MapSqlParameterSource()
                        .addValue("chat_id", passwordSettings.getChatId(), Types.NUMERIC)
                        .addValue("length", passwordSettings.getLength(), Types.NUMERIC)
                        .addValue("use_upper_case", BooleanUtils.toInteger(passwordSettings.isUseUpperCase()), Types.NUMERIC)
                        .addValue("use_lower_case", BooleanUtils.toInteger(passwordSettings.isUseLowerCase()), Types.NUMERIC)
                        .addValue("use_digits", BooleanUtils.toInteger(passwordSettings.isUseDigits()), Types.NUMERIC)
                        .addValue("use_special", BooleanUtils.toInteger(passwordSettings.isUseSpecial()), Types.NUMERIC)
                        .addValue("use_brackets", BooleanUtils.toInteger(passwordSettings.isUseBrackets()), Types.NUMERIC)
                        .addValue("chars", passwordSettings.getChars(), Types.VARCHAR)
        );
    }
}
