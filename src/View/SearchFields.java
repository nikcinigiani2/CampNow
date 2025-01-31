package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Field;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class SearchFields extends StandardView {
    private JTable table;
    private JScrollPane scrollPane;

    public SearchFields() {
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

        JLabel titleLabel = new JLabel("RICERCA CAMPO", JLabel.CENTER);
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

        String[] columnNames = {"ID", "Club", "Città", "Indirizo", "Numero", "Prezzo", "Prenota"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==6;
            }
        };
        ArrayList<Field> fields = Engine.getInstance().getAllFields();
        JToggleButton reserveButton;
        ButtonGroup buttonGroup = new ButtonGroup();
        for (Field field : fields) {
            Object[] rowData = {
                    field.getId(),
                    Engine.getInstance().getNameById(field.getClubid()),
                    Engine.getInstance().getCityById(field.getClubid()),
                    Engine.getInstance().getAddressById(field.getClubid()),
                    field.getNumber(),
                    field.getPrice(),
                    "Prenota"
            };
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);

        PageNavigation pageNavigation = PageNavigation.getInstance(this);
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Prenota"));
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor("Prenota",table, pageNavigation));

        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

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

