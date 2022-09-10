package com.Admi.Tech.Modelo;


public class MovimientoDinero {
    private int id;
    private String concepto;
    private int cantidad;
    private String user;

    public MovimientoDinero(int id, String concepto, int cantidad, String user){
        this.id = id;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
