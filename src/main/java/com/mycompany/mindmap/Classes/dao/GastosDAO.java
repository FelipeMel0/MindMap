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
            String sql = "INSERT INTO gastos (saldo, despesas, idUsuario) VALUES (?, ?, ?)";
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement insert = conn.prepareStatement(sql);

            insert.setDouble(1, gasto.getSaldo());
            insert.setDouble(2, gasto.getDespesas());
            insert.setInt(3, gasto.getIdUsuario());

            insert.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Gastos selecionarGastosPorId(int idUsuario) {

        Gastos gasto = null;

        String sql = "SELECT * FROM gastos WHERE idUsuario = ?";

        try {

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);

            consulta.setInt(1, idUsuario);

            ResultSet rs = consulta.executeQuery();

            if (rs.next() == true) {
                int idGastos = Integer.parseInt(rs.getString("idGastos"));
                Double saldo = rs.getDouble("saldo");
                Double despesas = rs.getDouble("despesas");
                int idUsuarioLogado = Integer.parseInt(rs.getString("idUsuario"));

                gasto = new Gastos(idGastos, saldo, despesas, idUsuarioLogado);

            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return gasto;

    }

    public Gastos selecionarGastosPorIdGastos(int idGastos) {

        Gastos gasto = null;

        String sql = "SELECT * FROM gastos WHERE idGastos = ?";

        try {

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement consulta = conn.prepareStatement(sql);

            consulta.setInt(1, idGastos);

            ResultSet rs = consulta.executeQuery();

            if (rs.next() == true) {
                int idGastosSelecionado = Integer.parseInt(rs.getString("idGastos"));
                Double saldo = rs.getDouble("saldo");
                Double despesas = rs.getDouble("despesas");
                int idUsuarioLogado = Integer.parseInt(rs.getString("idUsuario"));

                gasto = new Gastos(idGastosSelecionado, saldo, despesas, idUsuarioLogado);

            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return gasto;

    }

    public void editarSaldoGastos(Gastos gasto) {

        try {
            String sql = "UPDATE gastos SET saldo = ? WHERE idGastos = ?";

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement update = conn.prepareStatement(sql);

            update.setDouble(1, gasto.getSaldo());
            update.setInt(2, gasto.getIdGastos());

            update.execute();

        } catch (Exception e) {

            System.out.println("Erro na edição: " + e.getMessage());

        }

    }

    public void editarDespesasGastos(Gastos gasto) {

        try {
            String sql = "UPDATE gastos SET despesas = ? WHERE idGastos = ?";

            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement update = conn.prepareStatement(sql);

            update.setDouble(1, gasto.getDespesas());
            update.setInt(2, gasto.getIdGastos());

            update.execute();

        } catch (Exception e) {

            System.out.println("Erro na edição: " + e.getMessage());

        }

    }

    public void excluirGastosPorIdUsuario(int idUsuario) {

        String sql = "DELETE FROM gastos WHERE idUsuario = ?";
        try {
            Connection conn = ConexaoBD.obtemConexao();
            PreparedStatement delete = conn.prepareStatement(sql);

            delete.setInt(1, idUsuario);
            delete.execute();
                    
        } catch (Exception e) {
            System.out.println("Erro na exclusão: " + e.getMessage());
        }

    }

}
