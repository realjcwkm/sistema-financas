package com.mycompany.sistemaplanejago.componentes.SwitchButton;

import com.mycompany.sistemaplanejago.componentes.SwitchButton.SwitchButton;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class SwitchButtonCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    private final SwitchButton switchButton;

    public SwitchButtonCellRenderer() {
        this.switchButton = new SwitchButton();
        setOpaque(true); 
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String tipo = (String) table.getModel().getValueAt(row, 1); 

        // Se o tipo for "Despesa", o SwitchButton DEVE APARECER.
        if ("Despesa".equalsIgnoreCase(tipo)) { 
            if (value instanceof Boolean) {
                switchButton.setSelected((Boolean) value);
            } else {
                switchButton.setSelected(false); 
            }
            return switchButton; 
        } else { // SE o tipo NÃO for "Despesa" (ex: "Receita"), a célula deve ficar VAZIA.
            super.getTableCellRendererComponent(table, null, isSelected, hasFocus, row, column); 
            setText(""); // Garante que nenhum texto seja exibido explicitamente

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            setHorizontalAlignment(CENTER); 
            return this;
        }
    }
}