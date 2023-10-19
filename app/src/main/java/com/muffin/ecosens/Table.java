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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Table extends AppCompatActivity {

    //Vista Tabla
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        //Llamar funcion Cargar datos
        MedicionPorMinutoController.cargarArrayMedicion();
        AdaptadorMedicion adapter = new AdaptadorMedicion(this);
        listView = findViewById(R.id.listData);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent in = new Intent(Table.this,Detalle.class);
                in.putExtra("ID",i);
                startActivity(in);
            }
        });

    }

//Clase mostrar datos de manera ordenada
    class AdaptadorMedicion extends ArrayAdapter<MedicionPorMinuto> {
        final AppCompatActivity appCompatActivity;

            public AdaptadorMedicion(AppCompatActivity context) {
                super(context, R.layout.data, MedicionPorMinutoController.getListado());
                appCompatActivity = context;
            }
            public View getView(int i, View convertView, ViewGroup parent) {

                LayoutInflater inflater = appCompatActivity.getLayoutInflater();
                View item = inflater.inflate(R.layout.data, null);
                //Mostrar datos MinDB y MaxDB
                TextView tv1 = item.findViewById(R.id.listData);
                TextView tv2 = item.findViewById(R.id.data2);
                TextView tv3 = item.findViewById(R.id.data3);
                TextView tv4 = item.findViewById(R.id.data4);

                if (MedicionPorMinutoController.getListado().get(i).getConcentracionMonoxidoCarbono() > 50) {
                    item.setBackgroundColor(Color.RED);
                }if (MedicionPorMinutoController.getListado().get(i).getConcentracionMonoxidoCarbono() < 50){
                    item.setBackgroundColor(Color.GREEN);
                }

                tv1.setText("Medicion " + MedicionPorMinutoController.getListado().get(i).getId() + ":");
                tv2.setText("Hora : " + MedicionPorMinutoController.getListado().get(i).getHora());
                tv3.setText("Fecha : " + MedicionPorMinutoController.getListado().get(i).getFecha());
                tv4.setText("Concentracion Monoxido Carbono : " + MedicionPorMinutoController.getListado().get(i).getConcentracionMonoxidoCarbono() + " ppm");
                tv4.setText("Concentracion Monoxido Carbono : " + MedicionPorMinutoController.getListado().get(i).getConcentracionMonoxidoCarbono() + " ppm");


                return (item);
            }

    }
}

