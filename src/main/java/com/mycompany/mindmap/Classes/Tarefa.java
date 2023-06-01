package com.mycompany.mindmap.Classes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa {
    
    private String titulo;
    private String statusConclusao;
    private String descricao;
    private Date dataCriacao;
    private Time horaCriacao;
    private Date dataConclusao;
    private Time horaConclusao;
    
    public Tarefa(String titulo, String statusConclusao, String descricao, Date dataCriacao, Time horaCriacao, Date dataConclusao, Time horaConclusao){
        this.titulo = titulo;
        this.statusConclusao = statusConclusao;
        this.descricao = descricao;
        this.dataCriacao = Date.valueOf(LocalDate.now()); //2023-06-01 (1° de junho de 2023 - 01/06/2023)
        this.horaCriacao = Time.valueOf(LocalTime.now()); //09:25:04 (hora:minuto:segundo)
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Time getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(Time horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Time getHoraConclusao() {
        return horaConclusao;
    }

    public void setHoraConclusao(Time horaConclusao) {
        this.horaConclusao = horaConclusao;
    }
    
    
    
}
