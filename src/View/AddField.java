package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class AddField extends StandardView {
    private JTextField idField;
    private JTextField numberField;
    private JTextField soilField;
    private JCheckBox lightsCheckBox;
    private JCheckBox lockerroomCheckBox;
    private JTextField priceField;
    private JTextField startTimeField;
    private JTextField endTimeField;

    public AddField() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected void setupWindow() {
        setTitle("Aggiungi Campo");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Aggiungi Campo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        idField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(idField, gbc);

        JLabel numberLabel = new JLabel("Number:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(numberLabel, gbc);

        numberField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(numberField, gbc);

        JLabel soilLabel = new JLabel("Soil:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(soilLabel, gbc);

        soilField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(soilField, gbc);

        JLabel lightsLabel = new JLabel("Lights:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lightsLabel, gbc);

        lightsCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(lightsCheckBox, gbc);

        JLabel lockerroomLabel = new JLabel("Lockerroom:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lockerroomLabel, gbc);

        lockerroomCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(lockerroomCheckBox, gbc);

        JLabel priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(priceLabel, gbc);

        priceField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(priceField, gbc);

        JLabel startTimeLabel = new JLabel("Start Time:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(startTimeLabel, gbc);

        startTimeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(startTimeField, gbc);

        JLabel endTimeLabel = new JLabel("End Time:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(endTimeLabel, gbc);

        endTimeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(endTimeField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton confirmButton = new JButton("Conferma");
        confirmButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            int number = Integer.parseInt(numberField.getText());
            String soil = soilField.getText();
            boolean lights = lightsCheckBox.isSelected();
            boolean lockerroom = lockerroomCheckBox.isSelected();
            int price = Integer.parseInt(priceField.getText());
            Time startTime = Time.valueOf(startTimeField.getText() + ":00");
            Time endTime = Time.valueOf(endTimeField.getText() + ":00");
            Engine.getInstance().addField(id, number, soil, lights, lockerroom, price, startTime, endTime);
        });
        confirmButton.setFocusable(false);
        buttonPanel.add(confirmButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            pageNavigationController.navigateToFieldsTable();
        });
        backButton.setFocusable(false);
        buttonPanel.add(backButton);

        return buttonPanel;
    }
}