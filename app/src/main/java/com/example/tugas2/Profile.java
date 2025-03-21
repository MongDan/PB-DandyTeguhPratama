package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    TextView profileEmail, profileUID;
    Button logoutBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_setting) {
                    startActivity(new Intent(Profile.this, Setting.class));
                    return true;
                } else if (item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(Profile.this, MainActivity.class));
                    return true;
                }
                return false;
            }
        });
        // Inisialisasi Firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        // Inisialisasi View
        profileEmail = findViewById(R.id.profileEmail);
        profileUID = findViewById(R.id.profileUID);
        logoutBtn = findViewById(R.id.logoutBtn);

        if (user != null) {
            // Menampilkan data user
            profileEmail.setText("Email: " + user.getEmail());
            profileUID.setText("User ID: " + user.getUid());
        } else {
            Toast.makeText(this, "User tidak ditemukan!", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Tombol Logout
        logoutBtn.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(Profile.this, "Logout Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Profile.this, MainActivity1.class));
            finish();
        });
    }
}
