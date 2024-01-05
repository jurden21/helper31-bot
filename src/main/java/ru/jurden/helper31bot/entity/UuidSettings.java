package ru.jurden.helper31bot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class UuidSettings {

    private long chatId;
    private boolean useHyphens = true;
    private boolean useUpperCase = false;
    private boolean useBraces = true;

    public UuidSettings toggleUseHyphens() {
        useHyphens = !useHyphens;
        return this;
    }

    public UuidSettings toggleUseUpperCase() {
        useUpperCase = !useUpperCase;
        return this;
    }

    public UuidSettings toggleUseBraces() {
        useBraces = !useBraces;
        return this;
    }

    public String generate() {
        String uuid = UUID.randomUUID().toString();
        if (!useHyphens) {
            uuid = uuid.replaceAll("-", "");
        }
        if (useUpperCase) {
            uuid = uuid.toUpperCase();
        }
        if (useBraces) {
            uuid = String.format("{%s}", uuid);
        }
        return uuid;
    }

}
