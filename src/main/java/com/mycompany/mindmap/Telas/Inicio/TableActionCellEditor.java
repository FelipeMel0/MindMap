package com.mycompany.mindmap.Telas.Inicio;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent evento;
    
    public TableActionCellEditor(TableActionEvent evento) {
        super(new JCheckBox());
        this.evento = evento;
    }
       

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int linha, int column) {
        OpcoesAbas opcoesAbas = new OpcoesAbas();
        opcoesAbas.initEvent(evento, linha);
        opcoesAbas.setBackground(table.getSelectionBackground());
        return opcoesAbas;
    }
    
    
    
}
