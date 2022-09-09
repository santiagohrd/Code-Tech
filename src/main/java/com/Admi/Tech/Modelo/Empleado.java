package com.Admi.Tech.Modelo;

public class Empleado {
    private int id;
    private String full_name;
    private String email;
    private String rol;
    private String empresa;

    public Empleados() {
    }

    public Empleados(int id, String full_name, String email, String rol, String empresa) {
      this.id = id;
      this.full_name = full_name;
      this.email = email;
      this.rol = rol;
      this.empresa = empresa;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getempresa() {
        return empresa;
    }

    public void setempresa(String empresa) {
        this.empresa = empresa;
    }
}

}
