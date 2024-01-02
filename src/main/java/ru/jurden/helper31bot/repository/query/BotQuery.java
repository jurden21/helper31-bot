package ru.jurden.helper31bot.repository.query;

import ru.jurden.helper31bot.util.ResourceUtils;

public enum BotQuery {

    SAVE_REQUEST("/query/save_request.sql")
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
