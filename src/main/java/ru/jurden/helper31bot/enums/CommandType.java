package ru.jurden.helper31bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandType {
    HELP("/help"),
    UUID("/uuid"),
    UUID_STATUS("/uuid_status"),
    UUID_HYPHENS("/uuid_hyphens"),
    UUID_UPPERCASE("/uuid_uppercase"),
    UUID_BRACES("/uuid_braces"),
    PASSWORD("/password"),
    PASSWORD_STATUS("/password_status"),
    PASSWORD_LENGTH("/password_length"),
    PASSWORD_LENGTH_VALUE(""),
    PASSWORD_UPPERCASE("/password_uppercase"),
    PASSWORD_LOWERCASE("/password_lowercase"),
    PASSWORD_DIGITS("/password_digits"),
    PASSWORD_SPECIAL("/password_special"),
    ;

    private final String command;
}
