package com.muffin.ecosens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AudioLista extends AppCompatActivity {

    public ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private final List<Audio> listaAudio = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_lista);

        iniciarFireBase();
        listarAudio();
        listView = findViewById(R.id.listAudio);

    }
    private void iniciarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void listarAudio(){
        databaseReference.child("MedicionesAudio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaAudio.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    Audio a = item.getValue(Audio.class);
                    listaAudio.add(a);
                }
                AdaptadorAudio adapter = new AdaptadorAudio(AudioLista.this,R.layout.activity_audio,listaAudio);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    class AdaptadorAudio extends ArrayAdapter<Audio>{

            public AdaptadorAudio(AudioLista context,int resource,List<Audio> audios) {
                super(context, resource, audios);
            }
            public View getView(int i, View convertView, ViewGroup parent) {
                View item = convertView;
                item = LayoutInflater.from(getContext()).inflate(R.layout.activity_audio,parent,false);

                Audio currentAudio = getItem(i);

                TextView Medicion1 = item.findViewById(R.id.Medicion);
                TextView DB = item.findViewById(R.id.DB);
                TextView Fecha1 = item.findViewById(R.id.Fecha1);
                TextView Hora = item.findViewById(R.id.Hora1);

                if (currentAudio !=null){
                    Medicion1.setText("Medición "+currentAudio.getId());
                    DB.setText("Volumen : "+currentAudio.getDB()+" db");
                    Fecha1.setText("Fecha : "+currentAudio.getFecha());
                    Hora.setText("Hora : "+currentAudio.getHora());
                    float DBs = Float.parseFloat(currentAudio.getDB());

                    if (DBs >= 70.0F) {
                        DB.setBackgroundColor(Color.parseColor("#e7eecc"));}

                    if (DBs >= 50.0F) {
                        if (DBs <= 70.0F){
                            DB.setBackgroundColor(Color.parseColor("#458CCE"));}}
                }

                return item;
            }

    }
}