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

    //Esse método insere um cliente no banco de dados
    public void insertClient(Cliente cliente) {
        //query para adicionar cliente
        String sql = "INSERT INTO clientes(nome,telefone,email,endereco)VALUES(?,?,?,?)";
        PreparedStatement ps = null; //Prepara os parametros de forma mais segura
        try { //tenta fazer a logica abaixo
            ps = con.prepareStatement(sql); //preparada os parametros de forma segura usando a query de inserção
            //Aciona o objeto nas posições que foram colocadas na query (sql)
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getTelefoneCliente());
            ps.setString(3, cliente.getEmailCliente());
            ps.setString(4, cliente.getEnderecoCliente());
            
           //Executa instrução insert
            ps.executeUpdate();
            //Mostra uma mensagem caso a logica estiver  correta
            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso");
        } catch (Exception e) { //Senão mostra a mensage e o erro
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente" + e);
        } finally {//No final fecha as conexões utilizadas 
            ConnectionFactory.closeConection(con, ps);
        }
    }

    //Esse método exclui um cliente do banco de dados
    public void deleteClient(Cliente cliente) {
        //query deleta cliente de acordo com o id
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        PreparedStatement ps = null;
        try {//tenta fazer a logica abaixo
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId()); //pega o id do cliente
            ps.executeUpdate();//Executa a query
            JOptionPane.showMessageDialog(null, "Excluido com sucesso"); //mensagem informando sucesso
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente" + e);//mensagem informando falha e o erro causado
        } finally {
            ConnectionFactory.closeConection(con, ps); //fecha as conexoes utilizadas
        }
    }

    //Esse método cria de um array seleciona os objetos da tabela
    public java.util.List<Cliente> readCliente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.util.List<Cliente> cli = new ArrayList<>(); //Array de clientes
        try {
            ps = con.prepareStatement("SELECT * FROM clientes"); //Seleciona tdo de clientes
            rs = ps.executeQuery(); //Result set para se obter o resultado
            while (rs.next()) {//Enquando tiver resultado (linhas)
                Cliente cliente = new Cliente();
                //Lista os componentes
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome"));
                cliente.setTelefoneCliente(rs.getString("telefone"));
                cliente.setEmailCliente(rs.getString("email"));
                cliente.setEnderecoCliente(rs.getString("endereco"));
                //E adiciona no array list
                cli.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);//Mostra o erro da logica, ja que só mostra algum resultado
        } finally {
            ConnectionFactory.closeConection(con, ps, rs);
        }
        //Retora o array 
        return cli;
    }
    //Metodo que edita o cliente do banco de dados
    public void updateCliente(Cliente cliente) {//Query de atualizar cliente
        PreparedStatement ps = null;
        try {//tenta a logica abaixo
            ps = con.prepareStatement("UPDATE clientes SET nome = ? , telefone = ?,email = ?, endereco = ?  WHERE id_cliente = ? ");
            //Permissão para atualizar apenas os componentes abaixo
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getTelefoneCliente());
            ps.setString(3, cliente.getEmailCliente());
            ps.setString(4, cliente.getEnderecoCliente());
            ps.setInt(5, cliente.getId());
            //Executando a instrução sql
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");//Mostra a mensagem ao usuario de sucesso 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + e); //Mostra a mensagem de falha
        } finally {//Fecha as conexões
            ConnectionFactory.closeConection(con, ps);
        }
    }
    //Esse metodo vai uma pesquisa de clientes por nome 
     public java.util.List<Cliente> findeClient(String nome ) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Array lista adicionada
        java.util.List<Cliente> cliFinder = new ArrayList<>(); //Array de clientes
        try {   //seleciona por nome do cliente 
            ps = con.prepareStatement("SELECT * FROM clientes WHERE nome like ?"); //Seleciona tdo de clientes
            ps.setString(1, "%"+nome+"%");// ? = nome
            rs = ps.executeQuery(); //Result set para se obter o resultado
            while (rs.next()) {//Enquando tiver resultado (linhas)
                Cliente cliente = new Cliente();
                //Lista os componentes
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome"));
                cliente.setTelefoneCliente(rs.getString("telefone"));
                cliente.setEmailCliente(rs.getString("email"));
                cliente.setEnderecoCliente(rs.getString("endereco"));
                
                //E adiciona no array list
                cliFinder.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);//Mostra o erro da logica, ja que só mostra algum resultado
        } finally {
            ConnectionFactory.closeConection(con, ps, rs);
        }
        //Retora o array 
        return cliFinder;
    }
}
