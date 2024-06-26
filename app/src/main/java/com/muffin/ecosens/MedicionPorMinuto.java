package com.muffin.ecosens;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class MedicionPorMinuto implements Parcelable {
    private int id;
    private String hora;
    private String fecha;
    private String lugarCoordenadas;
    private String concentracionMP10;
    private String concentracionMP2_5;
    private String concentracionMonoxidoCarbono;
    private double concentracionOzono;
    private double concentracionDioxidoAzufre;
    private double concentracionNitrogeno;
    private double concentracionPlomo;

    private static final double UMBRAL_MP10 = 100.0; // Definir el umbral para MP10
    private static final double UMBRAL_MP2_5 = 50.0; // Definir el umbral para MP2.5
    private static final double UMBRAL_MONOXIDO = 5.0; // Definir el umbral para Monóxido de Carbono

    private static final String CHANNEL_ID = "Alertas";
    private static final int NOTIFICATION_ID = 1;


    public MedicionPorMinuto() {
    }

    public MedicionPorMinuto(int id, String hora, String fecha, String lugarCoordenadas, String concentracionMP10, String concentracionMP2_5, String concentracionMonoxidoCarbono, double concentracionOzono, double concentracionDioxidoAzufre, double concentracionNitrogeno, double concentracionPlomo) {
        this.id = id;
        this.hora = hora;
        this.fecha = fecha;
        this.lugarCoordenadas = lugarCoordenadas;
        this.concentracionMP10 = concentracionMP10;
        this.concentracionMP2_5 = concentracionMP2_5;
        this.concentracionMonoxidoCarbono = concentracionMonoxidoCarbono;
        this.concentracionOzono = concentracionOzono;
        this.concentracionDioxidoAzufre = concentracionDioxidoAzufre;
        this.concentracionNitrogeno = concentracionNitrogeno;
        this.concentracionPlomo = concentracionPlomo;
    }

    protected MedicionPorMinuto(Parcel in) {
        id = in.readInt();
        hora = in.readString();
        fecha = in.readString();
        lugarCoordenadas = in.readString();
        concentracionMP10 = in.readString();
        concentracionMP2_5 = in.readString();
        concentracionMonoxidoCarbono = in.readString();
        concentracionOzono = in.readDouble();
        concentracionDioxidoAzufre = in.readDouble();
        concentracionNitrogeno = in.readDouble();
        concentracionPlomo = in.readDouble();
    }

    public static final Creator<MedicionPorMinuto> CREATOR = new Creator<MedicionPorMinuto>() {
        @Override
        public MedicionPorMinuto createFromParcel(Parcel in) {
            return new MedicionPorMinuto(in);
        }

        @Override
        public MedicionPorMinuto[] newArray(int size) {
            return new MedicionPorMinuto[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugarCoordenadas() {
        return lugarCoordenadas;
    }

    public void setLugarCoordenadas(String lugarCoordenadas) {
        this.lugarCoordenadas = lugarCoordenadas;
    }

    public String getConcentracionMP10() {
        return concentracionMP10;
    }

    public void setConcentracionMP10(String concentracionMP10) {
        this.concentracionMP10 = concentracionMP10;
    }

    public String getConcentracionMP2_5() {
        return concentracionMP2_5;
    }

    public void setConcentracionMP2_5(String concentracionMP2_5) {
        this.concentracionMP2_5 = concentracionMP2_5;
    }

    public String getConcentracionMonoxidoCarbono() {
        return concentracionMonoxidoCarbono;
    }

    public void setConcentracionMonoxidoCarbono(String concentracionMonoxidoCarbono) {
        this.concentracionMonoxidoCarbono = concentracionMonoxidoCarbono;
    }

    public double getConcentracionOzono() {
        return concentracionOzono;
    }

    public void setConcentracionOzono(double concentracionOzono) {
        this.concentracionOzono = concentracionOzono;
    }

    public double getConcentracionDioxidoAzufre() {
        return concentracionDioxidoAzufre;
    }

    public void setConcentracionDioxidoAzufre(double concentracionDioxidoAzufre) {
        this.concentracionDioxidoAzufre = concentracionDioxidoAzufre;
    }

    public double getConcentracionNitrogeno() {
        return concentracionNitrogeno;
    }

    public void setConcentracionNitrogeno(double concentracionNitrogeno) {
        this.concentracionNitrogeno = concentracionNitrogeno;
    }

    public double getConcentracionPlomo() {
        return concentracionPlomo;
    }

    public void setConcentracionPlomo(double concentracionPlomo) {
        this.concentracionPlomo = concentracionPlomo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(hora);
        parcel.writeString(fecha);
        parcel.writeString(lugarCoordenadas);
        parcel.writeString(concentracionMP10);
        parcel.writeString(concentracionMP2_5);
        parcel.writeString(concentracionMonoxidoCarbono);
        parcel.writeDouble(concentracionOzono);
        parcel.writeDouble(concentracionDioxidoAzufre);
        parcel.writeDouble(concentracionNitrogeno);
        parcel.writeDouble(concentracionPlomo);
    }
}