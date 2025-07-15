package com.mycompany.sistemaplanejago.componentes.ActionButtons; 

import java.awt.Component;
import java.awt.FlowLayout; 
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.mycompany.sistemaplanejago.componentes.ActionButtons.ActionButton;

public class TableActionCellRender extends JPanel implements TableCellRenderer {

    private ActionPanel actionPanel; 

    public TableActionCellRender() {
        setOpaque(true);
        actionPanel = new ActionPanel(); 
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) { actionPanel.setBackground(table.getSelectionBackground()); } else { actionPanel.setBackground(table.getBackground()); }
        return actionPanel;
    }

    private static class ActionPanel extends JPanel { 
        private ActionButton viewButton;
        private ActionButton editButton;
        private ActionButton deleteButton;

        public ActionPanel() {
            setOpaque(true);
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); 

            viewButton = new ActionButton();
            editButton = new ActionButton();
            deleteButton = new ActionButton();
            
            // Outro carregamento que não funcionou
            viewButton.setActionIcon("/img/olho.png"); 
            editButton.setActionIcon("/img/botao-editar.png"); 
            deleteButton.setActionIcon("/img/excluir.png"); 
            
            add(viewButton);
            add(editButton);
            add(deleteButton);
        }
    }
}