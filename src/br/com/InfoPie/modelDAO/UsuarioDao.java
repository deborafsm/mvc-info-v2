/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Usuarios;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        String sql = "INSERT INTO tb_usuarios(nome_usuario,login,senha,fone,perfil) VALUES(?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuarios.getNomeUsuario());
            pst.setString(2, usuarios.getLogin());
            pst.setString(3, usuarios.getSenha());
            pst.setString(4, usuarios.getTelefoneUsuario());
            pst.setString(5, usuarios.getPerfil());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar usuario no sistema" + e);
        } finally {
            ConnectionFactory.closeConection(con, pst);
        }
    }

    //Deleta usuario do banco de dados
    public void deleteUser(Usuarios usuario) {

        String sql = "DELETE FROM tb_usuarios WHERE id_user=?"; //query deleta usuario atraves do id
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);//le a query
            pst.setInt(1, usuario.getIdUser());//pega o id
            pst.executeUpdate();//Executa e atualiza query
            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");//mensagem de sucesso
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover usuario!" + e);//,msg de falha
        } finally {
            ConnectionFactory.closeConection(con, pst);//fecha a conexao
        }

    }

    //Retorna uma lista
    public java.util.List<Usuarios> readUsuarios() {//seleciona usuarios
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Cria uma array com nome user
        java.util.List<Usuarios> user = new ArrayList<>(); //Array de usuarioss
        try {
            //executa com segurança o select
            ps = con.prepareStatement("SELECT * FROM tb_usuarios"); //Seleciona tdo de tb_usuarios
            rs = ps.executeQuery(); //Result set para se obter o resultado
            while (rs.next()) {//Enquando tiver resultado (linhas)
                Usuarios usuarios = new Usuarios();
                //Lista os componentes que vao ser setados
                usuarios.setIdUser(rs.getInt("id_user"));
                usuarios.setNomeUsuario(rs.getString("nome_usuario"));
                usuarios.setLogin(rs.getString("login"));
                usuarios.setSenha(rs.getString("senha"));
                usuarios.setTelefoneUsuario(rs.getString("fone"));
                usuarios.setPerfil(rs.getString("perfil"));

                //user.add adiciona os usuarios no array list
                user.add(usuarios);
            }
        } catch (Exception e) {
            System.out.println("Erro ao apresentar dados na tabela" + e);//Mostra o erro da logica, ja que só mostra algum resultado
        } finally {
            ConnectionFactory.closeConection(con, ps, rs);//Fechas as conexoes usadas
        }
        //Retora o array 
        return user;
    }

    //Atualiza Usuario
    public void updateUser(Usuarios usuario) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE tb_usuarios SET nome_usuario = ? , login = ? , senha = ?, fone = ?,perfil =? WHERE id_user = ?");
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefoneUsuario());
            //Erro perfil por que se trata de um combo box
            ps.setString(5, usuario.getPerfil());
            ps.setInt(6, usuario.getIdUser());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario editado com sucesso!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel atualizar o usuario." + e);
        } finally {
            ConnectionFactory.closeConection(con, ps);
        }
    }
}
