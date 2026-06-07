package ru.jurden.helper31bot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UuidSettings {

    private long chatId;
    private boolean useHyphens = true;
    private boolean useUpperCase = false;
    private boolean useBraces = true;
}
