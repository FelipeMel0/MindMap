package com.mycompany.mindmap.Telas.Gastos;

import com.mycompany.mindmap.Telas.Inicio.*;
import com.mycompany.mindmap.Classes.Usuario;
import com.mycompany.mindmap.Classes.dao.GastosDAO;
import com.mycompany.mindmap.Classes.Gastos;
import com.mycompany.mindmap.Classes.dao.UsuarioDAO;
import com.mycompany.mindmap.Telas.Gastos.Dialogs.DialogEditarDespesas;
import com.mycompany.mindmap.Telas.Gastos.Dialogs.DialogEditarSaldo;
import com.mycompany.mindmap.Telas.TelaLogin;
import com.mycompany.mindmap.Telas.Usuario.DialogSelecionarOpcoes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;

public class TelaGastos extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicio
     */
    int idUsuario = 0;

    public TelaGastos(Usuario usuario) {

        super("Tela de gastos");
        initComponents();
        setLocationRelativeTo(null);

        idUsuario = usuario.getIdUsuario();

        UsuarioDAO usuarioDao = new UsuarioDAO();

        Usuario usuarioLogado = usuarioDao.selecionarPorId(idUsuario);

        String arrayNome[] = usuarioLogado.getNome().split(" ", 2);

        labelNomeUsuario.setText(arrayNome[0]);

        String nome = usuarioLogado.getNome();
        String dataNasc = usuarioLogado.getDataNasc();
        String email = usuarioLogado.getEmail();
        String senha = usuarioLogado.getSenha();
        String tipoUsuario = usuarioLogado.getTipoUsuario();

        Usuario usuarioSelecionado = new Usuario(idUsuario, nome, dataNasc, email, senha, tipoUsuario);

        try {

            DialogSelecionarOpcoes dialogSelecionarOpcoes = new DialogSelecionarOpcoes(this, rootPaneCheckingEnabled, usuarioSelecionado);

            labelIconePerfil.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
//                System.out.println("Você clicou no ícone de perfil");
                    dialogSelecionarOpcoes.setVisible(true);
                }

            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        GastosDAO gastosDao = new GastosDAO();
        try {
            Gastos gastos = gastosDao.selecionarGastosPorId(idUsuario);

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator(',');
            simbolos.setGroupingSeparator('.');

            DecimalFormat formatarDecimal = new DecimalFormat("#,##0.00");
            formatarDecimal.setDecimalFormatSymbols(simbolos);

            String saldoFormatado = formatarDecimal.format(gastos.getSaldo());
            labelSaldo.setText("R$" + saldoFormatado);

            String despesasFormatada = formatarDecimal.format(gastos.getDespesas());
            labelDespesas.setText("R$" + despesasFormatada);
        } catch (Exception e) {

            int idUsuarioSelecionado = idUsuario;
            Gastos gasto = new Gastos(0, 0.0, 0.0, idUsuarioSelecionado);
            gastosDao.criarGastos(gasto);

            try {

                labelSaldo.setText("R$" + gasto.getSaldo().toString());
                labelDespesas.setText("R$" + gasto.getDespesas().toString());

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

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
        jPanel1 = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonTarefas = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        buttonInicio = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        labelDespesas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        buttonModificarSaldo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Gastos");

        jPanel1.setBackground(new java.awt.Color(0, 194, 255));

        labelNomeUsuario.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelNomeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeUsuario.setText("nome");

        labelIconePerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconePerfil.png"))); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOpaque(true);

        buttonTarefas.setBackground(new java.awt.Color(0, 194, 255));
        buttonTarefas.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        buttonTarefas.setForeground(new java.awt.Color(255, 255, 255));
        buttonTarefas.setText("TAREFAS");
        buttonTarefas.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        buttonTarefas.setBorderPainted(false);
        buttonTarefas.setContentAreaFilled(false);
        buttonTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTarefasActionPerformed(evt);
            }
        });

        buttonLogout.setBackground(new java.awt.Color(0, 194, 255));
        buttonLogout.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        buttonLogout.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogout.setText("LOGOUT");
        buttonLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        buttonLogout.setBorderPainted(false);
        buttonLogout.setContentAreaFilled(false);
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        buttonInicio.setBackground(new java.awt.Color(0, 194, 255));
        buttonInicio.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        buttonInicio.setForeground(new java.awt.Color(255, 255, 255));
        buttonInicio.setText("INÍCIO");
        buttonInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        buttonInicio.setBorderPainted(false);
        buttonInicio.setContentAreaFilled(false);
        buttonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInicioActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 194, 255));
        jButton3.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("GASTOS");
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelIconePerfil)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonTarefas, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(buttonInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelIconePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(buttonTarefas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(buttonLogout)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(186, 186, 186)
                    .addComponent(buttonInicio)
                    .addContainerGap(289, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Despesas");

        labelDespesas.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        labelDespesas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDespesas.setText("DESPESAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(labelDespesas)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Saldo");

        labelSaldo.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        labelSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSaldo.setText("SALDO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(labelSaldo)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        buttonModificarSaldo.setBackground(new java.awt.Color(0, 194, 255));
        buttonModificarSaldo.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        buttonModificarSaldo.setForeground(new java.awt.Color(255, 255, 255));
        buttonModificarSaldo.setText("MODIFICAR SALDO");
        buttonModificarSaldo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonModificarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarSaldoActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 194, 255));
        jButton2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("MODIFICAR DESPESAS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(buttonModificarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonModificarSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTarefasActionPerformed
//        JOptionPane.showMessageDialog(null, "Tarefas");

        DialogSelecionarTarefasAba dialogTarefasAba = new DialogSelecionarTarefasAba(this, rootPaneCheckingEnabled, idUsuario);
        dialogTarefasAba.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_buttonTarefasActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        this.dispose();

        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);

    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void buttonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInicioActionPerformed
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = usuarioDao.selecionarPorId(idUsuario);

        TelaInicio telaInicio = new TelaInicio(usuario);
        telaInicio.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_buttonInicioActionPerformed

    private void buttonModificarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarSaldoActionPerformed
        GastosDAO gastosDao = new GastosDAO();
        Gastos gasto = gastosDao.selecionarGastosPorId(idUsuario);

        DialogEditarSaldo dialogEditar = new DialogEditarSaldo(this, rootPaneCheckingEnabled, gasto);
        dialogEditar.setVisible(true);
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formatarDecimal = new DecimalFormat("#,##0.00");
        formatarDecimal.setDecimalFormatSymbols(simbolos);

        Gastos gastoAtualizado = gastosDao.selecionarGastosPorId(idUsuario);
        String saldoFormatado = formatarDecimal.format(gastoAtualizado.getSaldo());
        labelSaldo.setText("R$" + saldoFormatado);

    }//GEN-LAST:event_buttonModificarSaldoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GastosDAO gastosDao = new GastosDAO();
        Gastos gasto = gastosDao.selecionarGastosPorId(idUsuario);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = usuarioDao.selecionarPorId(idUsuario);

        DialogEditarDespesas dialogEditarDespesas = new DialogEditarDespesas(this, rootPaneCheckingEnabled, gasto, usuario);
        dialogEditarDespesas.setVisible(true);
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formatarDecimal = new DecimalFormat("#,##0.00");
        formatarDecimal.setDecimalFormatSymbols(simbolos);

        Gastos gastoAtualizado = gastosDao.selecionarGastosPorId(idUsuario);
        String despesaFormatada = formatarDecimal.format(gastoAtualizado.getDespesas());
        labelDespesas.setText("R$" + despesaFormatada);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario usuario = null;
                new TelaGastos(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonInicio;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonModificarSaldo;
    private javax.swing.JButton buttonTarefas;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelDespesas;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelSaldo;
    // End of variables declaration//GEN-END:variables
}
