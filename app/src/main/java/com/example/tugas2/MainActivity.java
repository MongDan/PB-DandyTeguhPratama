package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ImageView Keuangan, Tugas;

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


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_profile) {
                    startActivity(new Intent(MainActivity.this, Profile.class));
                    return true;
                } else if (item.getItemId() == R.id.nav_setting) {
                    startActivity(new Intent(MainActivity.this, Setting.class));
                    return true;
                }
                return false;
            }
        });


        Keuangan = findViewById(R.id.Keuangan);
        Tugas = findViewById(R.id.Tugas);


        Keuangan.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Keuangan.class));
        });

        Tugas.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Tugas.class));
        });
    }
}
