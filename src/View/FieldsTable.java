package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Field;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FieldsTable extends StandardView {
    private JTable table;
    private JScrollPane scrollPane;

    public FieldsTable() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("I MIEI CAMPI", JLabel.CENTER);
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

        String[] columnNames = {"ID", "Numero", "Terreno", "Luci", "Spogliatoi", "Prezzo", "Orario apertura", "Orario chiusura", "Modifica"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
            return column==8;
        }
        };
        List<Field> fields = Engine.getInstance().getClub().getFields();
        if(fields.isEmpty()){
            JLabel noReservationsLabel = new JLabel("Nessun campo ancora inserito", JLabel.CENTER);
            noReservationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
            tablePanel.add(noReservationsLabel, BorderLayout.CENTER);
        }
        else{
            for (Field field : fields) {
                Object[] rowData = {
                        field.getId(),
                        field.getNumber(),
                        field.getSoil(),
                        field.getLightsToString(),
                        field.getLockerroomToString(),
                        field.getPrice(),
                        field.getStartTime(),
                        field.getEndTime(),
                        "Modifica"
                };

                tableModel.addRow(rowData);
            }
            table = new JTable(tableModel);
            PageNavigation pageNavigationController = PageNavigation.getInstance(this);
            table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer("Modifica"));
            table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor("ModificaC",table, pageNavigationController));

            scrollPane = new JScrollPane(table);
            tablePanel.add(scrollPane, BorderLayout.CENTER);
        }
        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton addButton = new JButton("Aggiungi un campo");
        addButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getInstance(this);
            pageNavigationController.navigateToAddField();
        });


        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getInstance(this);
            pageNavigationController.navigateToClubHome();
        });
        backButton.setFocusable(false);
        buttonPanel.add(backButton);

        addButton.setFocusable(false);
        buttonPanel.add(addButton);

        return buttonPanel;
    }
}

