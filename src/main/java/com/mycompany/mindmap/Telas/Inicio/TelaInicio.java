/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mindmap.Telas.Inicio;

import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felip
 */
public class TelaInicio extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicio
     */
    int idUsuario = 0;

    public TelaInicio(Usuario usuario) {

        initComponents();
        idUsuario = usuario.getIdUsuario();
        consultaAbas();

        tableAba.getTableHeader().setFont(new Font("TW Cen MT", Font.BOLD, 14));
        tableAba.getTableHeader().setOpaque(false);
        tableAba.getTableHeader().setForeground(new Color(0, 0, 0));

        //Aplicando opções para a terceira coluna
        TableActionEvent evento = new TableActionEvent() {
            @Override
            public void editarAba(int linha) {
                System.out.println("Editar: " + linha);
                JOptionPane.showMessageDialog(null, "Editar: " + linha);
            }

            @Override
            public void excluirAba(int linha) {
                System.out.println("Excluir: " + linha);
                JOptionPane.showMessageDialog(null, "Excluir:  " + linha);
            }
        };
                
        tableAba.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
        tableAba.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(evento));

    }

    public void consultaAbas() {

        DefaultTableModel tabelaModel = (DefaultTableModel) tableAba.getModel();
        tabelaModel.setRowCount(0);

        String sql = "SELECT * FROM aba WHERE idUsuario = ?";

        try {
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);
            consulta.setInt(1, idUsuario);

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                tabelaModel.addRow(new String[]{rs.getString(1), rs.getString(2)});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer consulta");
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAba = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Início");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setText("Suas abas:");

        tableAba.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        tableAba.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Título", "Opções"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAba.setRowHeight(40);
        tableAba.setSelectionBackground(new java.awt.Color(0, 143, 188));
        tableAba.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tableAba);
        if (tableAba.getColumnModel().getColumnCount() > 0) {
            tableAba.getColumnModel().getColumn(0).setMinWidth(100);
            tableAba.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableAba.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario usuario = null;
                new TelaInicio(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAba;
    // End of variables declaration//GEN-END:variables
}