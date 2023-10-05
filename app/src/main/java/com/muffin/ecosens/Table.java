package com.muffin.ecosens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Table extends AppCompatActivity {


    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        DataController.cargarData();
        AdaptadorData adapter = new AdaptadorData (this);
        listView = findViewById(R.id.listData);
        listView.setAdapter(adapter);


    }
}
class AdaptadorData extends ArrayAdapter<Data> {
    final AppCompatActivity appCompatActivity;

    public AdaptadorData(AppCompatActivity context) {
        super(context, R.layout.data, DataController.getListado());
        appCompatActivity = context;
    }

    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = appCompatActivity.getLayoutInflater();
        View item = inflater.inflate(R.layout.data, null);

        TextView tableTest = item.findViewById(R.id.listData);
        tableTest.setText(DataController.getListado().get(i).getAirQ()+"%");

        return(item);

    }
}

