package com.muffin.ecosens;
//Clase Datos
public class Data {
    private int idData;
    private Float MinDB;
    private Float MaxDB;
    private Float AvgDB;
    private Float AirQ;
    private Float Co2;

    //Constructor
    public Data(int idData, Float minDB, Float maxDB, Float avgDB, Float airQ, Float co2) {
        this.idData = idData;
        this.MinDB = minDB;
        this.MaxDB = maxDB;
        this.AvgDB = avgDB;
        this.AirQ = airQ;
        this.Co2 = co2;
    }
    //Getter y Setter
    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public Float getMinDB() {
        return MinDB;
    }

    public void setMinDB(Float minDB) {
        MinDB = minDB;
    }

    public Float getMaxDB() {
        return MaxDB;
    }

    public void setMaxDB(Float maxDB) {
        MaxDB = maxDB;
    }

    public Float getAvgDB() {
        return AvgDB;
    }

    public void setAvgDB(Float avgDB) {
        AvgDB = avgDB;
    }

    public Float getAirQ() {
        return AirQ;
    }

    public void setAirQ(Float airQ) {
        AirQ = airQ;
    }

    public Float getCo2() {
        return Co2;
    }

    public void setCo2(Float co2) {
        Co2 = co2;
    }
}
