package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class UserLogged extends AppCompatActivity {
    //Vista 2 (4Botones)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogged);
    }

    //Cambiar a Vista de Detalles
    public void TestTable(View v){
        Intent i = new Intent(this, Table.class);
        startActivity(i);
    }
}