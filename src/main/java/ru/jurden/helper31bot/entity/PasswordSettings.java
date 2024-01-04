package ru.jurden.helper31bot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class PasswordSettings {
    public static final List<Character> LOWER_CASE = List.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static final List<Character> UPPER_CASE = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    public static final List<Character> DIGITS = List.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    public static final List<Character> SPECIAL = List.of(
            '!', '"', '#', '$', '%', '&', '\'', '*', '+', ',', '-' ,'.' ,'/' ,':' ,';', '=', '?', '@', '^', '_', '`', '|', '~');
    public static final List<Character> BRACKETS = List.of(
            '(', ')', '{', '}', '[', ']', '<', '>');

    private long chatId;
    private int length = 12;
    private boolean useUpperCase = true;
    private boolean useLowerCase = true;
    private boolean useDigits = true;
    private boolean useSpecial = false;
    private boolean useBrackets = false;
    private String chars = "";

    public List<Character> getCharList() {
        List<Character> charList = new ArrayList<>();
        if (!chars.isEmpty()) {
            return chars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        }
        if (useLowerCase) {
            charList.addAll(LOWER_CASE);
        }
        if (useUpperCase) {
            charList.addAll(UPPER_CASE);
        }
        if (useDigits) {
            charList.addAll(DIGITS);
        }
        if (useSpecial) {
            charList.addAll(SPECIAL);
        }
        if (useBrackets) {
            charList.addAll(BRACKETS);
        }
        return charList;
    }

    public PasswordSettings toggleUseUpperCase() {
        useUpperCase = !useUpperCase;
        return this;
    }

    public PasswordSettings toggleUseLowerCase() {
        useLowerCase = !useLowerCase;
        return this;
    }

}
