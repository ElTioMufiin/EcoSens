/*
package com.muffin.ecosens;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ConcentracionPorHora {
    private int id;
    private String horaInicio;
    private String horaFinal;
    private String lugares;
    private double promedioMP10;
    private double promedioMP2_5;
    private double promedioConcMonoxidoCarbono;
    private double promedioConcOzono;
    private double promedioConcDioxidoAzufre;
    private double promedioConcNitrogeno;
    private double promedioConcPlomo;

    private static final double UMBRAL_PROMEDIO = 100.0; // Establece el umbral para el promedio

    private static final String CHANNEL_ID = "Alertas";
    private static final int NOTIFICATION_ID = 2;

    private Context context;

    public ConcentracionPorHora(Context context, int id, String horaInicio, String horaFinal, String lugares) {
        this.context = context;
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.lugares = lugares;

        // Calcular los promedios
        calcularPromedioMP10();
        calcularPromedioMP2_5();
        calcularPromedioMonoxidoCarbono();
        calcularPromedioOzono();
        calcularPromedioDioxidoAzufre();
        calcularPromedioNitrogeno();
        calcularPromedioPlomo();
    }

    private void calcularPromedioMP10() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionMP10();
            }
            promedioMP10 = suma / mediciones.size();
            comprobarPromedio("MP10", promedioMP10);
        }
    }

    private void calcularPromedioMP2_5() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionMP2_5();
            }
            promedioMP2_5 = suma / mediciones.size();
            comprobarPromedio("MP2.5", promedioMP2_5);
        }
    }

    private void calcularPromedioMonoxidoCarbono() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionMonoxidoCarbono();
            }
            promedioConcMonoxidoCarbono = suma / mediciones.size();
            comprobarPromedio("Monóxido de Carbono", promedioConcMonoxidoCarbono);
        }
    }

    private void calcularPromedioOzono() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionOzono();
            }
            promedioConcOzono = suma / mediciones.size();
            comprobarPromedio("Ozono", promedioConcOzono);
        }
    }

    private void calcularPromedioDioxidoAzufre() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionDioxidoAzufre();
            }
            promedioConcDioxidoAzufre = suma / mediciones.size();
            comprobarPromedio("Dióxido de Azufre", promedioConcDioxidoAzufre);
        }
    }

    private void calcularPromedioNitrogeno() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionNitrogeno();
            }
            promedioConcNitrogeno = suma / mediciones.size();
            comprobarPromedio("Nitrógeno", promedioConcNitrogeno);
        }
    }

    private void calcularPromedioPlomo() {
        List<MedicionPorMinuto> mediciones = obtenerUltimas60Mediciones(horaInicio, horaFinal);
        if (mediciones.size() > 0) {
            double suma = 0.0;
            for (MedicionPorMinuto medicion : mediciones) {
                suma += medicion.getConcentracionPlomo();
            }
            promedioConcPlomo = suma / mediciones.size();
            comprobarPromedio("Plomo", promedioConcPlomo);
        }
    }

    private static List<MedicionPorMinuto> obtenerUltimas60Mediciones(String horaInicio, String horaFinal) {
        // Implementa la lógica para recuperar las últimas 60 mediciones en el rango de tiempo especificado.
        // Puedes utilizar una base de datos o sistema de almacenamiento para esto.
        // Devuelve una lista de mediciones por minuto.
        return null; // Reemplaza con la lógica real de recuperación de datos.
    }

    private void comprobarPromedio(String gas, double promedio) {
        if (promedio > UMBRAL_PROMEDIO) {
            generarNotificacion("Promedio de " + gas + " alto: " + promedio + " ppm");
        }
    }

    private void generarNotificacion(String mensaje) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Alerta de Concentración Promedio")
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());}

}
*/
