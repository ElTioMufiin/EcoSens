package com.muffin.ecosens;


import android.content.Context;
import java.util.ArrayList;

public class MedicionPorMinutoController {

    //Crear Lista
    private static ArrayList<MedicionPorMinuto> listaMedicion = new ArrayList<>();

    //CRUD

    //Crear
    public static void addMedicion(int id, String hora,
                                   String fecha,
                                   String lugarCoordenadas,
                                   String concentracionMP10,
                                   double concentracionMP2_5,
                                   String concentracionMonoxidoCarbono,
                                   double concentracionOzono,
                                   double concentracionDioxidoAzufre,
                                   double concentracionNitrogeno,
                                   double concentracionPlomo,
                                   Context context){
        MedicionPorMinuto m = new MedicionPorMinuto(id,hora,fecha,lugarCoordenadas,concentracionMP10,concentracionMP2_5,concentracionMonoxidoCarbono,concentracionOzono,concentracionDioxidoAzufre,concentracionNitrogeno,concentracionPlomo);
        listaMedicion.add(m);
    }


    //Leer
    public static MedicionPorMinuto findMedicion(int id){
        for (MedicionPorMinuto m : listaMedicion){
            if (id == m.getId()){
                return m;
            }
        }
        return null;
    }
    public static ArrayList<MedicionPorMinuto> getListado(){
        return listaMedicion;
    }
//    public static void cargarArrayMedicion(){
//        if(listaMedicion.size()==0){
//            addMedicion(1,
//                    "15:30",
//                    "2023-10-18",
//                    "40.7128° N, 74.0060° W",
//                    18.5,
//                    9.2,
//                    1.7,
//                    0.03,
//                    0.8,
//                    4.1,
//                    0.01,
//                    null);
//            addMedicion(    2,
//                    "12:45",
//                    "2023-10-19",
//                    "34.0522° N, 118.2437° W",
//                    12.8,
//                    6.3,
//                    2.2,
//                    0.05,
//                    0.6,
//                    3.7,
//                    0.02,
//                    null);
//            addMedicion(    3,
//                    "12:45",
//                    "2023-10-19",
//                    "33.7°S 70.5°W",
//                    12.8,
//                    6.3,
//                    55.5,
//                    0.05,
//                    0.6,
//                    3.7,
//                    0.02,
//                    null);
//        }
//    }




}
