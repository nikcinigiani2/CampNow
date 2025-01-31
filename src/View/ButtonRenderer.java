package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class ButtonRenderer extends JButton implements TableCellRenderer {
    private String type;
    public ButtonRenderer(String type) {
        this.type = type;
        setOpaque(true);
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(type); // Testo del bottone
        return this;
    }
}
