package com.mycompany.mindmap.Telas.Gastos.Dialogs;

import com.mycompany.mindmap.Classes.Gastos;
import com.mycompany.mindmap.Classes.Usuario;
import com.mycompany.mindmap.Classes.dao.GastosDAO;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class DialogEditarDespesasAdm extends javax.swing.JDialog {

    /**
     * Creates new form DialogEditarDespesas
     */
    int idGasto = 0;
    int idUsuario = 0;
    public DialogEditarDespesasAdm(java.awt.Frame parent, boolean modal, Gastos gastos, Usuario usuario) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        idGasto = gastos.getIdGastos();
        idUsuario = usuario.getIdUsuario();
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formatarDecimal = new DecimalFormat("#,##0.00");
        formatarDecimal.setDecimalFormatSymbols(simbolos);

        String despesaFormatada = formatarDecimal.format(gastos.getDespesas());
        txtDespesas.setText(despesaFormatada);
        
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
        txtDespesas = new javax.swing.JFormattedTextField();
        buttonEditarDespesas = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar despesas");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel2.setText("Modificar despesas:");

        txtDespesas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtDespesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDespesasActionPerformed(evt);
            }
        });

        buttonEditarDespesas.setBackground(new java.awt.Color(0, 194, 255));
        buttonEditarDespesas.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        buttonEditarDespesas.setForeground(new java.awt.Color(255, 255, 255));
        buttonEditarDespesas.setText("EDITAR DESPESAS");
        buttonEditarDespesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarDespesasActionPerformed(evt);
            }
        });

        buttonCancelar.setBackground(new java.awt.Color(255, 41, 69));
        buttonCancelar.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        buttonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelar.setText("CANCELAR");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonEditarDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(txtDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDespesas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditarDespesas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditarDespesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarDespesasActionPerformed
        GastosDAO gastosDao = new GastosDAO();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        double valorConvertido = 0.0;
        try {

            valorConvertido = decimalFormat.parse(txtDespesas.getText()).doubleValue();

        } catch (ParseException e) {
            System.out.println("Erro ao converter o valor.");
        }
        
        Gastos gasto = new Gastos(idGasto, valorConvertido, idUsuario);

        try {

            gastosDao.editarDespesasGastos(gasto);
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
            this.dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao realizar edição");
            System.out.println(e.getMessage());

        }
    }//GEN-LAST:event_buttonEditarDespesasActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void txtDespesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDespesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDespesasActionPerformed

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
            java.util.logging.Logger.getLogger(DialogEditarDespesasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogEditarDespesasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogEditarDespesasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogEditarDespesasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Gastos gastos = null;
                Usuario usuario = null;
                DialogEditarDespesasAdm dialog = new DialogEditarDespesasAdm(new javax.swing.JFrame(), true, gastos, usuario);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditarDespesas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JFormattedTextField txtDespesas;
    // End of variables declaration//GEN-END:variables
}
