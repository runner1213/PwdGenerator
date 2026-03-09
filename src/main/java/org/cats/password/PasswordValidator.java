package org.cats.password;

public class PasswordValidator {

    public void validate(PasswordOptions options) {
        if (!options.useLetters() && !options.useNumbers() && !options.useSymbols()) {
            throw new IllegalArgumentException("Выберите хотя бы один из чекбоксов");
        }

        if (options.length() > 200) {
            throw new IllegalArgumentException("Введите количество символов < 200");
        }

        if (options.length() <= 0) {
            throw new IllegalArgumentException("Введите количество символов больше нуля");
        }
    }
}