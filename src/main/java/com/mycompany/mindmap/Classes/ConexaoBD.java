package com.mycompany.mindmap.Classes;

import java.sql.*;

public class ConexaoBD {

    private static String host = "localhost";
    private static String porta = "3306";
    private static String db = "dbmindmap";
    private static String usuario = "root";
    private static String senha = "LegoBatman2*";

    public static Connection obtemConexao() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + db + "?useTimezone=true&serverTimezone=UTC" , usuario, senha);
            return c;
        } catch (SQLException e) {
            return null;
        }

    }
}
