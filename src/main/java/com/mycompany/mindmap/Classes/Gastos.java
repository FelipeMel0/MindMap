package com.mycompany.mindmap.Classes;

public class Gastos {
    
    int idGastos;
    Double saldo;
    Double despesas;
    int idUsuario;

    public Gastos(int idGastos, Double saldo, Double despesas, int idUsuario) {
        this.idGastos = idGastos;
        this.saldo = saldo;
        this.despesas = despesas;
        this.idUsuario = idUsuario;
    }   
    
    public int getIdGastos() {
        return idGastos;
    }

    public void setIdGastos(int idGastos) {
        this.idGastos = idGastos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getDespesas() {
        return despesas;
    }

    public void setDespesas(Double despesas) {
        this.despesas = despesas;
    }

    public Gastos(Double saldo, Double despesas) {
        this.saldo = saldo;
        this.despesas = despesas;
    }
       
}
