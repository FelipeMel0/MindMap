package com.mycompany.mindmap.Classes.dao;

import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public Connection conexao;

    public boolean usuarioExiste(Usuario usuario) throws Exception {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = ConexaoBD.obtemConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }

    }

    public void criarUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario (nome, dataNasc, email, senha, tipoUsuario) VALUES (?, ?, ?, ?, ?)";
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement insert = conn.prepareStatement(sql);

            insert.setString(1, usuario.getNome());
            insert.setString(2, usuario.getDataNasc());
            insert.setString(3, usuario.getEmail());
            insert.setString(4, usuario.getSenha());
            insert.setString(5, usuario.getTipoUsuario());
            
            insert.execute();

        } catch (Exception e) {

            System.out.println("Erro na inclusão: " + e.getMessage());

        }
    }

}
