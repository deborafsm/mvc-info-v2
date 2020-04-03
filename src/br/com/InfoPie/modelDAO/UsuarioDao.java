/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    Usuarios usuario = new Usuarios();
    //Adiciona Usuario no sistema
    public void InsertUser(Usuarios usuarios) {

        String sql = "INSERT INTO tb_usuarios(usuario,fone,login,senha,perfil) VALUES(?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuarios.getNomeUsuario());
            pst.setString(2, usuarios.getTelefoneUsuario());
            pst.setString(3, usuarios.getLogin());
            pst.setString(4, usuarios.getSenha());
            pst.setString(5, usuarios.getPerfil());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar usuario no sistema" + e);
        } finally {
            ConnectionFactory.closeConection(con, pst);
        }
    }
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
