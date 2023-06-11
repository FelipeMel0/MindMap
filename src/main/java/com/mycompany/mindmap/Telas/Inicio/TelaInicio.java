package com.mycompany.mindmap.Telas.Inicio;

import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Usuario;
import com.mycompany.mindmap.Classes.dao.AbaDAO;
import com.mycompany.mindmap.Classes.Aba;
import com.mycompany.mindmap.Classes.dao.UsuarioDAO;
import com.mycompany.mindmap.Telas.Aba.DialogCadastroAba;
import com.mycompany.mindmap.Telas.Aba.DialogEditarAba;
import com.mycompany.mindmap.Telas.Aba.DialogExcluirAba;
import com.mycompany.mindmap.Telas.TelaLogin;
import com.mycompany.mindmap.Telas.Usuario.DialogSelecionarOpcoes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;

public class TelaInicio extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicio
     */
    int idUsuario = 0;
    int idAba = 0;

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

    public void editarAbaSelecionada(int linhaTabela) {

        String pegandoIdAba = tableAba.getModel().getValueAt(linhaTabela, 0).toString();
        int idAbaSelecionada = Integer.parseInt(pegandoIdAba);

        AbaDAO abaDao = new AbaDAO();

        Aba abaSelecionada = abaDao.selecionarAbaPorId(idAbaSelecionada);

        int idUsuarioAba = abaSelecionada.getIdUsuario();
        String tituloAba = abaSelecionada.getTitulo();

        Aba aba = new Aba(idAbaSelecionada, tituloAba, idUsuarioAba);

        JDialog dialog = new DialogEditarAba(this, true, aba);
        dialog.setVisible(true);

        consultaAbas();

    }

    public void excluirAbaSelecionada(int linhaTabela) {

        String pegandoIdAba = tableAba.getModel().getValueAt(linhaTabela, 0).toString();
        int idAbaSelecionada = Integer.parseInt(pegandoIdAba);

        AbaDAO abaDao = new AbaDAO();

        Aba abaSelecionada = abaDao.selecionarAbaPorId(idAbaSelecionada);

        int idAba = abaSelecionada.getIdAba();
        int idUsuarioAba = abaSelecionada.getIdUsuario();
        String tituloAba = abaSelecionada.getTitulo();

        Aba aba = new Aba(idAba, tituloAba, idUsuarioAba);

        JDialog dialog = new DialogExcluirAba(this, true, aba);
        dialog.setVisible(true);

        consultaAbas();

    }

    public TelaInicio(Usuario usuario) {

        super("Tela de início");
        initComponents();
        setLocationRelativeTo(null);

        idUsuario = usuario.getIdUsuario();
        consultaAbas();

        tableAba.getTableHeader().setFont(new Font("TW Cen MT", Font.BOLD, 14));
        tableAba.getTableHeader().setOpaque(false);
        tableAba.getTableHeader().setForeground(new Color(0, 0, 0));

        //Aplicando opções para a terceira coluna
        TableActionEvent evento = new TableActionEvent() {
            @Override
            public void editarAba(int linha) {
//                System.out.println("Editar: " + linha);
//                JOptionPane.showMessageDialog(null, "Editar: " + linha);
                editarAbaSelecionada(linha);
            }

            @Override
            public void excluirAba(int linha) {
//                System.out.println("Excluir: " + linha);
//                JOptionPane.showMessageDialog(null, "Excluir:  " + linha);
                excluirAbaSelecionada(linha);
            }
        };

        tableAba.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
        tableAba.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(evento));

        UsuarioDAO usuarioDao = new UsuarioDAO();

        Usuario usuarioLogado = usuarioDao.selecionarPorId(idUsuario);

        String arrayNome[] = usuarioLogado.getNome().split(" ", 2);

        labelNomeUsuario.setText(arrayNome[0]); 
        
        DialogSelecionarOpcoes dialogSelecionarOpcoes = new DialogSelecionarOpcoes(this, rootPaneCheckingEnabled);
        
        labelIconePerfil.addMouseListener(new MouseAdapter(){
        
            public void mouseClicked(MouseEvent e){
//                System.out.println("Você clicou no ícone de perfil");
                  
                  dialogSelecionarOpcoes.setVisible(true);
            }
            
        });

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
        buttonCadastroAba = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonTarefas = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        buttonInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

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
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        buttonCadastroAba.setBackground(new java.awt.Color(0, 194, 255));
        buttonCadastroAba.setFont(new java.awt.Font("Tw Cen MT", 1, 48)); // NOI18N
        buttonCadastroAba.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastroAba.setText("+");
        buttonCadastroAba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastroAbaActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLogout)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(186, 186, 186)
                    .addComponent(buttonInicio)
                    .addContainerGap(289, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(buttonCadastroAba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCadastroAba, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastroAbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastroAbaActionPerformed

        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuarioLogado = usuarioDao.selecionarPorId(idUsuario);

        JDialog dialog = new DialogCadastroAba(this, rootPaneCheckingEnabled, usuarioLogado);
        dialog.setVisible(true);

        consultaAbas();
    }//GEN-LAST:event_buttonCadastroAbaActionPerformed

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
//        JOptionPane.showMessageDialog(null, "Início");
    }//GEN-LAST:event_buttonInicioActionPerformed

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
    private javax.swing.JButton buttonCadastroAba;
    private javax.swing.JButton buttonInicio;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonTarefas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JTable tableAba;
    // End of variables declaration//GEN-END:variables
}
