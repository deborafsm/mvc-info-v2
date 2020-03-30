/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Cliente;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DeboraDev
 */
public class ClienteDao {

    private Connection con = null;

    public ClienteDao() {
        con = ConnectionFactory.getConnection();
    }

    public boolean insertClient(Cliente cliente) {
        String sql = "INSERT INTO tb_clientes(nome_cliente,end_cliente,fone_cliente,email_cliente,"
                + "cidade,estado,uf,cep)VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            //ps.setInt(1, cliente.getId());
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getEnderecoCliente());
            ps.setString(3, cliente.getTelefoneCliente());
            ps.setString(4, cliente.getEmailCliente());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getEstado());
            ps.setString(7, cliente.getUf());
            ps.setString(8, cliente.getCep());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Erro> " + e);
            return false;
        } finally {
            ConnectionFactory.closeConection(con, ps);
        }
    }

    private boolean deleteClient(Cliente cliente) {
        //int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o Cliente ? ", "Atenção", JOptionPane.YES_NO_OPTION);
        //if (confirma == JOptionPane.YES_OPTION) {
        String sql = "delete from tb_clientes where id_cliente=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.executeUpdate();
            return true;
            //pst = conexao.prepareStatement(sql);
            //pst.setString(1, txtClienteId.getText());
            //int deletado = pst.executeUpdate();
            /*if (deletado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    txtClienteId.setText(null);
                    txtClienteNome.setText(null);
                    txtClienteFone.setText(null);
                    txtClienteEnd.setText(null);
                    txtClienteMail.setText(null);
                    btnClienteAdd.setEnabled(true);
                }*/
        } catch (HeadlessException | SQLException e) {
            System.out.println("Erro> " + e);
            return false;
        } finally {
            ConnectionFactory.closeConection(con, ps);
        }
    }

}
