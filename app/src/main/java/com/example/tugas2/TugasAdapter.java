package com.example.tugas2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.TugasViewHolder> {
    private ArrayList<TugasModel> daftarTugas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TugasAdapter(ArrayList<TugasModel> daftarTugas, OnItemClickListener listener) {
        this.daftarTugas = daftarTugas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tugas, parent, false);
        return new TugasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TugasViewHolder holder, int position) {
        TugasModel tugas = daftarTugas.get(position);
        holder.txtNamaTugas.setText(tugas.getNamaTugas());

        // Hapus tugas saat tombol delete ditekan
        holder.btnHapusTugas.setOnClickListener(v -> {
            daftarTugas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, daftarTugas.size());
        });

        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return daftarTugas.size();
    }

    public static class TugasViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaTugas;
        ImageButton btnHapusTugas;

        public TugasViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaTugas = itemView.findViewById(R.id.txtNamaTugas);
            btnHapusTugas = itemView.findViewById(R.id.btnHapusTugas);
        }
    }
}
