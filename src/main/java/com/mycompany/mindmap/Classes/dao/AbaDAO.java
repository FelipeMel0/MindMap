package com.mycompany.mindmap.Classes.dao;

import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Aba;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AbaDAO {

    public Connection conn = ConexaoBD.obtemConexao();

    public void criarTarefa(Aba aba) {

        try {
            String sql = "INSERT INTO aba (titulo, idUsuario) VALUES (?, ?)";
            PreparedStatement insert = conn.prepareStatement(sql);

            insert.setString(1, aba.getTitulo());
            insert.setInt(2, aba.getIdUsuario());

            insert.execute();

        } catch (Exception e) {

            System.out.println("Erro na inclusão: " + e.getMessage());

        }

    }
    
    public Aba selecionarAbaPorId(int idAba) {
        
        Aba aba = null;
        
        String sql = "SELECT * FROM aba WHERE idAba = ?";
        
        try{
            
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);
            
            consulta.setInt(1, idAba);
            
            ResultSet rs = consulta.executeQuery();
            
            if(rs.next() == true){
                int idAbaSelecionada = Integer.parseInt(rs.getString("idAba"));
                String titulo = rs.getString("titulo");
                int idUsuario = Integer.parseInt(rs.getString("idUsuario"));
                
                aba = new Aba(idAbaSelecionada, titulo, idUsuario);
                
            }
            
        } catch (Exception e ){
            System.out.println("Erro: " + e.getMessage());
        }
        
        return aba;
        
    }

    public void editarAba(Aba aba) {

        try {
            String sql = "UPDATE aba SET titulo = ? WHERE idAba = ?";

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement update = conn.prepareStatement(sql);
            
            update.setString(1, aba.getTitulo());
            update.setInt(2, aba.getIdAba());
            
            update.execute();

        } catch (Exception e){
            
            System.out.println("Erro na edição: " + e.getMessage());
            
        }

    }
    
    public void excluirAba(int idAba){
        
        try {
            
            String sql = "DELETE FROM aba WHERE idAba = ?";
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement delete = conn.prepareStatement(sql);

            delete.setInt(1, idAba);

            delete.execute();
            
        } catch (Exception e){
            
            System.out.println("Erro na exclusão: " + e.getMessage());
            
        }
        
    }

}
