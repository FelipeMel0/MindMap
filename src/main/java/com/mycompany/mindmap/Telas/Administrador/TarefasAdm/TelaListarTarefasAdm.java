package com.mycompany.mindmap.Telas.Administrador.TarefasAdm;

import com.mycompany.mindmap.Telas.Tarefa.*;
import com.mycompany.mindmap.Telas.Tarefa.Dialogs.DialogCriarTarefa;
import com.mycompany.mindmap.Telas.Tarefa.Dialogs.DialogEditarTarefa;
import com.mycompany.mindmap.Classes.Aba;
import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Tarefa;
import com.mycompany.mindmap.Classes.Usuario;
import com.mycompany.mindmap.Classes.dao.TarefaDAO;
import com.mycompany.mindmap.Classes.dao.UsuarioDAO;
import com.mycompany.mindmap.Telas.Administrador.GastosAdm.Dialogs.DialogSelecionarGastosUsuario;
import com.mycompany.mindmap.Telas.Administrador.InicioAdm.DialogSelecionarTarefasAbaAdm;
import com.mycompany.mindmap.Telas.Administrador.InicioAdm.TelaInicioAdm;
import com.mycompany.mindmap.Telas.Inicio.DialogSelecionarTarefasAba;
import com.mycompany.mindmap.Telas.Inicio.TelaInicio;
import com.mycompany.mindmap.Telas.Tarefa.Dialogs.DialogExcluirTarefa;
import com.mycompany.mindmap.Telas.TelaLogin;
import com.mycompany.mindmap.Telas.Usuario.DialogSelecionarOpcoes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaListarTarefasAdm extends javax.swing.JFrame {

    /**
     * Creates new form TelaListarTarefas
     */
    int idUsuario = 0;
    int idAba = 0;
    
    public TelaListarTarefasAdm(Usuario usuario, Aba aba) {
        super("Tarefas de " + aba.getTitulo() + " (Administrador)");
        initComponents();
        setLocationRelativeTo(null);

        idUsuario = usuario.getIdUsuario();
        idAba = aba.getIdAba();

        consultaTarefas();

        tableTarefas.getTableHeader().setFont(new Font("TW Cen MT", Font.BOLD, 12));
        tableTarefas.getTableHeader().setOpaque(false);
        tableTarefas.getTableHeader().setForeground(new Color(0, 0, 0));

        labelNomeAba.setText(aba.getTitulo() + ":");
        
        String arrayNome[] = usuario.getNome().split(" ", 2);
        labelNomeUsuario.setText(arrayNome[0]);
        
        //Aplicando opções para a terceira coluna
        TableActionEventTarefaAdm evento = new TableActionEventTarefaAdm() {
            @Override
            public void editarTarefa(int linha) {
//                System.out.println("Editar");
                editarTarefaSelecionada(linha);
            }

            @Override
            public void excluirTarefa(int linha) {
//                System.out.println("Excluir");
                excluirTarefaSelecionada(linha);                
            }
        };

        tableTarefas.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderTarefaAdm());
        tableTarefas.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditorTarefaAdm(evento));
        
        UsuarioDAO usuarioDao = new UsuarioDAO();

        Usuario usuarioLogado = usuarioDao.selecionarPorId(idUsuario);

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
        
    }
    
    public void editarTarefaSelecionada(int linhaTabela){
        
        String pegandoIdTarefa = tableTarefas.getModel().getValueAt(linhaTabela, 0).toString();
        int idTarefaSelecionada = Integer.parseInt(pegandoIdTarefa);
        
        TarefaDAO tarefaDao = new TarefaDAO();
        
        Tarefa tarefaSelecionada = tarefaDao.selecionarTarefaPorId(idTarefaSelecionada);
        
        String titulo = tarefaSelecionada.getTitulo();
        String statusConclusao = tarefaSelecionada.getStatusConclusao();
        String descricao = tarefaSelecionada.getDescricao();
        String dataConclusao = tarefaSelecionada.getDataConclusao();
        String horaConclusao = tarefaSelecionada.getHoraConclusao();
        
        Tarefa tarefa = new Tarefa(idTarefaSelecionada, titulo, statusConclusao, descricao, dataConclusao, horaConclusao);
        
        DialogEditarTarefa dialogEditarTarefa = new DialogEditarTarefa(this, rootPaneCheckingEnabled, tarefa);
        dialogEditarTarefa.setVisible(true);
        
        consultaTarefas();
        
    }
    
    public void excluirTarefaSelecionada(int linhaTabela){
        
        String pegandoIdTarefa = tableTarefas.getModel().getValueAt(linhaTabela, 0).toString();
        int idTarefaSelecionada = Integer.parseInt(pegandoIdTarefa);
        
        TarefaDAO tarefaDao = new TarefaDAO();
        
        Tarefa tarefaSelecionada = tarefaDao.selecionarTarefaPorId(idTarefaSelecionada);
        
        String titulo = tarefaSelecionada.getTitulo();
        String statusConclusao = tarefaSelecionada.getStatusConclusao();
        String descricao = tarefaSelecionada.getDescricao();
        String dataConclusao = tarefaSelecionada.getDataConclusao();
        String horaConclusao = tarefaSelecionada.getHoraConclusao();
        
        Tarefa tarefa = new Tarefa(idTarefaSelecionada, titulo, statusConclusao, descricao, dataConclusao, horaConclusao);
                
        DialogExcluirTarefa dialogExcluirTarefa = new DialogExcluirTarefa(this, rootPaneCheckingEnabled, tarefa);
        dialogExcluirTarefa.setVisible(true);
        
        consultaTarefas();
        
    }

    public void consultaTarefas() {

        DefaultTableModel tabelaModel = (DefaultTableModel) tableTarefas.getModel();
        tabelaModel.setRowCount(0);

        String sql = "SELECT * FROM tarefa WHERE idAba = ?";       

        try {
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);
//            consulta.setInt(1, idUsuario);
            consulta.setInt(1, idAba);

            ResultSet rs = consulta.executeQuery();
            
            while (rs.next()) {

                //Formatando data de criação
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date pegarDataCriacao = sdf.parse(rs.getString(5));
                sdf.applyPattern("dd/MM/yyyy");
                String dataCriacao = sdf.format(pegarDataCriacao);

                //Formatando data de conclusão
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                Date pegarDataConclusao = sdf2.parse(rs.getString(7));
                sdf2.applyPattern("dd/MM/yyyy");
                String dataConclusao = sdf2.format(pegarDataConclusao);

                //Formatando hora de criação
                LocalTime pegarHoraCriacao = LocalTime.parse(rs.getString(6), DateTimeFormatter.ofPattern("HH:mm:ss"));
                String horaCriacao = pegarHoraCriacao.format(DateTimeFormatter.ofPattern("HH'h'mm"));

                //Formatando hora de conclusão
                LocalTime pegarHoraConclusao = LocalTime.parse(rs.getString(8), DateTimeFormatter.ofPattern("HH:mm:ss"));
                String horaConclusao = pegarHoraConclusao.format(DateTimeFormatter.ofPattern("HH'h'mm"));

                tabelaModel.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), dataCriacao, horaCriacao, dataConclusao, horaConclusao});
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

        jPanel1 = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonTarefas = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        buttonInicio = new javax.swing.JButton();
        buttonGastos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelNomeAba = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTarefas = new javax.swing.JTable();
        buttonCriarTarefa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        buttonGastos.setBackground(new java.awt.Color(0, 194, 255));
        buttonGastos.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        buttonGastos.setForeground(new java.awt.Color(255, 255, 255));
        buttonGastos.setText("GASTOS");
        buttonGastos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGastos.setContentAreaFilled(false);
        buttonGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGastosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelIconePerfil)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonTarefas, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonGastos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(buttonInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
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
                .addComponent(buttonGastos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLogout)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(186, 186, 186)
                    .addComponent(buttonInicio)
                    .addContainerGap(289, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Tarefas");

        labelNomeAba.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        labelNomeAba.setText("Nome da aba");

        tableTarefas.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        tableTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Título", "Status", "Descrição", "Data de criação", "Hora de criação", "Data para conclusão", "Hora para conclusão", "Opções"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTarefas.setRowHeight(40);
        jScrollPane1.setViewportView(tableTarefas);
        if (tableTarefas.getColumnModel().getColumnCount() > 0) {
            tableTarefas.getColumnModel().getColumn(0).setMinWidth(70);
            tableTarefas.getColumnModel().getColumn(0).setPreferredWidth(70);
            tableTarefas.getColumnModel().getColumn(0).setMaxWidth(70);
            tableTarefas.getColumnModel().getColumn(2).setPreferredWidth(60);
            tableTarefas.getColumnModel().getColumn(4).setMinWidth(100);
            tableTarefas.getColumnModel().getColumn(4).setMaxWidth(100);
            tableTarefas.getColumnModel().getColumn(5).setMinWidth(100);
            tableTarefas.getColumnModel().getColumn(5).setMaxWidth(100);
            tableTarefas.getColumnModel().getColumn(6).setMinWidth(120);
            tableTarefas.getColumnModel().getColumn(6).setPreferredWidth(120);
            tableTarefas.getColumnModel().getColumn(6).setMaxWidth(120);
            tableTarefas.getColumnModel().getColumn(7).setMinWidth(120);
            tableTarefas.getColumnModel().getColumn(7).setPreferredWidth(120);
            tableTarefas.getColumnModel().getColumn(7).setMaxWidth(120);
            tableTarefas.getColumnModel().getColumn(8).setPreferredWidth(120);
        }

        buttonCriarTarefa.setBackground(new java.awt.Color(0, 194, 255));
        buttonCriarTarefa.setFont(new java.awt.Font("Tw Cen MT", 1, 48)); // NOI18N
        buttonCriarTarefa.setForeground(new java.awt.Color(255, 255, 255));
        buttonCriarTarefa.setText("+");
        buttonCriarTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCriarTarefaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNomeAba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonCriarTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNomeAba)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCriarTarefa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTarefasActionPerformed
        //        JOptionPane.showMessageDialog(null, "Tarefas");
        DialogSelecionarTarefasAbaAdm dialogTarefasAbaAdm = new DialogSelecionarTarefasAbaAdm(this, rootPaneCheckingEnabled, idUsuario);
        dialogTarefasAbaAdm.setVisible(true);
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
        
        TelaInicioAdm telaInicioAdm = new TelaInicioAdm(usuario);
        telaInicioAdm.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_buttonInicioActionPerformed

    private void buttonCriarTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCriarTarefaActionPerformed
        DialogCriarTarefa dialogCriarTarefa = new DialogCriarTarefa(this, rootPaneCheckingEnabled, idAba, idUsuario);
        dialogCriarTarefa.setVisible(true);
        
        consultaTarefas();
    }//GEN-LAST:event_buttonCriarTarefaActionPerformed

    private void buttonGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGastosActionPerformed
        DialogSelecionarGastosUsuario dialogSelecionar = new DialogSelecionarGastosUsuario(this, rootPaneCheckingEnabled, idUsuario);
        dialogSelecionar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonGastosActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListarTarefasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListarTarefasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListarTarefasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListarTarefasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            Usuario usuario = null;
            Aba aba = null;
            public void run() {
                new TelaListarTarefasAdm(usuario, aba).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCriarTarefa;
    private javax.swing.JButton buttonGastos;
    private javax.swing.JButton buttonInicio;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonTarefas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelNomeAba;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JTable tableTarefas;
    // End of variables declaration//GEN-END:variables
}
