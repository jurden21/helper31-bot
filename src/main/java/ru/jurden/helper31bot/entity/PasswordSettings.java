package ru.jurden.helper31bot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PasswordSettings {
    public static final int DEFAULT_LENGTH = 16;

    private long chatId;
    private int length = DEFAULT_LENGTH;
    private boolean useUpperCase = true;
    private boolean useLowerCase = true;
    private boolean useDigits = true;
    private boolean useSpecials = false;
}
