package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Field;
import Model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationsTable extends StandardView {
    private JTable table;
    private JScrollPane scrollPane;

    public ReservationsTable() {
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

        JLabel titleLabel = new JLabel("LE MIE PRENOTAZIONI", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Club", "Campo", "Data", "Ora di inizio", "Ora di fine", "Data/Ora"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        ArrayList<Reservation> reservations = Engine.getInstance().getUser().getReservations();
        if (reservations.isEmpty()) {
            JLabel noReservationsLabel = new JLabel("Nessuna prenotazione ancora effettuata", JLabel.CENTER);
            noReservationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
            tablePanel.add(noReservationsLabel, BorderLayout.CENTER);
        } else {
            for (Reservation reservation : reservations) {
                Object[] rowData = {
                        reservation.getId(),
                        Engine.getInstance().getNameById(reservation.getClubid()),
                        reservation.getFieldId(),
                        reservation.getDate(),
                        reservation.getStartrent(),
                        reservation.getEndrent(),
                        reservation.getDatetime()
                };
                tableModel.addRow(rowData);
            }
            table = new JTable(tableModel);
            scrollPane = new JScrollPane(table);
            table.getColumnModel().getColumn(6).setPreferredWidth(200);scrollPane = new JScrollPane(table);
            tablePanel.add(scrollPane, BorderLayout.CENTER);

        }

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e ->{
            pageNavigationController.navigateToUserHome();
        });

        backButton.setFocusable(false);

        buttonPanel.add(backButton);

        return buttonPanel;
    }
}

