//package com.muffin.ecosens;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//public class Detalle extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detalle);
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        ;
//        Intent in = getIntent();
//        if (in != null) {
//            Integer id = in.getIntExtra("ID", 0);
//            if (id != null) {
//                TextView test = findViewById(R.id.Test);
//                MedicionPorMinuto wea = MedicionPorMinutoController.findMedicion(id);
//                String testis = wea.getFecha();
//                test.setText(testis);
//
//            }
//        }
//    }
//}