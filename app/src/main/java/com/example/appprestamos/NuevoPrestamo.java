package com.example.appprestamos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NuevoPrestamo extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText desc;
    private EditText monto;
    private EditText fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_prestamo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        desc = findViewById(R.id.pre_desc);
        monto = findViewById(R.id.pre_monto);
        fecha = findViewById(R.id.pre_fecha);
    }

    public void Guardar(View view){
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("pre_desc",String.valueOf(desc.getText()));
            contentValues.put("pre_monto",Float.valueOf(String.valueOf(monto.getText())));
            contentValues.put("pre_vence",String.valueOf(fecha.getText()));

            db.insert("tb_prestamo",null, contentValues);

        } catch (SQLiteException e) {
            Toast.makeText(this,  e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        Toast.makeText(this,  "Se guardo correctamente", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(NuevoPrestamo.this, ListaPrestamosActivity.class);
        startActivity(i);
        finish();

    }





    public void Cancelar(View view){
        Intent i = new Intent(NuevoPrestamo.this, ListaPrestamosActivity.class);
        startActivity(i);
        finish();
    }

}