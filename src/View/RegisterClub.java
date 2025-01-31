package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;

public class RegisterClub extends StandardView {
    private JTextField idField;
    private JTextField nameField;
    private JTextField cityField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField validatePasswordField;

    public RegisterClub() {
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

        JPanel addUserPanel = createAddClubPanel();
        JPanel signUpButtonPanel = createButtonPanel();

        mainPanel.add(addUserPanel, BorderLayout.CENTER);
        mainPanel.add(signUpButtonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createAddClubPanel() {
        JPanel addTablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Nome:");
        JLabel cityLabel = new JLabel("Citta':");
        JLabel addressLabel = new JLabel("Indirizzo:");
        JLabel phoneNumberLabel = new JLabel("Numero di telefono:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel validatePasswordLabel = new JLabel("Conferma Password:");

        idField = new JTextField(20);
        nameField = new JTextField(20);
        cityField = new JTextField(20);
        addressField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);
        validatePasswordField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(idField, gbc);

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
        addTablePanel.add(cityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        addTablePanel.add(phoneNumberField, gbc);

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
            String id = idField.getText();
            String name = nameField.getText();
            String city = cityField.getText();
            String address = addressField.getText();
            String phoneNumberText = phoneNumberField.getText();
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

            // Controllo se le password corrispondono
            if (!password.equals(validatePassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Verifica se i campi obbligatori sono compilati
            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phoneNumberText.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                // Prova a registrare l'utente
                if (Engine.getInstance().registerClub(id, name, city, address, phoneNumber, email, password)) {
                    JOptionPane.showMessageDialog(this, "Club registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    pageNavigationController.navigateToClubLogin();
                } else {
                    JOptionPane.showMessageDialog(this, "Club already exists", "Error", JOptionPane.ERROR_MESSAGE);
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