package com.muffin.ecosens;

import java.util.ArrayList;

public class ArduinoController {
    private static ArrayList<Arduino> listaArduino = new ArrayList<>();

    public static Arduino addArduino(String id, String ip, String estado, String nombre, String ubicacion){
        Arduino a = new Arduino(id,ip,estado,nombre,ubicacion);
        listaArduino.add(a);
        return a;
    }
    public static Arduino findArduino(String id){
        for (Arduino a : listaArduino){
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }

    public static ArrayList<Arduino> getListaArduino(){return listaArduino;}

}