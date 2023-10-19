package com.muffin.ecosens;

import java.util.ArrayList;

public class AudioController {
    private static ArrayList<Audio> listaAudio = new ArrayList<>();

    public static void addAudio(int id,Float DB,String ubicacion, String fecha, String hora){
        Audio a = new Audio(id,DB,ubicacion,fecha,hora);
        listaAudio.add(a);
    }
    public static Audio findAudio(int id){
        for (Audio a : listaAudio){
            if(id==a.getId()){
                return a;
            }
        }
        return null;
    }

    public static ArrayList<Audio> getListaAudio(){return listaAudio;};

    public static void cargarAudio(){
        if(listaAudio.size()==0){
            addAudio(1,65.2F,"33.7°S 70.5°W","2023-10-19","12:45");
            addAudio(2,75.8F,"34.0522° N, 118.2437° W","2023-10-19","15:45");
            addAudio(3,30.0F,"40.7128° N, 74.0060° W","2023-10-18","02:25");
        }
    }
}
