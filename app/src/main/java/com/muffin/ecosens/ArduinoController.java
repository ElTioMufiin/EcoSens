package com.muffin.ecosens;

import java.util.ArrayList;

public class ArduinoController {
    private static ArrayList<Arduino> listaArduino = new ArrayList<>();

    public static void addArduino(int id, String ip, String estado, String nombre, String ubicacion){
        Arduino a = new Arduino(id,ip,estado,nombre,ubicacion);
        listaArduino.add(a);
    }
    public static Arduino findArduino(int id){
        for (Arduino a : listaArduino){
            if(id==a.getId()){
                return a;
            }
        }
        return null;
    }

    public static ArrayList<Arduino> getListaArduino(){return listaArduino;}

    public static void cargarArrayArduino(){
        if(listaArduino.size()==0){
            addArduino(1,"192.168.69.50","Activo","Ventana Comedor","33.7°S 70.5°W");
            addArduino(2,"192.168.69.51","Activo","Ventana Comedor","40.7128° N, 74.0060° W");
        }
}}