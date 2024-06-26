package com.muffin.ecosens;

import java.util.ArrayList;

public class DataController {

    //Crear Lista
    private static ArrayList<Data> listaData = new ArrayList<>();

    //Crear datos
    public static void addData(int id, Float minDB, Float maxDB, Float avgDB, Float airQ, Float co2) {
            Data d = new Data(id, minDB, maxDB, avgDB, airQ, co2);
            listaData.add(d);
    }

    //Leer datos (find)
    public static Data findData(int id){
        for (Data d : listaData){
            if (id == d.getIdData()){
                return d;
            }
        }
        return null;
    }

    public static ArrayList<Data> getListado(){
        return listaData;
    }
    //Cargar Datos a la lista si esta vacia
    public static void cargarData(){
        if (listaData.size()==0){
            addData(1, 45.0F,70.0f, 60.0F,70.0f,12.0f);
            addData(1,45.0f,85.0f,60.0f,70.0f,12.0f);
        }
    }

}



