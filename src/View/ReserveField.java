package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Field;
import Model.Reservation;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReserveField extends StandardView{

    private int fieldId;
    private String clubId;
    private String clubName;
    private JComboBox<String> startTimeComboBox;
    private JComboBox<String> endTimeComboBox;
    private JTextField dateField;

    public ReserveField(int fieldId, String clubId, String clubName){
        this.fieldId = fieldId;
        this.clubId = clubId;
        this.clubName = clubName;
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("DETTAGLI CAMPO", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        Border margin = new EmptyBorder(0,100,0,100);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);


        JPanel nameFieldPanel = createNameFieldPanel();
        contentPanel.add(nameFieldPanel, BorderLayout.NORTH);

        JPanel detailPanel = createDetailPanel();
        contentPanel.add(detailPanel, BorderLayout.CENTER);

        contentPanel.setBorder(new CompoundBorder(margin, blackBorder));
        mainPanel.add(contentPanel, BorderLayout.CENTER);


        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createNameFieldPanel(){
        JPanel nameFieldPanel = new JPanel();

        JLabel titleFieldLabel = new JLabel("Club: "+clubName+" | Campo: "+fieldId);
        titleFieldLabel.setFont(new Font("Arial", Font.BOLD, 20));

        nameFieldPanel.add(titleFieldLabel, BorderLayout.NORTH);
        return nameFieldPanel;
    }

    private JPanel createDetailPanel(){
        JPanel detailPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        Field f = Engine.getInstance().getFieldByID(fieldId, clubId);
        JLabel name = new JLabel("Campo: "+fieldId);
        JLabel number = new JLabel("Numero: "+f.getNumber());
        JLabel soil = new JLabel ("Tipo terreno: "+f.getSoil());

        JLabel lights;
        if(f.isLights()){
             lights = new JLabel("Luci: si");
        }else{
             lights = new JLabel("Luci: no");
        }
        JLabel lockerRoom;
        if(f.isLockerroom()){
            lockerRoom = new JLabel("Spogliatoi: si");
        }else{
            lockerRoom = new JLabel("Spogliatoi: no");
        }
        JLabel price = new JLabel("Prezzo: "+f.getPrice());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        name.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        number.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(number, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        soil.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(soil, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        lights.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(lights, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        lockerRoom.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(lockerRoom, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        price.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(price, gbc);

        JLabel text = new JLabel("Imposta l'orario e la data della prenotazione");
        text.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3; // Subito dopo "Prezzo"
        gbc.gridwidth = 2; // Occupa due colonne per centrare il testo
        gbc.anchor = GridBagConstraints.CENTER;
        detailPanel.add(text, gbc);

        JLabel startLabel = new JLabel("Ora di Inizio:");
        JLabel endLabel = new JLabel("Ora di Fine:");
        startTimeComboBox = new JComboBox<>(generateTimeSlots(f.getStartTime(), f.getEndTime()));
        endTimeComboBox = new JComboBox<>(generateTimeSlots(f.getStartTime(), f.getEndTime()));

        gbc.gridx = 0;
        gbc.gridy = 4; // Dopo la nuova label
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        startLabel.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(startLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Assicura che la JComboBox sia visibile
        gbc.weightx = 1;
        startTimeComboBox.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(startTimeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        endLabel.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(endLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        endTimeComboBox.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(endTimeComboBox, gbc);

        JLabel date = new JLabel("Data (yyyy-mm-dd):");
        dateField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        date.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(date, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 50, 5, 50);
        dateField.setPreferredSize(new Dimension(150, 25));
        detailPanel.add(dateField, gbc);

        return detailPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton backButton = new JButton("Back");
        JButton reserveButton = new JButton("Prenota");
        backButton.addActionListener(e ->{
            pageNavigationController.navigateToSearchFields();
        });

        reserveButton.addActionListener(e ->{
            String selectedStartTime = (String) startTimeComboBox.getSelectedItem();
            String selectedEndTime = (String) endTimeComboBox.getSelectedItem();
            String selectedDate = dateField.getText();


            Time startTime =  Time.valueOf(selectedStartTime + ":00");
            Time endTime =  Time.valueOf(selectedEndTime + ":00");

            Date date;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = new Date(dateFormat.parse(selectedDate).getTime());
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Use yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(endTime.before(startTime) || startTime.equals(endTime)) {
                JOptionPane.showMessageDialog(this, "Inserire correttamente gli orari!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                if(Engine.getInstance().addReservation(clubId, fieldId, date, startTime, endTime)){
                    ArrayList<Reservation> reservations = Engine.getInstance().getUser().getReservations();
                    Engine.getInstance().getUser().loadReservations(reservations);
                    JOptionPane.showMessageDialog(this, "Prenotazione avvenuta con successo!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "Campo già prenotato in questi orari!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            pageNavigationController.navigateToUserHome();
        });

        backButton.setFocusable(false);
        reserveButton.setFocusable(false);

        buttonPanel.add(backButton);
        buttonPanel.add(reserveButton);

        return buttonPanel;
    }

    private String[] generateTimeSlots(Time startTime, Time endTime) {
        ArrayList<String> timeSlots = new ArrayList<>();

        long startMillis = startTime.getTime();
        long endMillis = endTime.getTime();
        long halfHourMillis = 30 * 60 * 1000;

        long currentMillis = startMillis;
        while (currentMillis <= endMillis) {
            timeSlots.add(new Time(currentMillis).toString().substring(0, 5)); // Formato HH:mm
            currentMillis += halfHourMillis;
        }

        return timeSlots.toArray(new String[0]);
    }
}
