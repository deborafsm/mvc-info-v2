/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Cliente;
import java.awt.HeadlessException;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean deleteClient(Cliente cliente) {
        String sql = "DELETE FROM tb_clientes WHERE id_cliente = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.executeUpdate();
            return true;
        } catch (HeadlessException | SQLException e) {
            System.out.println("Erro> " + e);
            return false;
        } finally {
            ConnectionFactory.closeConection(con, ps);
        }
    }

    public java.util.List<Cliente> readCliente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.util.List<Cliente> cli = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM tb_clientes");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setEnderecoCliente(rs.getString("end_cliente"));
                cliente.setTelefoneCliente(rs.getString("fone_cliente"));
                cli.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);//mOSTRA o erro
        } finally {
            ConnectionFactory.closeConection(con, ps, rs);
        }
        return cli;
    }

}
