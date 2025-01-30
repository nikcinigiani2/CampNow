package View;


import Controller.PageNavigation;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private int selectedRow;

    public ButtonEditor(JTable table, PageNavigation pageNavigation) {
        button = new JButton("Prenota");
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped(); // Ferma l'editing della cella

            int fieldId = (int) table.getValueAt(selectedRow, 0);

            pageNavigation.navigateToReserveField(fieldId); // Cambia finestra
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