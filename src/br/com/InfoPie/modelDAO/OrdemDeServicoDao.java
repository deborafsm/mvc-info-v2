/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Equipamentos;
import br.com.InfoPie.model.beans.OrdemServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DeboraDev
 */
public class OrdemDeServicoDao {

    private Connection con = null;

    public OrdemDeServicoDao() {
        con = ConnectionFactory.getConnection();
    }

    public List<OrdemServico> findAll() {
        //Query para selecionar toda a tabela = tb_categoria
        String sql = "select ordemdeservico.tecnico, ordemdeservico.servico,ordemdeservico.situacao,ordemdeservico.valor, equipamento.defeito,equipamento.marca,equipamento.tipo\n"
                + "from ordemdeservico \n"
                + "inner join equipamento on ordemdeservico.id_os = equipamento.id_os;";
        //Prepara os parametros para ser exibido de forma segura
        PreparedStatement ps = null;
        //Resultado da execução de consulta sql
        ResultSet rs = null;
        //Cria lista com o nome (cat) para adicionar os dados do banco de dados
        List<OrdemServico> osList = new ArrayList<>();
        //para operações com o banco usar o try
        try {
            /*chama o contexto de conexao para preparar os paramentros sql de 
            forma segura*/
 /*
            1º ps recebe a conexao pregara o parametro da query 
            2º rs executa a query preparada anteriormente
             */
            ps = con.prepareStatement(sql);
            //Executa a Query 
            rs = ps.executeQuery();
            //se tiver resultado ao (.next()) passar pelas linhas da coluna
            while (rs.next()) {

                OrdemServico os = new OrdemServico();
                // envie uma descricao atraves do rs(resultset) obtem a string de descricao (da coluna)

                os.setTecnico(rs.getString("tecnico"));
                os.setServico(rs.getString("servico"));
                os.setSituacao(rs.getString("situacao"));
                os.setValor(rs.getDouble("valor"));

                Equipamentos eq = new Equipamentos();
                eq.setDefeito(rs.getString("defeito"));
                eq.setTipo(rs.getString("tipo"));
                eq.setMarca(rs.getString("marca"));
                os.setEquipamentos(eq);
                //e add um objeto a categoria
                osList.add(os);
            }
            //Erro
        } catch (Exception e) {
            System.out.println("Erro " + e);//mOSTRA o erro
            //No final
        } finally {
            //Fecha a conexão todas
            ConnectionFactory.closeConection(con, ps, rs);
        }
        //Retorne a lista ao final (cat)
        return osList;
    }
}
