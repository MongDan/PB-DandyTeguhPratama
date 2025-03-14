
package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity1 extends AppCompatActivity {

    TextInputEditText emailUser, passwordUser;
    CheckBox checkBoxes;
    Button btLogin;
    TextView forgotPass, signUp;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailUser = findViewById(R.id.email);
        passwordUser = findViewById(R.id.password);
        checkBoxes = findViewById(R.id.checkboxes);
        btLogin = findViewById(R.id.btnLogin);
        forgotPass = findViewById(R.id.forgotPassword);
        signUp = findViewById(R.id.signUp);

        mAuth =FirebaseAuth.getInstance();
        btLogin.setOnClickListener(view -> {
            String email = String.valueOf(emailUser.getText()).trim();
            String passwordUser1 = String.valueOf(passwordUser.getText()).trim();

            if (email.isEmpty()) {
                emailUser.setError("Email tidak boleh kosong!");
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailUser.setError("Format email tidak valid!");
                return;
            }
            if (passwordUser1.isEmpty()) {
                passwordUser.setError("Password tidak boleh kosong!");
                return;
            }

            mAuth.signInWithEmailAndPassword(email, passwordUser1)
                    .addOnSuccessListener(authResult -> {
                        Toast.makeText(MainActivity1.this, "Login Berhasil", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity1.this, MainActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity1.this, "Login Gagal: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
        signUp.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity1.this, MainActivity2.class));
        });
    }
}