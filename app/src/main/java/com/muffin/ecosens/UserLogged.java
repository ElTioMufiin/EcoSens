package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
    public void TestTable(View v) {
        Intent i = new Intent(this, Table.class);
        startActivity(i);
    }
    public void AudioBD(View v){
        Intent i = new Intent(this, AudioLista.class);
        startActivity(i);
    }
    public void ArduinoBD(View v){
        Intent i = new Intent(this, ArduinoLista.class);
        startActivity(i);
    }

    public void NotifyTest(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notify_1", "Test", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(UserLogged.this, "Notify_1");
        builder.setContentTitle("EcoSens Alerta");
        builder.setContentText("Alerta de Prueba");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(UserLogged.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        managerCompat.notify(1, builder.build());
    }
}