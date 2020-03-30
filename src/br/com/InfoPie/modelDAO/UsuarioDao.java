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

    private Connection con = null;

    public UsuarioDao() {
        con = ConnectionFactory.getConnection();
    }

    //Logar
    public void logar(Usuarios usuario) {
        String sql = "select * from tb_usuarios where login=? and senha=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());

            rs = ps.executeQuery();
            if (rs.next()) {
                String perfil = rs.getString(6);
                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menuRelatorio.setEnabled(true);
                    TelaPrincipal.MenuCadUser.setEnabled(true);
                    TelaPrincipal.lblUser.setText(rs.getString(2));
                    TelaPrincipal.lblUser.setForeground(Color.red);
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.lblUser.setText(rs.getString(2));
                }
            } else {
                JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            ConnectionFactory.closeConection(con, ps, rs);
        }
    }
}
