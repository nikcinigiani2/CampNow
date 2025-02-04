package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterUser extends StandardView {
    private JTextField cfField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField phoneNumberField;
    private JTextField birthdateField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField validatePasswordField;

    public RegisterUser() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Sign Up", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel addUserPanel = createAddUserPanel();
        JPanel signUpButtonPanel = createButtonPanel();

        mainPanel.add(addUserPanel, BorderLayout.CENTER);
        mainPanel.add(signUpButtonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createAddUserPanel() {
        JPanel addTablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        JLabel cfLabel = new JLabel("Codice Fiscale:");
        JLabel nameLabel = new JLabel("Nome:");
        JLabel surnameLabel = new JLabel("Cognome:");
        JLabel phoneNumberLabel = new JLabel("Numero di telefono:");
        JLabel birthdateLabel = new JLabel("Data di nascita (yyyy-mm-dd):");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel validatePasswordLabel = new JLabel("Conferma Password:");

        cfField = new JTextField(20);
        nameField = new JTextField(20);
        surnameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        birthdateField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);
        validatePasswordField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(cfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(cfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(surnameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(surnameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(birthdateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(birthdateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(validatePasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(validatePasswordField, gbc);

        JPanel emptyPanel = new JPanel();  // Un pannello vuoto per bilanciare l'altezza
        addTablePanel.add(emptyPanel);

        return addTablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        ButtonGroup buttonGroup = new ButtonGroup();
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JToggleButton signUpButton = createButton("Sign Up", buttonGroup, () -> {
            String cf = cfField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            String phoneNumberText = phoneNumberField.getText();
            String birthdateText = birthdateField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getText());
            String validatePassword = new String(validatePasswordField.getText());


            int phoneNumber;
            try {
                phoneNumber = Integer.parseInt(phoneNumberText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date birthdate;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                birthdate = new Date(dateFormat.parse(birthdateText).getTime());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid birthdate format. Use yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Controllo se le password corrispondono
            if (!password.equals(validatePassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Verifica se i campi obbligatori sono compilati
            if (cf.isEmpty() || name.isEmpty() || surname.isEmpty() || phoneNumberText.isEmpty() || birthdateText.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (Engine.getInstance().registerUser(cf, name, surname, phoneNumber, birthdate, email, password)) {
                    JOptionPane.showMessageDialog(this, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    pageNavigationController.navigateToUserLogin();
                } else {
                    JOptionPane.showMessageDialog(this, "User already exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


        });

        signUpButton.setSelected(false);

        JToggleButton backButton = createButton("Back", buttonGroup, pageNavigationController::navigateToLoginAs);

        buttonPanel.add(backButton);
        buttonPanel.add(signUpButton);

        return buttonPanel;
    }
}
