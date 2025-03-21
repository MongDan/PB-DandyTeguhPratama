package com.example.tugas2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Tugas extends AppCompatActivity {
    private EditText inputTugas;
    private Button btnTambah;
    private RecyclerView recyclerViewTugas;
    private TugasAdapter tugasAdapter;
    private ArrayList<TugasModel> daftarTugas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);

        // Inisialisasi komponen
        inputTugas = findViewById(R.id.inputTugas);
        btnTambah = findViewById(R.id.btnTambah);
        recyclerViewTugas = findViewById(R.id.recyclerViewTugas);

        // Inisialisasi RecyclerView
        daftarTugas = new ArrayList<>();
        tugasAdapter = new TugasAdapter(daftarTugas, position -> {
            // Aksi klik pada item (jika diperlukan)
            Toast.makeText(this, "Tugas: " + daftarTugas.get(position).getNamaTugas(), Toast.LENGTH_SHORT).show();
        });

        recyclerViewTugas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTugas.setAdapter(tugasAdapter);

        // Event klik tombol tambah
        btnTambah.setOnClickListener(v -> {
            String tugas = inputTugas.getText().toString().trim();
            if (!tugas.isEmpty()) {
                daftarTugas.add(new TugasModel(tugas));
                tugasAdapter.notifyItemInserted(daftarTugas.size() - 1);
                inputTugas.setText("");
            } else {
                Toast.makeText(this, "Masukkan tugas terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
