package com.muffin.ecosens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Table extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //Vista Tabla
    public ListView listView;

    private final List<MedicionPorMinuto> listaMedPMin = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        iniciarFireBase();
        listarMedPMin();

        listView = findViewById(R.id.listData);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                MedicionPorMinuto m = listaMedPMin.get(i);
                Intent in = new Intent(Table.this,Detalle.class);
                in.putExtra("MedicionPorMinuto", (Parcelable) m);
                startActivity(in);
            }
        });

    }
    private void iniciarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void listarMedPMin(){
        databaseReference.child("MedicionesPorMinuto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaMedPMin.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    MedicionPorMinuto m = item.getValue(MedicionPorMinuto.class);
                    listaMedPMin.add(m);
                }
                AdaptadorMedicion adapter = new AdaptadorMedicion(Table.this,R.layout.data,listaMedPMin);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//Clase mostrar datos de manera ordenada
    class AdaptadorMedicion extends ArrayAdapter<MedicionPorMinuto> {

            public AdaptadorMedicion(Table context,int resource,List<MedicionPorMinuto> medicionPorMinuto) {
                super(context, resource, medicionPorMinuto);
            }
            public View getView(int i, View convertView,@NonNull ViewGroup parent) {

                View item = convertView;
                item = LayoutInflater.from(getContext()).inflate(R.layout.data,parent,false);
                MedicionPorMinuto currentMedicion = getItem(i);
                //Mostrar datos MinDB y MaxDB
                TextView tv1 = item.findViewById(R.id.listData);
                TextView tv2 = item.findViewById(R.id.data2);
                TextView tv3 = item.findViewById(R.id.data3);
                TextView tv4 = item.findViewById(R.id.data4);
                float car = Float.parseFloat(currentMedicion.getConcentracionMonoxidoCarbono());

                if (car > 50) {
                    tv4.setBackgroundColor(Color.RED);
                }if (car < 50){
                    tv4.setBackgroundColor(Color.GREEN);
                }

                tv1.setText("Medicion " +currentMedicion.getId() + ":");
                tv2.setText("Hora : " + currentMedicion.getHora());
                tv3.setText("Fecha : " + currentMedicion.getFecha());
                tv4.setText("Concentracion Monoxido Carbono : " + currentMedicion.getConcentracionMonoxidoCarbono() + " ppm");


                return (item);
            }
    }
}

