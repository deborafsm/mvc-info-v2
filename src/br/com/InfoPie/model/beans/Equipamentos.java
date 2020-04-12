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
public class Equipamentos {

    private int id_equipamento;
    private String marca;
    private String modelo;
    private int numeroSerie;
    private String sistemaOp;
    private String ano;
    private String defeito;
    private String observacao;
    private String tipo;
    private Cliente cliente;

    public Equipamentos() {
    }

    public Equipamentos(String marca, String modelo, int numeroSerie, String sistemaOp, String ano, String defeito, String observacao, String tipo, Cliente cliente) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.sistemaOp = sistemaOp;
        this.ano = ano;
        this.defeito = defeito;
        this.observacao = observacao;
        this.tipo = tipo;
        this.cliente = cliente;
    }
    
  

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_equipamento() {
        return id_equipamento;
    }

    public void setId_equipamento(int id_equipamento) {
        this.id_equipamento = id_equipamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

   
    public String getSistemaOp() {
        return sistemaOp;
    }

    public void setSistemaOp(String sistemaOp) {
        this.sistemaOp = sistemaOp;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
