package com.mycompany.mindmap.Classes.dao;

import com.mycompany.mindmap.Classes.ConexaoBD;
import java.sql.Connection;
import com.mycompany.mindmap.Classes.Tarefa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TarefaDAO {

    public Connection conn = ConexaoBD.obtemConexao();

    public void criarTarefa(Tarefa tarefa, int idAba, int idUsuario) {

        try {

            String sql = "INSERT INTO tarefa (titulo, statusConclusao, descricao, dataCriacao, horaCriacao, dataConclusao, horaConclusao, idUsuario, idAba) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement insert = conn.prepareStatement(sql);

            insert.setString(1, tarefa.getTitulo());
            insert.setString(2, tarefa.getStatusConclusao());
            insert.setString(3, tarefa.getDescricao());
            insert.setString(4, tarefa.getDataCriacao());
            insert.setString(5, tarefa.getHoraCriacao());
            insert.setString(6, tarefa.getDataConclusao());
            insert.setString(7, tarefa.getHoraConclusao());
            insert.setInt(8, idUsuario);
            insert.setInt(9, idAba);

            insert.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void editarTarefa(Tarefa tarefa) {

        try {

            String sql = "UPDATE tarefa SET titulo = ?, statusConclusao = ?, descricao = ?, dataConclusao = ?, horaConclusao = ? WHERE idTarefa = ?";

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement update = conn.prepareStatement(sql);

            update.setString(1, tarefa.getTitulo());
            update.setString(2, tarefa.getStatusConclusao());
            update.setString(3, tarefa.getDescricao());
            update.setString(4, tarefa.getDataConclusao());
            update.setString(5, tarefa.getHoraConclusao());

            update.setInt(6, tarefa.getIdTarefa());

            update.execute();

        } catch (Exception e) {

            System.out.println("Erro na edição: " + e.getMessage());

        }

    }

    public Tarefa selecionarTarefaPorId(int idTarefa) {

        Tarefa tarefa = null;

        String sql = "SELECT * FROM tarefa WHERE idTarefa = ?";

        try {
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);

            consulta.setInt(1, idTarefa);

            ResultSet rs = consulta.executeQuery();
            
            if(rs.next() == true){
                
                String titulo = rs.getString("titulo");
                String statusConclusao = rs.getString("statusConclusao");
                String descricao = rs.getString("descricao");
                String dataCriacao = rs.getString("dataCriacao");
                String horaCriacao = rs.getString("horaCriacao");
                String dataConclusao = rs.getString("dataConclusao");
                String horaConclusao = rs.getString("horaConclusao");
                
                int idUsuario = rs.getInt("idUsuario");
                int idAba = rs.getInt("idAba");
                
                tarefa = new Tarefa(titulo, statusConclusao, descricao, dataCriacao, horaCriacao, dataConclusao, horaConclusao, idUsuario, idAba);
                
            }
            
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        
        return tarefa;

    }

}
