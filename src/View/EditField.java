package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Field;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;

public class EditField extends StandardView{

    private int fieldId;
    private String clubId;

    private JTextField numberField;
    private JTextField soilField;
    private JCheckBox lightsCheckBox;
    private JCheckBox lockerroomCheckBox;
    private JTextField priceField;
    private JTextField startTimeField;
    private JTextField endTimeField;

    public EditField(int fieldId){
        this.fieldId = fieldId;
        clubId = Engine.getInstance().getClub().getId();
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Modifica Campo", JLabel.CENTER);
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

    private JPanel createFormPanel(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 220, 20, 220));

        Field f = Engine.getInstance().getFieldByID(fieldId, clubId);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 15, 2, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Numero:"), gbc);

        gbc.gridx = 1;
        numberField = new JTextField(String.valueOf(f.getNumber()));
        formPanel.add(numberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Tipo terreno:"), gbc);

        gbc.gridx = 1;
        soilField = new JTextField(String.valueOf(f.getSoil()));
        formPanel.add(soilField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Luci:"), gbc);

        gbc.gridx = 1;
        lightsCheckBox = new JCheckBox("", f.isLights());
        formPanel.add(lightsCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Spogliatoi:"), gbc);

        gbc.gridx = 1;
        lockerroomCheckBox = new JCheckBox("", f.isLockerroom());
        formPanel.add(lockerroomCheckBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Prezzo:"), gbc);

        gbc.gridx = 3;
        priceField = new JTextField(String.valueOf(f.getPrice()));
        formPanel.add(priceField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Orario apertura (hh:mm):"), gbc);

        gbc.gridx = 3;
        String startTime = String.valueOf(f.getStartTime());
        String resultStart = startTime.substring(0, startTime.length() - 3);
        startTimeField = new JTextField(resultStart);
        formPanel.add(startTimeField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Orario chiusura (hh:mm):"), gbc);

        gbc.gridx = 3;
        String endTime = String.valueOf(f.getEndTime());
        String resultEnd = endTime.substring(0, endTime.length() - 3);
        endTimeField = new JTextField(resultEnd);
        formPanel.add(endTimeField, gbc);
        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton backButton = new JButton("Back");
        JButton editButton = new JButton("Conferma");
        JButton deleteButton = new JButton("Elimina Campo");
        backButton.addActionListener(e ->{
            pageNavigationController.navigateToFieldsTable();
        });

        editButton.addActionListener(e ->{
            int number = Integer.parseInt(numberField.getText());
            String soil = soilField.getText();
            boolean lights = lightsCheckBox.isSelected();
            boolean lockerroom = lockerroomCheckBox.isSelected();
            int price = Integer.parseInt(priceField.getText());
            Time startTime = Time.valueOf(startTimeField.getText() + ":00");
            Time endTime = Time.valueOf(endTimeField.getText() + ":00");


            if(Engine.getInstance().updateField(fieldId, number, soil, lights, lockerroom, price, startTime, endTime)){
                saveChanges(number, soil, lights, lockerroom, price, startTime, endTime);
                JOptionPane.showMessageDialog(this, "Modifica avvenuta con successo!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this, "Errore duranate la modifica del campo!", "Error", JOptionPane.ERROR_MESSAGE);

            pageNavigationController.navigateToClubHome();
        });

        deleteButton.addActionListener(e->{
            if(Engine.getInstance().deleteField(fieldId)){
                JOptionPane.showMessageDialog(this, "Campo eliminato con successo!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione della prenotazione!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pageNavigationController.navigateToFieldsTable();
        });

        backButton.setFocusable(false);
        editButton.setFocusable(false);
        deleteButton.setFocusable(false);

        buttonPanel.add(backButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        return buttonPanel;
    }

    private void saveChanges(int number, String soil, boolean lights, boolean lockerroom, int price, Time startTime, Time endTime){
        ArrayList<Field> fields = Engine.getInstance().getClub().getFields();

        for(Field field : fields){
            if(field.getId() == fieldId && clubId.equals(field.getClubid())){
                field.setNumber(number);
                field.setSoil(soil);
                field.setLights(lights);
                field.setLockerroom(lockerroom);
                field.setPrice(price);
                field.setStartTime(startTime);
                field.setEndTime(endTime);
            }
        }
        Engine.getInstance().getClub().loadFields(fields);
    }

}
