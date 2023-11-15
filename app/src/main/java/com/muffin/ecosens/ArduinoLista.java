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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArduinoLista extends AppCompatActivity {

    public ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private final List<Arduino> listaArduino = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_lista);

        iniciarFireBase();
        listarArduinos();

        listView = findViewById(R.id.ArduinoList);
//        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Arduino a = listaArduino.get(i);
                Dialog dialog = new Dialog(ArduinoLista.this);
                dialog.setContentView(R.layout.activity_edit_arduino);
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
                        databaseReference.child("Arduino").child(a.getId()).removeValue();
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
                        databaseReference.child("Arduino").child(a.getId()).setValue(a);
//                        ((AdaptadorArduino)listView.getAdapter()).notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }
        });

        //Boton Agregar
        FloatingActionButton btnShowDialog = findViewById(R.id.addBtn);
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
        public View getView(int i, View convertView, @NonNull ViewGroup parent) {
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

                tv1.setText("Arduino ID : "+currentArduino.getId());
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
                String id = UUID.randomUUID().toString();
                String location = "-33.44364550456146, -70.66245811043197";
                Arduino a = ArduinoController.addArduino(id, ip.getText().toString(), "Activo", nombre.getText().toString(), location);
                databaseReference.child("Arduino").child(a.getId()).setValue(a);
                Toast.makeText(ArduinoLista.this, "Agregado con Exito", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }
    //Fin Funcion Show

}
