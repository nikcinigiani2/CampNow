package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;

public class LoginClub extends StandardView {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginClub(){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Titolo in alto
        JLabel titleLabel = new JLabel("CAMPNOW", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Pannello centrale con il form
        JPanel contentPanel = createLogClubPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Pannello dei bottoni in basso
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createLogClubPanel() {
        // Pannello del form principale
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Distanza tra i componenti

        // Etichetta e campo per l'email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        // Etichetta e campo per la password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Posizione dei componenti con GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(passwordField, gbc);

        return contentPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10)); // Distanza tra i bottoni

        ButtonGroup buttonGroup = new ButtonGroup();
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JToggleButton loginButton = createButton("Sign In", buttonGroup, () -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Engine.getInstance().loginClub(email, password)) {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                pageNavigationController.navigateToClubHome();
            }
        });

        JToggleButton backButton = createButton("Back", buttonGroup, pageNavigationController::navigateToLoginAs);

        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);

        return buttonPanel;
    }
}