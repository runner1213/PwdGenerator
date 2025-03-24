package org.cats.ActionListeners;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static org.cats.Main.*;

public class ButtonListener {
    public static void init() {
        button.addActionListener(e -> {
            boolean letters = useLetters.isSelected();
            boolean numbers = useNumbers.isSelected();
            int symbolsfieldText;
            try {
                symbolsfieldText = Integer.parseInt(symbolsfield.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Введите корректное число!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!letters && !numbers) {
                JOptionPane.showMessageDialog(frame, "Выберите хотя-бы один из чекбоксов", "Выберите хотя-бы один вариант!", JOptionPane.WARNING_MESSAGE);
            }
            else if (symbolsfieldText > 100) {
                JOptionPane.showMessageDialog(frame, "Введите количество символов меньше 100", "Неверное количество символов!", JOptionPane.WARNING_MESSAGE);
            }
            else if (symbolsfieldText <= 0) {
                JOptionPane.showMessageDialog(frame, "Введите количество символов больше нуля", "Неверное количество символов!", JOptionPane.WARNING_MESSAGE);
            }
            else {
                button.setText("Генерация пароля...");
                JOptionPane.showMessageDialog(frame, "Ваш пароль: " + generatedpwd(letters, numbers), "Пароль сгенерирован!", JOptionPane.INFORMATION_MESSAGE);
                button.setText("Подтвердить");
            }
        });
    }
    @NotNull
    private static String generatedpwd(boolean letters, boolean numbers) {
        int length = Integer.parseInt(symbolsfield.getText());
        return RandomStringUtils.random(length, letters, numbers);
    }
}
