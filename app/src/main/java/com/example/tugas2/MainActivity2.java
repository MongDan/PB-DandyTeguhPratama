package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tugas2.R;
import com.example.tugas2.UserDetails;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    Button signUpBtn;
    TextInputEditText usernameSignUp, passwordSignUp, nimPengguna, emailPengguna;
    FirebaseAuth mAuth;

    TextView signIn;
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        signUpBtn = findViewById(R.id.signUpBtn);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        emailPengguna = findViewById(R.id.emailPengguna);
        passwordSignUp = findViewById(R.id.password);
        nimPengguna = findViewById(R.id.nimPengguna);
        signIn = findViewById(R.id.signIn); // ✅ DITAMBAHKAN

        signIn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

        signUpBtn.setOnClickListener(view -> {
            String username, email, password, NIM;

            username = String.valueOf(usernameSignUp.getText());
            email = String.valueOf(emailPengguna.getText());
            password = String.valueOf(passwordSignUp.getText());
            NIM = String.valueOf(nimPengguna.getText());

            if (TextUtils.isEmpty(username)){
                usernameSignUp.setError("Enter Username");
                usernameSignUp.requestFocus();
            } else if (TextUtils.isEmpty(email)) {
                emailPengguna.setError("Enter email");
                emailPengguna.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                passwordSignUp.setError("Enter Password");
                passwordSignUp.requestFocus();
            } else if (TextUtils.isEmpty(NIM)) {
                nimPengguna.setError("Please Insert your NIM");
                nimPengguna.requestFocus();
            } else {
                registerUser(username, email, password, NIM);
            }
        });
    }

    private void registerUser(String username, String email, String password, String NIM) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity2.this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser fUser = auth.getCurrentUser();
                if (fUser != null) {
                    String uid = fUser.getUid();
                    UserDetails userDetails = new UserDetails(uid, username, email, password, NIM);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(uid).setValue(userDetails).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            fUser.sendEmailVerification();
                            Toast.makeText(MainActivity2.this, "YEAYY, SUCCESSFULLY REGISTERED", Toast.LENGTH_LONG).show();

                            //Pindah ke halaman utama
                            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity2.this, "Account registration failed", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Register: Error");
                        }
                    });
                } else {
                    Toast.makeText(MainActivity2.this, "Error: User is null", Toast.LENGTH_SHORT).show();
                }
            } else {
                Exception e = task.getException();
                Toast.makeText(MainActivity2.this, "Register Failed: " + (e != null ? e.getMessage() : "Unknown Error"), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Register Error: ", e);
            }
        });
    }
}
