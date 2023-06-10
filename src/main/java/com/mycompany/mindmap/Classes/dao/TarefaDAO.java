package com.mycompany.mindmap.Classes.dao;

import com.mycompany.mindmap.Classes.ConexaoBD;
import java.sql.Connection;
import com.mycompany.mindmap.Classes.Tarefa;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TarefaDAO {
    
    public Connection conn = ConexaoBD.obtemConexao();
    
    public void criarTarefa(Tarefa tarefa, int idAba, int idUsuario){
        
        try{
            
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
            
        } catch (Exception e ){
            System.out.println(e.getMessage());
        }
        
    }
    
}
