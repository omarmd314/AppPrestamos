package com.example.appprestamos;

import android.annotation.SuppressLint;
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

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private EditText log_usuario;
    private EditText log_contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        log_usuario = findViewById(R.id.txtUsuario);
        log_contra = findViewById(R.id.txtContra);





        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = null;

        try {
            db = dbHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        //String[] array =  new String[10];
        String empleado = "";
        //int i = 0;
        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT * FROM tb_cliente", null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("cli_nombre"));
                    //@SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex("age"));
                    @SuppressLint("Range") String apellido = cursor.getString(cursor.getColumnIndex("cli_apellido"));
                    // Haz algo con los datos
                    //array[i] = name.concat(" " + email);
                    empleado = empleado.concat(nombre + " " + apellido+ "\n");
                    //i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }

        Toast.makeText(this, empleado, Toast.LENGTH_LONG).show();



    }


    @SuppressLint("Range")
    public void VerificarUsuario(View view ){
        //Toast.makeText(this, "Sesion iniciada", Toast.LENGTH_SHORT).show();

        SQLiteDatabase db;
        try {
            db = dbHelper.getReadableDatabase();
            // String[] columns = {"u_nombre", "u_contra"};
            String[] columns = {"u_nombre", "u_contra"};
            String selection = "u_nombre = ? and u_contra = ?";
            String usuario = "", contra = "";
            //String[] selectionArgs = {String.valueOf(log_usuario.getText())};
            String[] selectionArgs = {String.valueOf(log_usuario.getText()), String.valueOf(log_contra.getText())};
            Cursor cursor = db.query("tb_usuario", columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                   usuario = cursor.getString(cursor.getColumnIndex("u_nombre"));
                   contra = cursor.getString(cursor.getColumnIndex("u_contra"));
                } while (cursor.moveToNext());
            }

            if (!usuario.isEmpty() && !contra.isEmpty()){
                Toast.makeText(this, "SESION INICIADA", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, ListaPrestamosActivity.class);
                startActivity(i);
                finish();
            }

            cursor.close();
            db.close();

        } catch (SQLiteException e ) {
            e.printStackTrace();
        }

    }


}