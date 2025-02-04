package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Club;
import Model.Field;
import Model.Reservation;
import Model.User;

import javax.print.attribute.standard.PresentationDirection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReservationsTable extends StandardView {
    private JTable table;
    private JScrollPane scrollPane;
    private Object userType;

    public ReservationsTable(Object userType) {
        this.userType = userType;
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
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
        String[] columnNames;
        DefaultTableModel tableModel;

        if(userType instanceof User){
            columnNames = new String[]{"ID", "Club", "Campo", "Data", "Ora di inizio", "Ora di fine", "Data/Ora", "Modifica"};
            tableModel = new DefaultTableModel(columnNames, 0)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column==7;
                }
            };
        }else{
            columnNames = new String[]{"ID", "CF utente", "Nome", "Cognome", "Campo", "Data", "Ora di inizio", "Ora di fine", "Data/Ora"};
            tableModel = new DefaultTableModel(columnNames, 0);
        }

        ArrayList<Reservation> reservations;
        if(userType instanceof User)
            reservations = Engine.getInstance().getUser().getReservations();
        else
            reservations = Engine.getInstance().getClub().getReservations();
        
        if (reservations.isEmpty()) {
            JLabel noReservationsLabel = new JLabel("Nessuna prenotazione ancora effettuata", JLabel.CENTER);
            noReservationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
            tablePanel.add(noReservationsLabel, BorderLayout.CENTER);
        } else {
            if (userType instanceof User) {
                for (Reservation reservation : reservations) {
                    Object[] rowData = {
                            reservation.getId(),
                            Engine.getInstance().getNameById(reservation.getClubid()),
                            reservation.getFieldId(),
                            reservation.getDate(),
                            reservation.getStartrent(),
                            reservation.getEndrent(),
                            reservation.getDatetime(),
                            "Modifica"
                    };
                    tableModel.addRow(rowData);
                }
            }
            else{
                for (Reservation reservation : reservations) {
                    Object[] rowData = {
                            reservation.getId(),
                            reservation.getUsercf(),
                            Engine.getInstance().getNameByCF(reservation.getUsercf()),
                            Engine.getInstance().getSurnameByCF(reservation.getUsercf()),
                            reservation.getFieldId(),
                            reservation.getDate(),
                            reservation.getStartrent(),
                            reservation.getEndrent(),
                            reservation.getDatetime()
                    };
                    tableModel.addRow(rowData);
                }
            }

            table = new JTable(tableModel);
            PageNavigation pageNavigationController = PageNavigation.getInstance(this);
            if(userType instanceof User){
                table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Modifica"));
                table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor("ModificaP",table, pageNavigationController));
                table.getColumnModel().getColumn(6).setPreferredWidth(200);scrollPane = new JScrollPane(table);
            }else
                table.getColumnModel().getColumn(8).setPreferredWidth(200);scrollPane = new JScrollPane(table);



            scrollPane = new JScrollPane(table);
            tablePanel.add(scrollPane, BorderLayout.CENTER);
        }

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e ->{
            if(userType instanceof User)
                pageNavigationController.navigateToUserHome();

            else
                pageNavigationController.navigateToClubHome();
        });


        backButton.setFocusable(false);

        buttonPanel.add(backButton);

        return buttonPanel;
    }
}

