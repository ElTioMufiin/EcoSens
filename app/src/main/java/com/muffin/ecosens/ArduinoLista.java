package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ArduinoLista extends AppCompatActivity {

    //Vista Tabla
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_lista);


        //Llamar funcion Cargar datos
        ArduinoController.cargarArrayArduino();
        AdaptadorArduino adapter = new AdaptadorArduino(this);
        listView = findViewById(R.id.ArduinoList);
        listView.setAdapter(adapter);


//  TODO: Implementar Vista Detalle Arduinos
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
//                Intent in = new Intent(Arduino.this,Detalle.class);
//                in.putExtra("ID",i);
//                startActivity(in);
//            }
//        });

    }

    //Clase mostrar datos de manera ordenada
    class AdaptadorArduino extends ArrayAdapter<Arduino> {
        final AppCompatActivity appCompatActivity;

        public AdaptadorArduino(AppCompatActivity context) {
            super(context, R.layout.activity_arduino, ArduinoController.getListaArduino());
            appCompatActivity = context;
        }
        public View getView(int i, View convertView, ViewGroup parent) {

            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.activity_arduino, null);
            //Mostrar datos MinDB y MaxDB
            TextView tv1 = item.findViewById(R.id.ArduinoNro);
            TextView tv2 = item.findViewById(R.id.ArduinoIp);
            TextView tv3 = item.findViewById(R.id.ArduinoNombre);
            TextView tv4 = item.findViewById(R.id.ArduinoEstado);


            tv1.setText("Dispositivo " + ArduinoController.getListaArduino().get(i).getId() + ":");
            tv2.setText("Ip : " + ArduinoController.getListaArduino().get(i).getIp());
            tv3.setText("Fecha : " + ArduinoController.getListaArduino().get(i).getNombre());
            tv4.setText("Estado :" + ArduinoController.getListaArduino().get(i).getEstado());

            return (item);
        }

    }
}
