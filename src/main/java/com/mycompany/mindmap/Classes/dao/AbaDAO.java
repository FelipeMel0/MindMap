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
        
}
