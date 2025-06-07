package org.cats.ActionListeners;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
            else if (symbolsfieldText > 200) {
                JOptionPane.showMessageDialog(frame, "Введите количество символов < 200", "Неверное количество символов!", JOptionPane.WARNING_MESSAGE);
            }
            else if (symbolsfieldText <= 0) {
                JOptionPane.showMessageDialog(frame, "Введите количество символов больше нуля", "Неверное количество символов!", JOptionPane.WARNING_MESSAGE);
            }
            else {
                button.setText("Генерация пароля...");
                // JOptionPane.showMessageDialog(frame, "Ваш пароль: " + generatedpwd(letters, numbers), "Пароль сгенерирован!", JOptionPane.INFORMATION_MESSAGE);
                String password = generatedpwd(letters, numbers);
                showCustomDialog(password);
                button.setText("Подтвердить");
            }
        });
    }
    @NotNull
    private static String generatedpwd(boolean letters, boolean numbers) {
        int length = Integer.parseInt(symbolsfield.getText());
        return RandomStringUtils.random(length, letters, numbers);
    }

    private static void showCustomDialog(String password) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Ваш пароль: " + password);
        panel.add(label, BorderLayout.CENTER);

        JButton copyButton = new JButton("Скопировать");
        copyButton.addActionListener(e -> {
            copyToClipboard(password);
            copyButton.setText("Скопировано!");
            Timer timer = new Timer(3000, event -> copyButton.setText("Скопировать"));
            timer.setRepeats(false);
            timer.start();
        });
        Object[] options = {copyButton, "OK"};
        JOptionPane.showOptionDialog(frame, panel, "Пароль сгенерирован!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
    }

    private static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
}
