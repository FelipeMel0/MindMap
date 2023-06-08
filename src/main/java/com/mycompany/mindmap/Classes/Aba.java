package com.mycompany.mindmap.Classes;

public class Aba {

    private int idAba;
    private String titulo;
    private int idUsuario;

    public Aba(String titulo, int idUsuario) {
        this.titulo = titulo;
        this.idUsuario = idUsuario;
    }
    
    public Aba(int idAba, String titulo){
        this.idAba = idAba;
        this.titulo = titulo;
    }

    public Aba(int idAba, String titulo, int idUsuario) {
        this.idAba = idAba;
        this.titulo = titulo;
        this.idUsuario = idUsuario;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
