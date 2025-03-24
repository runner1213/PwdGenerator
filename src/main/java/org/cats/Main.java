package org.cats;

import org.cats.ActionListeners.ButtonListener;

import javax.swing.*;

public class Main {
    public static JButton button = new JButton("Подтвердить");
    public static JLabel label = new JLabel("Генератор паролей");
    public static JFrame frame = new JFrame("Генератор паролей");
    public static JTextField symbolsfield = new JTextField("20");
    public static JLabel symbolslabel = new JLabel("Введите количество символов");
    // public static JCheckBox useSymbols = new JCheckBox("Использовать символы?");
    public static JCheckBox useLetters = new JCheckBox("Использовать буквы?");
    public static JCheckBox useNumbers = new JCheckBox("Использовать числа?");

    public static void main(String[] args) {
        Elements.init();
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        ButtonListener.init();
        frame.setVisible(true);
    }
}
