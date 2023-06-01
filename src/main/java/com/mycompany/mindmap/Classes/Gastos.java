package com.mycompany.mindmap.Classes;

public class Gastos {
    
    Double saldo;
    Double despesas;

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
