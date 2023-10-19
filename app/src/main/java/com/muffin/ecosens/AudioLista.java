package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AudioLista extends AppCompatActivity {

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_lista);

        AudioController.cargarAudio();
        AdaptadorAudio adapter = new AdaptadorAudio(this);
        listView = findViewById(R.id.listAudio);
        listView.setAdapter(adapter);

    }
    class AdaptadorAudio extends ArrayAdapter<Audio>{
        final AppCompatActivity appCompatActivity;
            public AdaptadorAudio(AppCompatActivity context) {
                super(context, R.layout.activity_audio, AudioController.getListaAudio());
                appCompatActivity = context;
            }
            public View getView(int i, View convertView, ViewGroup parent) {

                LayoutInflater inflater = appCompatActivity.getLayoutInflater();
                View item = inflater.inflate(R.layout.activity_audio, null);
                TextView Medicion1 = item.findViewById(R.id.Medicion);
                Medicion1.setText("Medición "+AudioController.getListaAudio().get(i).getId());
                TextView DB = item.findViewById(R.id.DB);
                DB.setText("Volumen : "+AudioController.getListaAudio().get(i).getDB()+" db");
                TextView Fecha1 = item.findViewById(R.id.Fecha1);
                Fecha1.setText("Fecha : "+AudioController.getListaAudio().get(i).getFecha());
                TextView Hora = item.findViewById(R.id.Hora1);
                Hora.setText("Hora : "+AudioController.getListaAudio().get(i).getHora());

                if (AudioController.getListaAudio().get(i).getDB() >= 70.0F) {
                        item.setBackgroundColor(Color.parseColor("#e7eecc"));}

                if (AudioController.getListaAudio().get(i).getDB() >= 50.0F) {
                    if (AudioController.getListaAudio().get(i).getDB() < 70.0F){
                        item.setBackgroundColor(Color.parseColor("#458CCE"));}}

                return(item);
            }

    }
}