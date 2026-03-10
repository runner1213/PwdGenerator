package org.cats.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GeneratorFormPanel extends JPanel {
    private final JButton button = new JButton("Подтвердить");
    private final JLabel label = new JLabel("Генератор паролей");
    private final JTextField symbolsfield = new JTextField("20");
    private final JLabel symbolslabel = new JLabel("Введите количество символов");
    private final JCheckBox useSymbols = new JCheckBox("Использовать специальные символы?");
    private final JCheckBox useLetters = new JCheckBox("Использовать буквы?");
    private final JCheckBox useNumbers = new JCheckBox("Использовать числа?");

    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private final JPanel centerPanel = new JPanel(new GridBagLayout());
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

    public GeneratorFormPanel() {
        super(new BorderLayout());
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        label.setFont(new Font("Arial", Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(500, 100));

        symbolsfield.setFont(new Font("Arial", Font.PLAIN, 15));
        symbolsfield.setHorizontalAlignment(JTextField.CENTER);
        symbolsfield.setPreferredSize(new Dimension(200, 25));
        symbolsfield.setMinimumSize(new Dimension(200, 25));

        symbolslabel.setFont(new Font("Arial", Font.PLAIN, 15));
        symbolslabel.setPreferredSize(new Dimension(350, 30));

        useNumbers.setFont(new Font("Arial", Font.PLAIN, 15));
        useNumbers.setPreferredSize(new Dimension(300, 30));

        useLetters.setFont(new Font("Arial", Font.PLAIN, 15));
        useLetters.setPreferredSize(new Dimension(300, 30));

        useSymbols.setFont(new Font("Arial", Font.PLAIN, 15));
        useSymbols.setPreferredSize(new Dimension(300, 30));

        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(380, 60));
        button.setMinimumSize(new Dimension(380, 60));
    }

    private void layoutComponents() {
        setBorder(new EmptyBorder(40, 100, 60, 100));

        titlePanel.setOpaque(false);
        titlePanel.add(label);
        add(titlePanel, BorderLayout.NORTH);

        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;

        // Строка: label + field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(55, 0, 25, 4);
        centerPanel.add(symbolslabel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(55, 0, 25, 0);
        centerPanel.add(symbolsfield, gbc);

        // Чекбоксы
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 12, 0);
        centerPanel.add(useNumbers, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 12, 0);
        centerPanel.add(useLetters, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 12, 0);
        centerPanel.add(useSymbols, gbc);

        // Растягивающийся пустой хвост
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(Box.createGlue(), gbc);

        add(centerPanel, BorderLayout.CENTER);

        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getLengthText() {
        return symbolsfield.getText();
    }

    public boolean isLettersSelected() {
        return useLetters.isSelected();
    }

    public boolean isNumbersSelected() {
        return useNumbers.isSelected();
    }

    public boolean isSymbolsSelected() {
        return useSymbols.isSelected();
    }

    public void addGenerateListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    public void setGenerateButtonText(String text) {
        button.setText(text);
    }

    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
