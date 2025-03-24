package org.cats;

import javax.swing.*;
import java.awt.*;

import static org.cats.Main.*;

public class Elements {
    public static void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("Panel.background", new Color(30, 30, 30));
            UIManager.put("OptionPane.background", new Color(30, 30, 30));
            UIManager.put("OptionPane.messageForeground", Color.LIGHT_GRAY);
            UIManager.put("Button.background", new Color(60, 60, 60));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("CheckBox.background", new Color(30, 30, 30));
            UIManager.put("CheckBox.foreground", Color.WHITE);
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("TextField.background", new Color(50, 50, 50));
            UIManager.put("TextField.foreground", Color.WHITE);
            UIManager.put("TextField.caretForeground", Color.WHITE);
        } catch (Exception ignored) {}

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        symbolsfield.setBounds(360, 200, 200, 25);
        symbolsfield.setFont(new Font("Arial", Font.PLAIN, 15));
        symbolsfield.setHorizontalAlignment(JTextField.CENTER);

        symbolslabel.setBounds(100, 195, 350, 30);
        symbolslabel.setFont(new Font("Arial", Font.PLAIN, 15));

        useNumbers.setBounds(100, 250, 300, 30);
        useNumbers.setFont(new Font("Arial", Font.PLAIN, 15));

        useLetters.setBounds(100, 305, 300, 30);
        useLetters.setFont(new Font("Arial", Font.PLAIN, 15));

        label.setBounds(160, 40, 500, 100);
        label.setFont(new Font("Arial",Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        button.setBounds(210, 440, 380, 60);
        button.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(label);
        frame.add(symbolsfield);
        frame.add(button);
        frame.add(symbolslabel);
        frame.add(symbolsfield);
        frame.add(useNumbers);
        frame.add(useLetters);
        frame.add(useNumbers);

        SwingUtilities.updateComponentTreeUI(frame); // Применение UI хуйни из начала кода
    }
}
