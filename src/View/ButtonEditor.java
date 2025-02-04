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
        this.type = type;
        button = new JButton(type=="Prenota" ? "Prenota" : "Modifica");
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped(); // Ferma l'editing della cella

            if(type == "Prenota"){
                int fieldId = (int) table.getValueAt(selectedRow, 0);
                String clubId = (String) table.getValueAt(selectedRow, 1);
                String clubName = (String) table.getValueAt(selectedRow, 2);
                pageNavigation.navigateToReserveField(fieldId, clubId, clubName); // Cambia finestra

            } else if(type == "ModificaC") {
                int fieldId = (int) table.getValueAt(selectedRow, 0);
                pageNavigation.navigateToEditField(fieldId);
            }
            else if(type == "ModificaP"){
                int reservationId = (int) table.getValueAt(selectedRow, 0);
                pageNavigation.navigateToEditReservation(reservationId);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        switch (type){
            case "Prenota":
                return "Prenota";
            default:
                return "Modifica";
        }
    }
}