package com.muffin.ecosens;
public class Audio {
    private String id;

    private Float DB;

    private String Ubicacion;

    private String fecha;

    private String hora;

    public Audio() {
    }

    public Audio(String id, Float DB, String ubicacion, String fecha, String hora) {
        this.id = id;
        this.DB = DB;
        Ubicacion = ubicacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getDB() {
        return DB;
    }

    public void setDB(Float DB) {
        this.DB = DB;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
