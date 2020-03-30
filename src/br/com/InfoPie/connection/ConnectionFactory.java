/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DeboraDev
 */
public class ConnectionFactory {
    //Atributos && constantes 
    private static final String DRIVER = "com.mysql.jdbc.Driver"; //Constantes staticas drive
    private static final String URL = "jdbc:mysql://localhost:3306/bd_assistenciatecnica"; //Constantes staticas url
    private static final String USER = "root"; //Constantes staticas user
    private static final String PASS = ""; //Constantes staticas password

    //Pega conexão
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) { //Multicath para juntas as duas exceções 
            throw new RuntimeException("Erro na conexão: ", ex); //Mostra uma mensagem e a execeções 
        }
    }
    //Fechar conexao

    public static void closeConection(Connection conn) {
        if (conn != null) {
            try {
                //tem alguma coisa aberta
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Erro"+ex);

            }
        }
    }

    //fecha conexao STATMENT
    public static void closeConection(Connection conn, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                //tem alguma coisa aberta
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro"+ex);
            }
            closeConection(conn);
        }
    }
    
    //Fecha result set
    public static void closeConection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                //tem alguma coisa aberta
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro"+ex);

            }
            closeConection(conn, stmt);
        }

    }
}
