/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.model.beans;

/**
 *
 * @author DeboraDev
 */
public class OrdemServico extends Equipamentos {

    private int id_os;
    private String situacao;
    private String defeito;
    private String servico;
    private String tecnico;
    private double valor;
    private Cliente cliente;
    private Equipamentos equipamentos;

    public OrdemServico() {
    }

    public OrdemServico(int id_os, String situacao, String defeito, String servico, String tecnico, double valor, Cliente cliente) {
        this.id_os = id_os;
        this.situacao = situacao;
        this.defeito = defeito;
        this.servico = servico;
        this.tecnico = tecnico;
        this.valor = valor;
        this.cliente = cliente;
    }

    public OrdemServico(String situacao, String defeito, String servico, String tecnico, double valor, Cliente cliente, Equipamentos equipamentos) {
        this.situacao = situacao;
        this.defeito = defeito;
        this.servico = servico;
        this.tecnico = tecnico;
        this.valor = valor;
        this.cliente = cliente;
        this.equipamentos = equipamentos;
    }
    

    public Equipamentos getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Equipamentos equipamentos) {
        this.equipamentos = equipamentos;
    }
    

    public int getId_os() {
        return id_os;
    }

    public void setId_os(int id_os) {
        this.id_os = id_os;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getIdOrdemServico() {
        return id_os;
    }

    public void setIdOrdemServico(int id_os) {
        this.id_os = id_os;
    }


    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
