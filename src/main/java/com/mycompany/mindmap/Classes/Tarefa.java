package com.mycompany.mindmap.Classes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa {
    
    private int idTarefa;
    private String titulo;
    private String statusConclusao;
    private String descricao;
    private String dataCriacao;
    private String horaCriacao;
    private String dataConclusao;
    private String horaConclusao;
    
    private int idUsuario;
    private int idAba;

    public Tarefa(String titulo, String statusConclusao, String descricao, String dataCriacao, String horaCriacao, String dataConclusao, String horaConclusao) {
        this.titulo = titulo;
        this.statusConclusao = statusConclusao;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.horaCriacao = horaCriacao;
        this.dataConclusao = dataConclusao;
        this.horaConclusao = horaConclusao;
    }     

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }   

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdAba() {
        return idAba;
    }

    public void setIdAba(int idAba) {
        this.idAba = idAba;
    }

    public Tarefa(int idTarefa, String titulo, String statusConclusao, String descricao, String dataConclusao, String horaConclusao) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.statusConclusao = statusConclusao;
        this.descricao = descricao;
        this.dataConclusao = dataConclusao;
        this.horaConclusao = horaConclusao;
    }
    
    

    public Tarefa(String titulo, String statusConclusao, String descricao, String dataCriacao, String horaCriacao, String dataConclusao, String horaConclusao, int idUsuario, int idAba) {
        this.titulo = titulo;
        this.statusConclusao = statusConclusao;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.horaCriacao = horaCriacao;
        this.dataConclusao = dataConclusao;
        this.horaConclusao = horaConclusao;
        this.idUsuario = idUsuario;
        this.idAba = idAba;
    }

    public Tarefa(String titulo, String statusConclusao, String descricao, String dataConclusao, String horaConclusao) {
        this.titulo = titulo;
        this.statusConclusao = statusConclusao;
        this.descricao = descricao;
        this.dataConclusao = dataConclusao;
        this.horaConclusao = horaConclusao;
    }

    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatusConclusao() {
        return statusConclusao;
    }

    public void setStatusConclusao(String statusConclusao) {
        this.statusConclusao = statusConclusao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(String horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getHoraConclusao() {
        return horaConclusao;
    }

    public void setHoraConclusao(String horaConclusao) {
        this.horaConclusao = horaConclusao;
    }

   
    
    
    
}
