package org.cats.password;

public record PasswordOptions(
        int length,
        boolean useLetters,
        boolean useNumbers,
        boolean useSymbols
) {}
