package com.example.tugas2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {
    private List<SettingModel> settingList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public SettingAdapter(List<SettingModel> settingList, OnItemClickListener listener) {
        this.settingList = settingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingModel setting = settingList.get(position);
        holder.titleSetting.setText(setting.getTitle());
        holder.iconSetting.setImageResource(setting.getIcon());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return settingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleSetting;
        ImageView iconSetting;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleSetting = itemView.findViewById(R.id.titleSetting);
            iconSetting = itemView.findViewById(R.id.iconSetting);
        }
    }
}
