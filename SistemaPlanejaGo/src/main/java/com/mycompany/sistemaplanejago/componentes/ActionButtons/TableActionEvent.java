package com.mycompany.sistemaplanejago.componentes.ActionButtons; 

public interface TableActionEvent {
    void onEdit(int row);
    void onDelete(int row);
    void onView(int row);
}