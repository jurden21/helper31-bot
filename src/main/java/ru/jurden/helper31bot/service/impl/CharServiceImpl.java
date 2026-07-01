package ru.jurden.helper31bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.jurden.helper31bot.entity.PasswordSettings;
import ru.jurden.helper31bot.service.CharService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharServiceImpl implements CharService {

    public static final List<Character> LOWER_CASE = List.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static final List<Character> UPPER_CASE = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    public static final List<Character> DIGITS = List.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    public static final List<Character> SPECIAL = List.of(
            '!', '"', '#', '$', '%', '&', '\'', '*', '+', ',', '-' ,'.' ,'/' ,':' ,';', '=', '?', '@', '^',
            '_', '`', '|', '~', '(', ')', '{', '}', '[', ']', '<', '>');

    @Override
    public List<Character> getCharList(PasswordSettings passwordSettings) {
        List<Character> charList = new ArrayList<>();

        if (passwordSettings.isUseLowerCase()) {
            charList.addAll(LOWER_CASE);
        }
        if (passwordSettings.isUseUpperCase()) {
            charList.addAll(UPPER_CASE);
        }
        if (passwordSettings.isUseDigits()) {
            charList.addAll(DIGITS);
        }
        if (passwordSettings.isUseSpecials()) {
            charList.addAll(SPECIAL);
        }
        return charList;
    }
}
