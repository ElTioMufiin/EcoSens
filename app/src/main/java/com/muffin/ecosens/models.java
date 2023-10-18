package com.muffin.ecosens;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MedicionPorMinuto {
    private int id;
    private String hora;
    private String fecha;
    private String lugarCoordenadas;
    private double concentracionMP10;
    private double concentracionMP2_5;
    private double concentracionMonoxidoCarbono;
    private double concentracionOzono;
    private double concentracionDioxidoAzufre;
    private double concentracionNitrogeno;
    private double concentracionPlomo;

    private static final double UMBRAL_MP10 = 100.0; // Definir el umbral para MP10
    private static final double UMBRAL_MP2_5 = 50.0; // Definir el umbral para MP2.5
    private static final double UMBRAL_MONOXIDO = 5.0; // Definir el umbral para Monóxido de Carbono

    private static final String CHANNEL_ID = "Alertas";
    private static final int NOTIFICATION_ID = 1;

    private Context context;

    public MedicionPorMinuto(Context context, int id, String hora, String fecha, String lugarCoordenadas,
                             double concentracionMP10, double concentracionMP2_5, double concentracionMonoxidoCarbono,
                             double concentracionOzono, double concentracionDioxidoAzufre, double concentracionNitrogeno,
                             double concentracionPlomo) {
        this.context = context;
        this.id = id;
        this.hora = hora;
        this.fecha = fecha;
        this.lugarCoordenadas = lugarCoordenadas;
        this.concentracionMP10 = concentracionMP10;
        this.concentracionMP2_5 = concentracionMP2_5;
        this.concentracionMonoxidoCarbono = concentracionMonoxidoCarbono;
        this.concentracionOzono = concentracionOzono;
        this.concentracionDioxidoAzufre = concentracionDioxidoAzufre;
        this.concentracionNitrogeno = concentracionNitrogeno;
        this.concentracionPlomo = concentracionPlomo;
    }

    public void procesarMedicion() {
        comprobarConcentracionMP10();
        comprobarConcentracionMP2_5();
        comprobarConcentracionMonoxidoCarbono();

        guardarMedicion();
    }

    private void comprobarConcentracionMP10() {
        if (concentracionMP10 > UMBRAL_MP10) {
            generarNotificacion("Concentración de MP10 alta: " + concentracionMP10 + " ppm");
        }
    }

    private void comprobarConcentracionMP2_5() {
        if (concentracionMP2_5 > UMBRAL_MP2_5) {
            generarNotificacion("Concentración de MP2.5 alta: " + concentracionMP2_5 + " ppm");
        }
    }

    private void comprobarConcentracionMonoxidoCarbono() {
        if (concentracionMonoxidoCarbono > UMBRAL_MONOXIDO) {
            generarNotificacion("Concentración de Monóxido de Carbono alta: " + concentracionMonoxidoCarbono + " ppm");
        }
    }

    private void generarNotificacion(String mensaje) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Alerta de Concentración")
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void guardarMedicion() {
        // Implementa la lógica para guardar la medición en una base de datos o sistema de almacenamiento.
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Alertas";
            String description = "Canal de notificaciones para alertas";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

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

    private List<MedicionPorMinuto> obtenerUltimas60Mediciones(String horaInicio, String horaFinal) {
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
        notificationManager.notify(NOTIFICATION_ID
