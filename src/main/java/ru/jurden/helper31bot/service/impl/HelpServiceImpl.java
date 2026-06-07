package ru.jurden.helper31bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.jurden.helper31bot.service.HelpService;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService {

    public final static String REPO_URL = "https://github.com/jurden/helper31bot";

    @Override
    public String getHelp() {
        return """
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
                """
                .formatted(REPO_URL);
    }
}
