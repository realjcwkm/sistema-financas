package com.mycompany.sistemaplanejago.componentes.ActionButtons;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JPanel; 

import com.mycompany.sistemaplanejago.componentes.ActionButtons.ActionButton;
import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionEvent;
import java.awt.event.MouseEvent;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event; 
    private ActionPanel actionPanel; 

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
        
        actionPanel = new ActionPanel(event, this); 
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        actionPanel.setCurrentRowAndTable(row, table);

        if (isSelected) { actionPanel.setBackground(table.getSelectionBackground()); } else { actionPanel.setBackground(table.getBackground()); }
        return actionPanel;
    }

    @Override public Object getCellEditorValue() { return null; }
    @Override public boolean isCellEditable(java.util.EventObject anEvent) {
        if (anEvent instanceof MouseEvent) { 
            MouseEvent me = (MouseEvent) anEvent; 
            return me.getClickCount() == 1; }
        return true; 
    }
    @Override public boolean shouldSelectCell(java.util.EventObject anEvent) { return true; }
    @Override public boolean stopCellEditing() { fireEditingStopped(); return true; }
    @Override public void cancelCellEditing() { fireEditingCanceled(); }

    private static class ActionPanel extends JPanel { 
        private ActionButton viewButton;
        private ActionButton editButton;
        private ActionButton deleteButton;

        private TableActionEvent internalEvent;
        private DefaultCellEditor internalEditor;
        private JTable internalTable;
        private int internalRow;     

        public ActionPanel(TableActionEvent event, DefaultCellEditor editor) {
            this.internalEvent = event;
            this.internalEditor = editor;

            setOpaque(true); 
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); 

            viewButton = new ActionButton();
            editButton = new ActionButton();
            deleteButton = new ActionButton();
            
            //Os icones que não carregam de jeito nenhum
            viewButton.setActionIcon("/img/olho.png"); 
            editButton.setActionIcon("/img/botao-editar.png"); 
            deleteButton.setActionIcon("/img/excluir.png"); 
            
            add(viewButton);
            add(editButton);
            add(deleteButton);

            viewButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) { if (internalTable != null && internalRow != -1 && internalEvent != null) { internalEvent.onView(internalTable.convertRowIndexToModel(internalRow)); SwingUtilities.invokeLater(() -> internalEditor.stopCellEditing()); }}
            });
            editButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) { if (internalTable != null && internalRow != -1 && internalEvent != null) { internalEvent.onEdit(internalTable.convertRowIndexToModel(internalRow)); SwingUtilities.invokeLater(() -> internalEditor.stopCellEditing()); }}
            });
            deleteButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) { if (internalTable != null && internalRow != -1 && internalEvent != null) { internalEvent.onDelete(internalTable.convertRowIndexToModel(internalRow)); SwingUtilities.invokeLater(() -> internalEditor.stopCellEditing()); }}
            });
        }
        
        public void setCurrentRowAndTable(int row, JTable table) {
            this.internalRow = row;
            this.internalTable = table;
        }
    }
}