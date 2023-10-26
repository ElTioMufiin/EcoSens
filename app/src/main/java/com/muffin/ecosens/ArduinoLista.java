package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ArduinoLista extends AppCompatActivity {

    //Vista Tabla
    public ListView listView;
    private FloatingActionButton btnShowDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_lista);



        //Llamar funcion Cargar datos
        ArduinoController.cargarArrayArduino();
        AdaptadorArduino adapter = new AdaptadorArduino(this);
        listView = findViewById(R.id.ArduinoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Dialog dialog = new Dialog(ArduinoLista.this);
                dialog.setContentView(R.layout.activity_edit_arduino);
                Arduino a = ArduinoController.getListaArduino().get(i);
                TextView eName = dialog.findViewById(R.id.editNombre);
                TextView eIp = dialog.findViewById(R.id.editIp);
                eName.setText(a.getNombre());
                eIp.setText(a.getIp());
                Switch switcher = dialog.findViewById(R.id.swEstado);
                Boolean swstate = switcher.isChecked();
                if(a.getEstado()=="Activo"){
                    switcher.setChecked(true);
                }if(a.getEstado()=="Inactivo") {
                    switcher.setChecked(false);
                }
                dialog.show();



                Button edit = dialog.findViewById(R.id.updateDevice);
                Button delete = dialog.findViewById(R.id.deleteDevice);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArduinoController.getListaArduino().remove(i);
                        ((AdaptadorArduino)listView.getAdapter()).notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a.setNombre(eName.getText().toString());
                        a.setIp(eIp.getText().toString());
                        if(switcher.isChecked()==true){
                            a.setEstado("Activo");
                        }if(switcher.isChecked()==false){
                            a.setEstado("Inactivo");
                        }
                        ((AdaptadorArduino)listView.getAdapter()).notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }
        });

        //Boton Agregar
        btnShowDialog = findViewById(R.id.addBtn);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //Fin Boton Agregar
    }
    //Funcion Dialogo (Mostrar Layout, leer entrada de datos y refrescar la vista)
    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_add_arduino);
        dialog.show();
        Button add = dialog.findViewById(R.id.addDevice);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nombre = dialog.findViewById(R.id.addNombre);
                TextView ip = dialog.findViewById(R.id.addIp);
                int id = ArduinoController.getListaArduino().size()+1;
                ArduinoController.addArduino(id,ip.getText().toString(),"Activo",nombre.getText().toString(),"wea");
                dialog.dismiss();
                ((AdaptadorArduino) listView.getAdapter()).notifyDataSetChanged();
            }
        });







    }
    //Fin Funcion Show
    //Clase mostrar datos de manera ordenada
    class AdaptadorArduino extends ArrayAdapter<Arduino> {
        final AppCompatActivity appCompatActivity;

        public AdaptadorArduino(AppCompatActivity context) {
            super(context, R.layout.activity_arduino, ArduinoController.getListaArduino());
            appCompatActivity = context;
        }
        public View getView(int i, View convertView, ViewGroup parent) {

            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.activity_arduino, null);

            TextView tv1 = item.findViewById(R.id.ArduinoNro);
            TextView tv2 = item.findViewById(R.id.ArduinoIp);
            TextView tv3 = item.findViewById(R.id.ArduinoNombre);
            TextView tv4 = item.findViewById(R.id.ArduinoEstado);


            tv1.setText("Dispositivo " + ArduinoController.getListaArduino().get(i).getId() + ":");
            tv2.setText("Ip : " + ArduinoController.getListaArduino().get(i).getIp());
            tv3.setText("Nombre : " + ArduinoController.getListaArduino().get(i).getNombre());
            tv4.setText("Estado :" + ArduinoController.getListaArduino().get(i).getEstado());

            return (item);
        }

    }
}
