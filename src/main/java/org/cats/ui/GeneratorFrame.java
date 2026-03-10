package org.cats.ui;

import javax.swing.*;

public class GeneratorFrame extends JFrame {
    public GeneratorFrame(JPanel rootPanel) {
        super("Генератор паролей");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(rootPanel);
    }
}
