package com.mycompany.mindmap.Telas.Administrador.InicioAdm;

import com.mycompany.mindmap.Telas.Inicio.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorAdm extends DefaultCellEditor {

    private TableActionEventAdm evento;
    
    public TableActionCellEditorAdm(TableActionEventAdm evento) {
        super(new JCheckBox());
        this.evento = evento;
    }
       

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int linha, int column) {
        OpcoesAbasAdm opcoesAbas = new OpcoesAbasAdm();
        opcoesAbas.initEvent(evento, linha);
        opcoesAbas.setBackground(table.getSelectionBackground());
        return opcoesAbas;
    }
    
    
    
}
