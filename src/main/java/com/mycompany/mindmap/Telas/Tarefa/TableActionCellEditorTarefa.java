package com.mycompany.mindmap.Telas.Tarefa;

import com.mycompany.mindmap.Telas.Inicio.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorTarefa extends DefaultCellEditor {

    private TableActionEventTarefa evento;
    
    public TableActionCellEditorTarefa(TableActionEventTarefa evento) {
        super(new JCheckBox());
        this.evento = evento;
    }
       

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int linha, int column) {
        OpcoesTarefas opcoesAbas = new OpcoesTarefas();
        opcoesAbas.initEvent(evento, linha);
        opcoesAbas.setBackground(table.getSelectionBackground());
        return opcoesAbas;
    }
    
    
    
}
