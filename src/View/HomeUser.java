package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;

public class HomeUser extends StandardView{

    public HomeUser(){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected void setupWindow() {
        setTitle("CampNow");
        setSize(900, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel userLabel = new JLabel("Benvenuto, " + Engine.getInstance().getUser().getName(), JLabel.CENTER);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
        topPanel.add(userLabel, BorderLayout.EAST);
        return topPanel;
    }


    private JPanel createButtonPanel(){
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        //TODO cambia il navigateTo..
        JToggleButton newReservationButton = createButton("Nuova Prenotazione", buttonGroup, pageNavigationController::navigateToClubLogin);
        JToggleButton reservationButton = createButton("Prenotazioni", buttonGroup, pageNavigationController::navigateToClubRegister );

        newReservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newReservationButton.setPreferredSize(new Dimension(200, 60));
        reservationButton.setPreferredSize(new Dimension(200, 40));

        newReservationButton.setFocusable(false);
        reservationButton.setFocusable(false);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(newReservationButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(reservationButton);
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;

    }

}
