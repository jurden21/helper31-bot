package ru.jurden.helper31bot.repository.query;

import ru.jurden.helper31bot.util.ResourceUtils;

public enum BotQuery {

    SAVE_REQUEST("/query/save_request.sql"),
    GET_UUID_SETTINGS("/query/get_uuid_settings.sql"),
    SAVE_UUID_SETTINGS("/query/save_uuid_settings.sql"),
    GET_PASSWORD_SETTINGS("/query/get_password_settings.sql"),
    SAVE_PASSWORD_SETTINGS("/query/save_password_settings.sql"),
    ;

    private final String query;

    BotQuery(String path) {
        this.query = readQuery(path);
    }

    private String readQuery(String path) {
        return ResourceUtils.readToString(path);
    }

    public String query() {
        return query;
    }

}
