/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.model.beans;

import java.util.List;

/**
 *
 * @author DeboraDev
 */
public class Cliente {

    private int id;
    private String nomeCliente;
    private String enderecoCliente;
    private String telefoneCliente;
    private String emailCliente;
    private String cidade;
    private String uf;
    private String cep;
    private String estado;

    public Cliente() {
    }

    public Cliente(String nomeCliente, String enderecoCliente, String telefoneCliente, String emailCliente, String cidade, String uf, String cep, String estado) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.estado = estado;
    }

    public Cliente(int id, String nomeCliente, String enderecoCliente, String telefoneCliente, String emailCliente, String cidade, String uf, String cep) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public Cliente(String nomeCliente, String enderecoCliente, String telefoneCliente, String emailCliente, String cidade, String uf, String cep) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void add(List<Cliente> cli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
