package com.mycompany.sistemaplanejago.componentes;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SwitchButtonCellRenderer extends JPanel implements TableCellRenderer {

    private final SwitchButton switchButton;

    public SwitchButtonCellRenderer() {
        setOpaque(true);
        switchButton = new SwitchButton();
        add(switchButton);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof Boolean) {
            switchButton.setSelected((Boolean) value); 
        } else {
            switchButton.setSelected(false);
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        return this;
    }
}