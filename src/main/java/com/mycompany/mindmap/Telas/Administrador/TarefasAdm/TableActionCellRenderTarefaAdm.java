/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mindmap.Telas.Administrador.TarefasAdm;

import com.mycompany.mindmap.Telas.Tarefa.*;
import com.mycompany.mindmap.Telas.Inicio.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRenderTarefaAdm extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        OpcoesTarefasAdm opcoesAbas = new OpcoesTarefasAdm();

        if (isSelected == false) {
            opcoesAbas.setBackground(Color.WHITE);
        } else {
            opcoesAbas.setBackground(com.getBackground());
        }

        return opcoesAbas;
    }

}
