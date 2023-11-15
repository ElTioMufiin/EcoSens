package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Detalle extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        iniciarFireBase();

        LayoutInflater inflater = this.getLayoutInflater();
        Intent in = getIntent();
        if (in != null) {
            MedicionPorMinuto m = in.getParcelableExtra("MedicionPorMinuto");
            if (m != null) {

                TextView title = findViewById(R.id.titulo);
                TextView ID1 = findViewById(R.id.id1);
                TextView Hora = findViewById(R.id.Hora);
                TextView Fecha = findViewById(R.id.fecha);
                TextView cordenadas = findViewById(R.id.Cordenadas);
                TextView MP10 = findViewById(R.id.Mp10);
                TextView MP25 = findViewById(R.id.Mp25);
                TextView MonoCar = findViewById(R.id.Monocar);
                TextView Ozono = findViewById(R.id.Ozono);
                TextView DioAzu = findViewById(R.id.Dioazu);
                TextView Nitro = findViewById(R.id.Nitro);
                TextView Plomo = findViewById(R.id.Plomo);


                title.setText("Detalle Medición "+ m.getId() +"");
                ID1.setText("ID Medición : "+m.getId());
                Hora.setText("Hora : "+m.getHora());
                Fecha.setText("Fecha : "+m.getFecha());
                cordenadas.setText("Ubicacion : "+m.getLugarCoordenadas());
                MP10.setText("MP 10 : "+m.getConcentracionMP10()+" ppm");
                MP25.setText("MP 25 : "+m.getConcentracionMP2_5()+" ppm");
                MonoCar.setText("Monoxido de Carbono : "+m.getConcentracionMonoxidoCarbono()+" ppm");
                Ozono.setText("Ozono : "+m.getConcentracionOzono()+" ppm");
                DioAzu.setText("Dioxido de Azufre : "+m.getConcentracionDioxidoAzufre()+" ppm");
                Nitro.setText("Nitrogeno : "+m.getConcentracionNitrogeno()+" ppm");
                Plomo.setText("Plomo : "+m.getConcentracionPlomo()+" ppm");
            }
        }
    }
    private void iniciarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}