package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Arduino{
    private int Id;
    private String Ip;
    private String Estado;
    private String Nombre;
    private String Ubicacion;

    public Arduino() {
    }

    public Arduino(int id, String ip, String estado, String nombre, String ubicacion) {
        Id = id;
        Ip = ip;
        Estado = estado;
        Nombre = nombre;
        Ubicacion = ubicacion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return getNombre()+" "+getIp();
    }
}