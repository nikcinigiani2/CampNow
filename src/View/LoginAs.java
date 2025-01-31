package View;

import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;

public class LoginAs extends StandardView {

    public LoginAs(){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("CAMPNOW", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;

        JPanel clubPanel = createClubPanel();
        gbc.gridx = 0;
        centerPanel.add(clubPanel, gbc);

        // Add vertical separator
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBackground(Color.BLACK);
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        centerPanel.add(separator, gbc);

        JPanel userPanel = createUserPanel();
        gbc.gridx = 2;
        gbc.weightx = 0.5;
        centerPanel.add(userPanel, gbc);

        return mainPanel;
    }

    private JPanel createClubPanel() {
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);
        JPanel clubPanel = new JPanel();
        clubPanel.setLayout(new BoxLayout(clubPanel, BoxLayout.Y_AXIS));
        JLabel clubLabel = new JLabel("CLUB", JLabel.CENTER);
        ButtonGroup buttonGroup = new ButtonGroup();
        JToggleButton clubLoginButton = createButton("Login", buttonGroup, pageNavigationController::navigateToClubLogin);
        JToggleButton clubRegisterButton = createButton("Register", buttonGroup, pageNavigationController::navigateToClubRegister );

        clubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        clubLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clubRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        clubPanel.add(Box.createVerticalGlue());
        clubPanel.add(clubLabel);
        clubPanel.add(Box.createVerticalStrut(10));
        clubPanel.add(clubLoginButton);
        clubPanel.add(Box.createVerticalStrut(10));
        clubPanel.add(clubRegisterButton);
        clubPanel.add(Box.createVerticalGlue());

        return clubPanel;
    }

    private JPanel createUserPanel() {
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        JLabel userLabel = new JLabel("USER", JLabel.CENTER);
        ButtonGroup buttonGroup = new ButtonGroup();
        JToggleButton userLoginButton = createButton("Login", buttonGroup, pageNavigationController::navigateToUserLogin );
        JToggleButton userRegisterButton = createButton("Register", buttonGroup, pageNavigationController::navigateToUserRegister);

        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        userPanel.add(Box.createVerticalGlue());
        userPanel.add(userLabel);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(userLoginButton);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(userRegisterButton);
        userPanel.add(Box.createVerticalGlue());

        return userPanel;
    }
}