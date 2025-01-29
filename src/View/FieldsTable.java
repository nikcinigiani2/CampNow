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
    protected void setupWindow() {
        setTitle("CampNow");
        setSize(900, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        String[] columnNames = {"ID", "Number", "Address", "City"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<Field> fields = Engine.getInstance().getClub().getFields();
        for (Field field : fields) {
            Object[] rowData = {
                    field.getId(),
                    field.getNumber(),
                    Engine.getInstance().getClub().getAddress(),
                    Engine.getInstance().getClub().getCity()
            };
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton addButton = new JButton("Aggiungi un campo");
        PageNavigation pageNavigationController = PageNavigation.getInstance(this);
        addButton.addActionListener(e -> {
            pageNavigationController.navigateToAddField();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e ->{
            pageNavigationController.navigateToClubHome();
        });

        addButton.setFocusable(false);
        backButton.setFocusable(false);

        buttonPanel.add(backButton);
        buttonPanel.add(addButton);


        return buttonPanel;
    }
}

