package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Aggiungi Campo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        Border margin = new EmptyBorder(0,100,0,100);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

        formPanel.setBorder(new CompoundBorder(margin, blackBorder));
        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 220, 20, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 15, 2, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Campo ID:"), gbc);

        gbc.gridx = 1;
        idField = new JTextField(20);
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Numero:"), gbc);

        gbc.gridx = 1;
        numberField = new JTextField(20);
        formPanel.add(numberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Tipo terreno:"), gbc);

        gbc.gridx = 1;
        soilField = new JTextField(20);
        formPanel.add(soilField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Luci:"), gbc);

        gbc.gridx = 1;
        lightsCheckBox = new JCheckBox();
        formPanel.add(lightsCheckBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Spogliatoi:"), gbc);

        gbc.gridx = 3;
        lockerroomCheckBox = new JCheckBox();
        formPanel.add(lockerroomCheckBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Prezzo:"), gbc);

        gbc.gridx = 3;
        priceField = new JTextField(20);
        formPanel.add(priceField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Orario apertura (hh:mm):"), gbc);

        gbc.gridx = 3;
        startTimeField = new JTextField(20);
        formPanel.add(startTimeField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Orario chiusura (hh:mm):"), gbc);

        gbc.gridx = 3;
        endTimeField = new JTextField(20);
        formPanel.add(endTimeField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton confirmButton = new JButton("Conferma");
        confirmButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int number = Integer.parseInt(numberField.getText());
                String soil = soilField.getText();
                boolean lights = lightsCheckBox.isSelected();
                boolean lockerroom = lockerroomCheckBox.isSelected();
                int price = Integer.parseInt(priceField.getText());
                Time startTime = Time.valueOf(startTimeField.getText() + ":00");
                Time endTime = Time.valueOf(endTimeField.getText() + ":00");
                boolean success = Engine.getInstance().addField(id, number, soil, lights, lockerroom, price, startTime, endTime);
                if (success) {
                    pageNavigationController.navigateToFieldsTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'aggiunta del campo", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID, Number, and Price", "Invalid Number Format", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid time in HH:MM format", "Invalid Time Format", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            pageNavigationController.navigateToFieldsTable();
        });
        backButton.setFocusable(false);
        buttonPanel.add(backButton);

        confirmButton.setFocusable(false);
        buttonPanel.add(confirmButton);


        return buttonPanel;
    }
}