package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Field;

import javax.swing.*;
import java.awt.*;

public class AddField extends StandardView {
    private JTextField numberField;
    private JCheckBox lightsCheckBox;
    private JCheckBox lockerroomCheckBox;
    private JTextField soilField;
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
        setTitle("Add New Field");
        setSize(900, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Aggiungi Nuovo Campo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel addFieldPanel = createAddFieldPanel();
        JPanel confirmButtonPanel = createButtonPanel();

        mainPanel.add(addFieldPanel, BorderLayout.CENTER);
        mainPanel.add(confirmButtonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createAddFieldPanel() {
        JPanel addFieldPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel numberLabel = new JLabel("Number:");
        JLabel soilLabel = new JLabel("Soil:");
        JLabel lightsLabel = new JLabel("Lights:");
        JLabel lockerroomLabel = new JLabel("Locker Room:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel startTimeLabel = new JLabel("Start Time:");
        JLabel endTimeLabel = new JLabel("End Time:");

        numberField = new JTextField(20);
        soilField = new JTextField(20);
        lightsCheckBox = new JCheckBox();
        lockerroomCheckBox = new JCheckBox();
        priceField = new JTextField(20);
        startTimeField = new JTextField(20);
        endTimeField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(numberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(numberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(soilLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(soilField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(lightsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(lightsCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(lockerroomLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(lockerroomCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(startTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(startTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(endTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        addFieldPanel.add(endTimeField, gbc);

        return addFieldPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            int number = Integer.parseInt(numberField.getText());
            String soil = soilField.getText();
            boolean lights = lightsCheckBox.isSelected();
            boolean lockerroom = lockerroomCheckBox.isSelected();
            int price = Integer.parseInt(priceField.getText());
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();

            Field newField = new Field(0, "clubId", number, soil, lights, lockerroom, price, startTime, endTime);
            Engine.getInstance().addField(newField);

            PageNavigation.getInstance(this).navigateToFieldsTable();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation.getInstance(this).navigateToFieldsTable();
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }
}