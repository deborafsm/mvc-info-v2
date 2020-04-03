/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DeboraDev
 */
public class LoginDao {
    public boolean logar(String login, String senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean logar = false;
        try {
            ps = con.prepareStatement("SELECT * FROM  tb_usuarios WHERE login = ? AND senha = ?");
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (rs.next()) {
                logar = true;
            }
        } catch (SQLException e) {
            System.out.println("erro: " + e);
        } finally {
            ConnectionFactory.closeConection(con, ps, rs);
        }
        return logar;
    }
}
