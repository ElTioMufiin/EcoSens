package com.muffin.ecosens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArduinoLista extends AppCompatActivity {

    public ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FloatingActionButton btnShowDialog;
    public long arduinoCount;

    private List<Arduino> listaArduino = new ArrayList<Arduino>();
    ArrayAdapter<Arduino> arrayAdapterArduino;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_lista);

        iniciarFireBase();
        listarArduinos();
        //Llamar funcion Cargar datos
//        ArduinoController.cargarArrayArduino();
//        AdaptadorArduino adapter = new AdaptadorArduino(this);
        listView = findViewById(R.id.ArduinoList);
//        listView.setAdapter(adapter);
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
//                        ((AdaptadorArduino)listView.getAdapter()).notifyDataSetChanged();
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
//                        ((AdaptadorArduino)listView.getAdapter()).notifyDataSetChanged();
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
    private void listarArduinos(){
        databaseReference.child("Arduino").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaArduino.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    long arduinoCount = snapshot.getChildrenCount();
                    Arduino a = item.getValue(Arduino.class);
                    listaArduino.add(a);
                }

                // Utiliza tu adaptador personalizado
                AdaptadorArduino adapter = new AdaptadorArduino(ArduinoLista.this, R.layout.activity_arduino, listaArduino);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores de base de datos si es necesario
            }
        });
    }

    public class AdaptadorArduino extends ArrayAdapter<Arduino> {

        public AdaptadorArduino(ArduinoLista context, int resource, List<Arduino> arduinos) {
            super(context, resource, arduinos);
        }
        @Override
        public View getView(int i, View convertView,  ViewGroup parent) {
            View listItemView = convertView;
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_arduino, parent, false);

            Arduino currentArduino = getItem(i);

            if (currentArduino != null) {
                //Declarar TextViews Para El diseño
            TextView tv1 = listItemView.findViewById(R.id.ArduinoID);
            TextView tv2 = listItemView.findViewById(R.id.ArduinoIp);
            TextView tv3 = listItemView.findViewById(R.id.ArduinoNombre);
            TextView tv4 = listItemView.findViewById(R.id.ArduinoEstado);
                // Configura el texto del TextView utilizando toString()

                tv1.setText("Arduino "+currentArduino.getId());
                tv2.setText("Ip : "+currentArduino.getIp());
                tv3.setText("Nombre : "+currentArduino.getNombre());
                tv4.setText("Estado : "+currentArduino.getEstado());


            }

            return listItemView;
        }
    }

    private void iniciarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
                Arduino a = ArduinoController.addArduino("Wea",ip.getText().toString(),"Activo",nombre.getText().toString(),"wea");
                if (a != null){
                    databaseReference.child("Arduino").child(a.getNombre()).setValue(a);
                }else {}

                dialog.dismiss();
//                ((AdaptadorArduino) listView.getAdapter()).notifyDataSetChanged();
            }
        });

    }

    //Fin Funcion Show

    //Clase mostrar datos de manera ordenada
//    class AdaptadorArduino extends ArrayAdapter<Arduino> {
//        final AppCompatActivity appCompatActivity;
//
//        public AdaptadorArduino(AppCompatActivity context) {
//            super(context, R.layout.activity_arduino, ArduinoController.getListaArduino());
//            appCompatActivity = context;
//        }
//        public View getView(int i, View convertView, ViewGroup parent) {
//
//            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
//            View item = inflater.inflate(R.layout.activity_arduino, null);
//
//            TextView tv1 = item.findViewById(R.id.ArduinoNro);
//            TextView tv2 = item.findViewById(R.id.ArduinoIp);
//            TextView tv3 = item.findViewById(R.id.ArduinoNombre);
//            TextView tv4 = item.findViewById(R.id.ArduinoEstado);
//
//
//            tv1.setText("Dispositivo " + ArduinoController.getListaArduino().get(i).getId() + ":");
//            tv2.setText("Ip : " + ArduinoController.getListaArduino().get(i).getIp());
//            tv3.setText("Nombre : " + ArduinoController.getListaArduino().get(i).getNombre());
//            tv4.setText("Estado :" + ArduinoController.getListaArduino().get(i).getEstado());
//
//            return (item);
//        }
//
//    }
}
