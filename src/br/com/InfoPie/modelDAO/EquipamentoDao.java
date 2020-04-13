/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Equipamentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author DeboraDev
 */
public class EquipamentoDao {
    private Connection con = null;

    public EquipamentoDao() {
        con = ConnectionFactory.getConnection();
    }
    
    //Inserir dados do equipamento no banco de dados
    public void insertEquipamento(Equipamentos equipamento){
        String sql = "INSERT INTO equipamento(marca,modelo,numero_serie,sistema_op,defeito,observacao,tipo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,equipamento.getMarca());
            ps.setString(2,equipamento.getModelo());
            ps.setString(3,equipamento.getNumeroSerie());
            ps.setString(4,equipamento.getSistemaOp());
            ps.setString(5, equipamento.getDefeito());
            ps.setString(6, equipamento.getObservacao());
            ps.setString(7, equipamento.getTipo());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Equipamento Adicionado com sucesso ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar dados do equipamento no sistema: "+e);
        }finally{
            ConnectionFactory.closeConection(con, ps);
        }
    }
}
