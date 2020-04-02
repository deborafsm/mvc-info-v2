/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Usuarios;
import br.com.InfoPie.view.TelaPrincipal;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DeboraDev
 */
public class UsuarioDao {

    Usuarios usuario = new Usuarios();

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

    /*public boolean consultarUsuario() {
        String sql = "select * from tb_usuarios where id_user =?";
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getIdUser());
            rs = ps.executeQuery();

            if (rs.next()) {
                txtUsuarioNome.setText(rs.getString(2));
                txtUsuarioFone.setText(rs.getString(3));
                txtUsuarioLogin.setText(rs.getString(4));
                txtUsuarioPass.setText(rs.getString(5));
                //Combo Box 
                cboUsuarioPerfil.setSelectedItem(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                txtUsuarioNome.setText(null);
                txtUsuarioFone.setText(null);
                txtUsuarioLogin.setText(null);
                txtUsuarioPass.setText(null);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }*/
}

