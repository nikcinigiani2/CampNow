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

public class EditReservation extends StandardView{

    private int reservationId;
    private JTextField dateField;
    private JComboBox<String> startTimeComboBox;
    private JComboBox<String> endTimeComboBox;

    public EditReservation(int reservationId){
        this.reservationId = reservationId;
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Modifica Prenotazione", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        Border margin = new EmptyBorder(0,100,0,100);
        Border backBorder = BorderFactory.createLineBorder(Color.BLACK);

        JPanel detailPanel = createDetailPanel();
        contentPanel.add(detailPanel, BorderLayout.NORTH);

        contentPanel.setBorder(new CompoundBorder(margin, backBorder));
        mainPanel.add(contentPanel, BorderLayout.CENTER);


        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createDetailPanel(){
        JPanel detailPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        Reservation r = Engine.getInstance().getReservationByID(reservationId);
        Field f = Engine.getInstance().getFieldByID(r.getFieldId(), r.getClubid());

        JLabel date = new JLabel("Data (yyyy:mm:dd):");
        dateField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        date.setBorder(new EmptyBorder(50, 50, 0, 50));
        detailPanel.add(date, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(50, 50, 0, 50); // Aggiunge margini coerenti
        detailPanel.add(dateField, gbc);

        JLabel startLabel = new JLabel("Ora di Inizio:");
        JLabel endLabel = new JLabel("Ora di Fine:");
        startTimeComboBox = new JComboBox<>(generateTimeSlots(f.getStartTime(), f.getEndTime()));
        endTimeComboBox = new JComboBox<>(generateTimeSlots(f.getStartTime(), f.getEndTime()));

        gbc.gridx = 0;
        gbc.gridy = 1; // Dopo la nuova label
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        startLabel.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(startLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Assicura che la JComboBox sia visibile
        gbc.weightx = 1;
        startTimeComboBox.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(startTimeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        endLabel.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(endLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        endTimeComboBox.setBorder(new EmptyBorder(0, 50, 0, 50));
        detailPanel.add(endTimeComboBox, gbc);
        return detailPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton backButton = new JButton("Back");
        JButton reserveButton = new JButton("Conferma");
        JButton deleteButton = new JButton("Elimina Prenotazione");
        backButton.addActionListener(e ->{
            pageNavigationController.navigateToReservationsTable();
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
                JOptionPane.showMessageDialog(this, "Inserire correttamente gli orari", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (Engine.getInstance().updateReservation(reservationId, Engine.getInstance().getReservationByID(reservationId).getClubid(), Engine.getInstance().getReservationByID(reservationId).getFieldId(), date, startTime, endTime)) {
                    saveChanges(date, startTime, endTime);
                    JOptionPane.showMessageDialog(this, "Modifica avvenuta con successo!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Campo già prenotato in questi orari!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            pageNavigationController.navigateToUserHome();
        });

        deleteButton.addActionListener(e->{
            if(Engine.getInstance().deleteReservation(reservationId)){
                JOptionPane.showMessageDialog(this, "Prenotazione eliminata con successo!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione della prenotazione!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pageNavigationController.navigateToReservationsTable();
        });

        backButton.setFocusable(false);
        reserveButton.setFocusable(false);
        deleteButton.setFocusable(false);

        buttonPanel.add(backButton);
        buttonPanel.add(reserveButton);
        buttonPanel.add(deleteButton);

        return buttonPanel;
    }

    private void saveChanges(Date date, Time startRent, Time endRent){
        ArrayList<Reservation> reservations = Engine.getInstance().getUser().getReservations();

        for(Reservation reservation : reservations){
            if(reservation.getId() == reservationId){
                reservation.setDate(date);
                reservation.setStartrent(startRent);
                reservation.setEndrent(endRent);
            }
        }
        Engine.getInstance().getUser().loadReservations(reservations);
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
