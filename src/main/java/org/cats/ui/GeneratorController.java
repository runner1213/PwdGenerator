package org.cats.ui;

import org.cats.password.PasswordGeneratorService;
import org.cats.password.PasswordOptions;
import org.cats.password.PasswordValidator;

import javax.swing.*;
import java.awt.*;

public class GeneratorController {
    private final GeneratorFormPanel form;
    private final PasswordValidator validator;
    private final PasswordGeneratorService generator;
    private final PasswordDialog passwordDialog;

    public GeneratorController(
            GeneratorFormPanel form,
            PasswordValidator validator,
            PasswordGeneratorService generator,
            PasswordDialog passwordDialog
    ) {
        this.form = form;
        this.validator = validator;
        this.generator = generator;
        this.passwordDialog = passwordDialog;

        this.form.addGenerateListener(e -> onConfirm());
    }

    private void onConfirm() {
        int length;
        try {
            length = Integer.parseInt(form.getLengthText());
        } catch (NumberFormatException ex) {
            form.showErrorMessage("Введите корректное число", "Ошибка ввода");
            return;
        }

        PasswordOptions options = new PasswordOptions(
                length,
                form.isLettersSelected(),
                form.isNumbersSelected(),
                form.isSymbolsSelected()
        );

        try {
            validator.validate(options);
        } catch (IllegalArgumentException ex) {
            form.showWarningMessage(ex.getMessage(), "Неверное количество символов!");
            return;
        }

        form.setGenerateButtonText("Генерация пароля...");

        try {
            String password = generator.generate(options);
            passwordDialog.showPasswordDialog(password);
        } finally {
            form.setGenerateButtonText("Подтвердить");
        }
    }
}
