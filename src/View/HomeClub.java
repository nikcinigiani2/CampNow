package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;

public class HomeClub extends StandardView{

    public HomeClub(){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = createButtonPanel();
        centerPanel.add(buttonPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            Engine.getInstance().userLogout();
            PageNavigation pageNavigationController = PageNavigation.getInstance(this);
            pageNavigationController.navigateToLoginAs();
        });
        logoutButton.setFocusable(false);
        topPanel.add(logoutButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Home", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel clubLabel = new JLabel("Benvenuto, " + Engine.getInstance().getClub().getName(), JLabel.CENTER);
        clubLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        clubLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
        topPanel.add(clubLabel, BorderLayout.EAST);
        return topPanel;
    }


    private JPanel createButtonPanel(){
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        JToggleButton fieldsButton = createButton("I miei campi", buttonGroup, pageNavigationController::navigateToFieldsTable);

        JButton reservationButton = new JButton("Prenotazioni");
        reservationButton.addActionListener(e -> {
            pageNavigationController.navigateToReservationsTable(Engine.getInstance().getClub());
        });


        fieldsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldsButton.setFocusable(false);
        reservationButton.setFocusable(false);

        fieldsButton.setPreferredSize(new Dimension(200, 60));
        reservationButton.setPreferredSize(new Dimension(200, 60));

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(fieldsButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(reservationButton);
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;

    }

}
