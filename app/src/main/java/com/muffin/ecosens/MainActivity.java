package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //Mostrar Pantalla Login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Funcion cambiar de vista (Boton Login)
    public void Login(View v){
        Intent i = new Intent(this, UserLogged.class);
        startActivity(i);
    }
}