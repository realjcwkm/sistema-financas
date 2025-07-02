package com.mycompany.sistemaplanejago.componentes;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.mycompany.sistemaplanejago.componentes.SwitchButton;
import com.mycompany.sistemaplanejago.componentes.SwitchListener;

public class SwitchButtonCellEditor extends AbstractCellEditor implements TableCellEditor {

    private final SwitchButton switchButton;
    private Boolean currentValue;

    public SwitchButtonCellEditor() {
        switchButton = new SwitchButton();
        switchButton.addSwitchListener(new SwitchListener() { 
            @Override
            public void selectChange(boolean selected) {
                currentValue = selected;
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        if (value instanceof Boolean) {
            currentValue = (Boolean) value;
            switchButton.setSelected(currentValue);
        } else {
            currentValue = false;
            switchButton.setSelected(false);
        }

        if (isSelected) {
            switchButton.setBackground(table.getSelectionBackground());
            switchButton.setForeground(table.getSelectionForeground()); 
        } else {
            switchButton.setBackground(table.getBackground());
            switchButton.setForeground(table.getForeground()); 
        }

        return switchButton;
    }

    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }
}