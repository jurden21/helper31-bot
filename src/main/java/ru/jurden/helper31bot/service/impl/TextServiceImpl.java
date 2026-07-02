package ru.jurden.helper31bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import ru.jurden.helper31bot.entity.UuidSettings;
import ru.jurden.helper31bot.service.TextService;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextServiceImpl implements TextService {

    public final static String REPO_URL = "https://github.com/jurden21/helper31-bot";
    public final static String OPTION_ON = "ON ";
    public final static String OPTION_OFF = "OFF";
    public final static String HELP_TEXT = """
                Useful tools. Use command /help for additional information.
                \s
                <b>UUID generator</b>
                /uuid - generate uuid
                /uuid_status - uuid generator status
                /uuid_hyphens - toggle using hyphens
                /uuid_uppercase - toggle using uppercase
                /uuid_braces - toggle using braces
                \s
                <b>Password Generator</b>
                /password - generate password
                /password_status - password generator status
                /password_length - password generator status
                /password_uppercase - toggle using uppercase
                /password_lowercase - toggle using lowercase
                /password_digits - toggle using digits
                /password_special - toggle using special chars
                \s
                %s
            """;
    public final static String UUID_STATUS_TEXT = """
                <b>UUID Generator Settings</b>
                <code>Hyphens:   %s</code>  (/uuid_hyphens)
                <code>UpperCase: %s</code>  (/uuid_uppercase)
                <code>Braces:    %s</code>  (/uuid_braces)
                Generate: /uuid
            """;


    @Override
    public String getHelpText() {
        return HELP_TEXT.formatted(REPO_URL);
    }

    @Override
    public String getUuidStatusText(UuidSettings settings) {
        return UUID_STATUS_TEXT.formatted(
                BooleanUtils.toString(settings.isUseHyphens(), OPTION_ON, OPTION_OFF),
                BooleanUtils.toString(settings.isUseUpperCase(), OPTION_ON, OPTION_OFF),
                BooleanUtils.toString(settings.isUseBraces(), OPTION_ON, OPTION_OFF));
    }
}
