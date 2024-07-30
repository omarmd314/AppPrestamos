package com.example.appprestamos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appprestamos.databinding.ActivityListaPrestamosBinding;

import java.io.IOException;
import java.util.ArrayList;

public class ListaPrestamosActivity extends AppCompatActivity {

    ActivityListaPrestamosBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //--
        binding = ActivityListaPrestamosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //--

       /* EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_prestamos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/


        dbHelper = new DatabaseHelper(this);

       /* try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        SQLiteDatabase db = null;

        try {
            db = dbHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT pre_id, pre_desc, pre_monto, pre_vence, pre_fecha from tb_prestamo", null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int pre_id = cursor.getInt(cursor.getColumnIndex("pre_id"));
                    @SuppressLint("Range") String pre_desc = cursor.getString(cursor.getColumnIndex("pre_desc"));
                    //@SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex("age"));
                    @SuppressLint("Range") float pre_monto = cursor.getFloat(cursor.getColumnIndex("pre_monto"));
                    @SuppressLint("Range") String pre_fecha_f = cursor.getString(cursor.getColumnIndex("pre_vence"));
                    @SuppressLint("Range") String pre_fecha = cursor.getString(cursor.getColumnIndex("pre_fecha"));
                    // Haz algo con los datos
                    listData = new ListData(pre_id, pre_desc, pre_monto, pre_fecha_f, pre_fecha);
                    Toast.makeText(this, String.valueOf(pre_fecha), Toast.LENGTH_SHORT).show();
                    dataArrayList.add(listData);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }

        listAdapter = new ListAdapter(ListaPrestamosActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaPrestamosActivity.this, DetailedActivity.class);
                intent.putExtra("desc", dataArrayList.get(i).pre_desc);
                intent.putExtra("monto", String.valueOf(dataArrayList.get(i).pre_monto));
                intent.putExtra("fecha_cobro", dataArrayList.get(i).fecha_f);
                intent.putExtra("fecha_pre", dataArrayList.get(i).fecha_i);
                intent.putExtra("image", dataArrayList.get(i).pre_desc);
                startActivity(intent);
            }
        });
    }

    public void NuevoPrestamo(View view){
        Intent i = new Intent(ListaPrestamosActivity.this, NuevoPrestamo.class);
        startActivity(i);
        finish();
    }
}