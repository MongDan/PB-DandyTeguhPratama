package com.example.tugas2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Keuangan extends AppCompatActivity {

    private EditText inputSaldo, inputDeskripsi;
    private TextView txtOutputSaldo, txtOutputDeskripsi;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan);

        // Inisialisasi komponen UI
        inputSaldo = findViewById(R.id.inputSaldo);
        inputDeskripsi = findViewById(R.id.inputDeskripsi);
        txtOutputSaldo = findViewById(R.id.txtOutputSaldo);
        txtOutputDeskripsi = findViewById(R.id.txtOutputDeskripsi);
        btnSimpan = findViewById(R.id.btnSimpan);

        // Menambahkan fungsi pada tombol Simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil nilai dari input
                String saldo = inputSaldo.getText().toString();
                String deskripsi = inputDeskripsi.getText().toString();

                // Menampilkan hasil input di TextView
                if (!saldo.isEmpty() && !deskripsi.isEmpty()) {
                    txtOutputSaldo.setText("Disimpan: Rp " + saldo);
                    txtOutputDeskripsi.setText("Deskripsi: " + deskripsi);

                } else {
                    txtOutputSaldo.setText("Harap masukkan saldo!");
                    txtOutputDeskripsi.setText("Harap masukkan deskripsi!");
                }
            }
        });
    }
}
