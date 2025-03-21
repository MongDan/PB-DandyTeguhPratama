package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Setting extends AppCompatActivity {

    RecyclerView recyclerViewSetting;
    List<SettingModel> settingList;
    SettingAdapter settingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Inisialisasi BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_profile) {
                    startActivity(new Intent(Setting.this, Profile.class));
                    return true;
                } else if (item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(Setting.this, MainActivity.class));
                    return true;
                }
                return false;
            }
        });

        // Inisialisasi RecyclerView
        recyclerViewSetting = findViewById(R.id.recyclerViewSetting);
        recyclerViewSetting.setLayoutManager(new LinearLayoutManager(this));

        // Menambahkan menu pengaturan
        settingList = new ArrayList<>();
        settingList.add(new SettingModel("Akun", android.R.drawable.ic_menu_myplaces));
        settingList.add(new SettingModel("Keamanan", android.R.drawable.ic_lock_lock));
        settingList.add(new SettingModel("Notifikasi", android.R.drawable.ic_dialog_alert));
        settingList.add(new SettingModel("Bantuan", android.R.drawable.ic_menu_help));
        settingList.add(new SettingModel("Tentang Aplikasi", android.R.drawable.ic_menu_info_details));

        settingAdapter = new SettingAdapter(settingList, position -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(Setting.this, Profile.class));
                    break;
                case 1:
                    startActivity(new Intent(Setting.this, SecurityActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(Setting.this, NotificationActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(Setting.this, HelpActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(Setting.this, AboutActivity.class));
                    break;
            }
        });

        recyclerViewSetting.setAdapter(settingAdapter);
    }
}
