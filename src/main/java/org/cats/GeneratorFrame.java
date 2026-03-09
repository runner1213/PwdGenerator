package org.cats;

import org.cats.password.PasswordGeneratorService;
import org.cats.password.PasswordOptions;
import org.cats.password.PasswordValidator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GeneratorFrame extends JFrame {

    private final JButton button = new JButton("Подтвердить");
    private final JLabel label = new JLabel("Генератор паролей");
    private final JTextField symbolsfield = new JTextField("20");
    private final JLabel symbolslabel = new JLabel("Введите количество символов");
    private final JCheckBox useSymbols = new JCheckBox("Использовать специальные символы?");
    private final JCheckBox useLetters = new JCheckBox("Использовать буквы?");
    private final JCheckBox useNumbers = new JCheckBox("Использовать числа?");

    private final JPanel rootPanel = new JPanel(new BorderLayout());
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private final JPanel centerPanel = new JPanel(new GridBagLayout());
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final PasswordGeneratorService passwordGeneratorService = new PasswordGeneratorService();

    public GeneratorFrame() {
        super("Генератор паролей");
        initFrame();
        initComponents();
        layoutComponents();
        bindActions();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(rootPanel);
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
        rootPanel.setBorder(new EmptyBorder(40, 100, 60, 100));

        titlePanel.setOpaque(false);
        titlePanel.add(label);
        rootPanel.add(titlePanel, BorderLayout.NORTH);

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

        rootPanel.add(centerPanel, BorderLayout.CENTER);

        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        buttonPanel.add(button);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void bindActions() {
        button.addActionListener(e -> onConfirm());
    }

    private void onConfirm() {
        int length;
        try {
            length = Integer.parseInt(symbolsfield.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Введите корректное число!",
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        PasswordOptions options = new PasswordOptions(
                length,
                useLetters.isSelected(),
                useNumbers.isSelected(),
                useSymbols.isSelected()
        );

        try {
            passwordValidator.validate(options);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Неверное количество символов!",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        button.setText("Генерация пароля...");

        try {
            String password = passwordGeneratorService.generate(options);
            showCustomDialog(password);
        } finally {
            button.setText("Подтвердить");
        }
    }

    private void showCustomDialog(String password) {
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
                this,
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
