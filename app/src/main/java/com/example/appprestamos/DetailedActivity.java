package com.example.appprestamos;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appprestamos.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {
    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("desc");
            String time = intent.getStringExtra("monto");
            String ingredients = intent.getStringExtra("fecha_cobro");
            String desc = intent.getStringExtra("fecha_pre");
            /*int image = intent.getIntExtra("image", R.drawable.prestamo);*/
            binding.detailName.setText(name);
            binding.detailTime.setText(time);
            binding.detailDesc.setText(ingredients);
            binding.detailIngredients.setText(desc);
            binding.detailImage.setImageResource(R.drawable.prestamo);
        }
    }
}