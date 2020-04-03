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
public class Usuarios {
    private int idUser ;
    private String nomeUsuario;
    private String telefoneUsuario;
    private String login;
    private String senha;
    private String perfil;
    private OrdemServico ordemServico;
    

    
    
    public Usuarios() {
    }

    public Usuarios(String nomeUsuario, String telefoneUsuario, String login, String senha, String perfil, OrdemServico ordemServico) {
        this.nomeUsuario = nomeUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.ordemServico = ordemServico;
    }
    
    

    public Usuarios(int idUser, String nomeUsuario, String telefoneUsuario, String login, String senha, String perfil) {
        this.idUser = idUser;
        this.nomeUsuario = nomeUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Usuarios(String nomeUsuario, String telefoneUsuario, String login, String senha, String perfil) {
        this.nomeUsuario = nomeUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }
    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    @Override
    public String toString() {
        return getPerfil(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

