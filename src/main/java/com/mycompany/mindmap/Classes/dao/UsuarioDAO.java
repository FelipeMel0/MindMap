package com.mycompany.mindmap.Classes.dao;

import com.mycompany.mindmap.Classes.ConexaoBD;
import com.mycompany.mindmap.Classes.Usuario;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UsuarioDAO {

    public Usuario realizarLogin(String email, String senha) throws Exception {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = ConexaoBD.obtemConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
//                Date dataNascimento = rs.getDate("dataNasc");
                String tipoUsuario = rs.getString("tipoUsuario");

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                String dataNasc = fmt.format(rs.getDate("dataNasc"));

                usuario = new Usuario(id, nome, dataNasc, email, senha, tipoUsuario);
            }
        }

        return usuario;

    }

    public Usuario selecionarPorId(int idUsuario) {
        
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE idUsuario = ?";

        try {

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);
            
            consulta.setInt(1, idUsuario);
            
            ResultSet rs = consulta.executeQuery();
            
            if(rs.next() == true){
                
                String nome = rs.getString("nome");
                String dataNasc = rs.getString("dataNasc");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String tipoUsuario = rs.getString("tipoUsuario");
                
                usuario = new Usuario(idUsuario, nome, dataNasc, email, senha, tipoUsuario);
                
            }              

        } catch (Exception e){
            
            System.out.println("Erro: " + e.getMessage());
            
        }
        
        return usuario;

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

    public void editarUsuario(Usuario usuario) {

        try {
            String sql = "UPDATE usuario SET nome = ?, dataNasc = ?, email = ?, senha = ? WHERE idUsuario = ?";

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement update = conn.prepareStatement(sql);

            update.setString(1, usuario.getNome());
            update.setString(2, usuario.getDataNasc());
            update.setString(3, usuario.getEmail());
            update.setString(4, usuario.getSenha());
            update.setInt(5, usuario.getIdUsuario());

            update.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void excluirUsuario(int idUsuario) {

        try {
            String sql = "DELETE FROM usuario WHERE idUsuario = ?";

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement delete = conn.prepareStatement(sql);

            delete.setInt(1, idUsuario);

            delete.execute();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
