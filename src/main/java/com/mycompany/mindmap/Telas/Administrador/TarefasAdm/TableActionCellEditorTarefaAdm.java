package com.mycompany.mindmap.Telas.Administrador.TarefasAdm;

import com.mycompany.mindmap.Telas.Tarefa.*;
import com.mycompany.mindmap.Telas.Inicio.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorTarefaAdm extends DefaultCellEditor {

    private TableActionEventTarefaAdm evento;
    
    public TableActionCellEditorTarefaAdm(TableActionEventTarefaAdm evento) {
        super(new JCheckBox());
        this.evento = evento;
    }
       

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int linha, int column) {
        OpcoesTarefasAdm opcoesAbas = new OpcoesTarefasAdm();
        opcoesAbas.initEvent(evento, linha);
        opcoesAbas.setBackground(table.getSelectionBackground());
        return opcoesAbas;
    }
    
    
    
}
