package com.mycompany.mindmap.Classes.dao;

import com.mycompany.mindmap.Classes.Aba;
import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Gastos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GastosDAO {

    public void criarGastos(Gastos gasto) {

        try {
            String sql = "INSERT INTO gastos (saldo, despesas) VALUES (?, ?)";
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement insert = conn.prepareStatement(sql);

            insert.setDouble(1, gasto.getSaldo());
            insert.setDouble(2, gasto.getDespesas());

            insert.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public Gastos selecionarGastosPorId(int idUsuario){
        
        Gastos gasto = null;
        
        String sql = "SELECT * FROM gastos WHERE idUsuario = ?";
        
        try{
            
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);
            
            consulta.setInt(1, idUsuario);
            
            ResultSet rs = consulta.executeQuery();
            
            if(rs.next() == true){
                int idGastos = Integer.parseInt(rs.getString("idGastos"));
                Double saldo = rs.getDouble("saldo");
                Double despesas = rs.getDouble("despesas");
                int idUsuarioLogado = Integer.parseInt(rs.getString("idUsuario"));
                
                gasto = new Gastos(idGastos, saldo, despesas, idUsuarioLogado);
                
            }
            
        } catch (Exception e ){
            System.out.println("Erro: " + e.getMessage());
        }
        
        return gasto;
        
    }

}
