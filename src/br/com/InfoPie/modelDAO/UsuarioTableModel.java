/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.modelDAO;

import br.com.InfoPie.model.beans.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DeboraDev
 */
public class UsuarioTableModel extends AbstractTableModel {

    /*
    =====[1º]FASE ====
    1 Criar uma classe para herdar AbstracTableModel
    2Importar/criar os metodos getRow,getCOlumm,getValueAt.
    3Criar um lista para os usuarios 
    4 Especificar linhas e colunas 
    5 "dar vida" aos metodos
    5.1 getRowCount() return 
    5.2 getCollumnCount() return
    5.3 getValueAt()  swith return
    6 ctrl + espaço 
    6.1 getColumnName() return nome colunas
    (Sem o metodo acima as colunas sao nomeadas pelo sistema)
    =====[2º]FASE ====
    Add dados fireDAtaChanged
    1 Criar metodo para adicionar objetos na tabela
    Remover dados com fireTableRowsDeleted
    2 Criar metodo para remover objetos da tabela
     */
    private List<Usuarios> dados = new ArrayList<>();//Lista de usuarios com dados para colocar dentro da tabela
    private String[] colunas = {"id", "Nome Usuario", "Telefone", "Login", "Senha", "Perfil"};

    @Override//Especifica o nome de cada coluna
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {//Retorna o quant linha
        return dados.size();
    }

    @Override
    public int getColumnCount() {//qnt de colunas
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int colunas) {//Pega os valores da tabela
        switch (colunas) {
            case 0:
                return dados.get(linha).getIdUser();
            case 1:
                return dados.get(linha).getNomeUsuario();
            case 2:
                return dados.get(linha).getTelefoneUsuario();
            case 3:
                return dados.get(linha).getLogin();
            case 4:
                return dados.get(linha).getSenha();
            case 5:
                return dados.get(linha).getPerfil();

        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {

            case 0:
                dados.get(linha).setIdUser(Integer.parseInt((String) valor));
                break;
            case 1:
                dados.get(linha).setNomeUsuario((String) valor);
                break;
            case 2:
                dados.get(linha).setTelefoneUsuario((String) valor);
                break;
            case 3:
                dados.get(linha).setLogin((String) valor);
                break;
            case 4:
                dados.get(linha).setSenha((String) valor);
                break;
            case 5:
                dados.get(linha).setPerfil((String) valor);
                break;

        }
        this.fireTableRowsUpdated(linha, linha);//Atualiza
    }

    public void addRow(Usuarios usuario) {
        this.dados.add(usuario);//COloca o objeto usuario dentro da lista
        this.fireTableDataChanged(); //Adiciona
    }

    public void removeRow(int linha) {//Cria um indice/PArametro para remover dados da tabela
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);//Remove

    }

}
