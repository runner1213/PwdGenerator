package org.cats.password;

import java.security.SecureRandom;

public class PasswordGeneratorService {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>?/";

    private final SecureRandom random = new SecureRandom();

    public String generate(PasswordOptions options) {
        StringBuilder pool = new StringBuilder();

        if (options.useLetters()) {
            pool.append(LETTERS);
        }
        if (options.useNumbers()) {
            pool.append(NUMBERS);
        }
        if (options.useSymbols()) {
            pool.append(SYMBOLS);
        }

        if (pool.isEmpty()) {
            throw new IllegalArgumentException("Невозможно сгенерировать пароль: пустой набор символов.");
        }

        String chars = pool.toString();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < options.length(); i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }

        return result.toString();
    }
}