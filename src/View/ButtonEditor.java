package View;


import Controller.PageNavigation;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private int selectedRow;
    private String type;

    public ButtonEditor(String type, JTable table, PageNavigation pageNavigation) {
        button = new JButton(type);
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped(); // Ferma l'editing della cella

            int fieldId = (int) table.getValueAt(selectedRow, 0);

            if(type == "Prenota")
                pageNavigation.navigateToReserveField(fieldId); // Cambia finestra
            else
                pageNavigation.navigateToClubHome();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Prenota"; // Testo del bottone
    }
}