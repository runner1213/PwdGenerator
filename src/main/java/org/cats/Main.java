package org.cats;

import org.cats.password.PasswordGeneratorService;
import org.cats.password.PasswordValidator;
import org.cats.ui.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            // ui
            GeneratorFormPanel formPanel = new GeneratorFormPanel();
            GeneratorFrame frame = new GeneratorFrame(formPanel);

            // services
            PasswordValidator validator = new PasswordValidator();
            PasswordGeneratorService generator = new PasswordGeneratorService();
            PasswordDialog passwordDialog = new PasswordDialog(frame);

            new GeneratorController(
                    formPanel,
                    validator,
                    generator,
                    passwordDialog
            );
            frame.setVisible(true);
        });
    }
}
