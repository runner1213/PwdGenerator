package org.cats.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class PasswordDialog {
    private final Component parent;

    public PasswordDialog(Component parent) {
        this.parent = parent;
    }

    public void showPasswordDialog(String password) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel passwordLabel = new JLabel("Ваш пароль: " + password);
        panel.add(passwordLabel, BorderLayout.CENTER);

        JButton copyButton = new JButton("Скопировать");
        copyButton.addActionListener(e -> {
            copyToClipboard(password);
            copyButton.setText("Скопировано!");

            Timer timer = new Timer(3000, event -> copyButton.setText("Скопировать"));
            timer.setRepeats(false);
            timer.start();
        });

        Object[] options = {copyButton, "OK"};

        JOptionPane.showOptionDialog(
                parent,
                panel,
                "Пароль сгенерирован!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]
        );
    }

    private void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
}
