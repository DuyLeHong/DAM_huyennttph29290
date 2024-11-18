package com.example.dam_huyennttph29290;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.dam_huyennttph29290.FRAGMENT.Fra_DangXuat;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_DoanhThu;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_DoiMK;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_LoaiSach;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_PhieuMuon;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_Sach;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_ThanhVien;
import com.example.dam_huyennttph29290.FRAGMENT.Fra_Top10;
import com.google.android.material.navigation.NavigationView;

public class Ac_ManChinh extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ac_man_chinh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_drawlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.main_drawlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.linerlayout, new Fra_PhieuMuon()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_phieumuon) {
                    fragment = new Fra_PhieuMuon();
                    toolbar.setTitle("Quản Lý Phiếu Mượn");
                } else if (item.getItemId() == R.id.nav_loaisach) {
                    fragment = new Fra_LoaiSach();
                    toolbar.setTitle("Quản Lý Loại Sách");
                } else if (item.getItemId() == R.id.nav_sach) {
                    fragment = new Fra_Sach();
                    toolbar.setTitle("Quản Lý Sách");
                } else if (item.getItemId() == R.id.nav_thanhvien) {
                    fragment = new Fra_ThanhVien();
                    toolbar.setTitle("Quản Lý Thanh Viên");
                } else if (item.getItemId() == R.id.nav_top10) {
                    fragment = new Fra_Top10();
                    toolbar.setTitle("Top 10 sách mượn nhiều");
                } else if (item.getItemId() == R.id.nav_doanhthu) {
                    fragment = new Fra_DoanhThu();
                    toolbar.setTitle("Thống Kê Doanh Thu");
                } else if (item.getItemId() == R.id.nav_doimk) {
                    fragment = new Fra_DoiMK();
                    toolbar.setTitle("Đổi Mật Khẩu");
                } else if (item.getItemId() == R.id.nav_logtout) {
                    fragment = new Fra_DangXuat();
                    toolbar.setTitle("Đăng Xuất");
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.linerlayout, fragment).commit();
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == android.R.id.home){
                drawerLayout.openDrawer(GravityCompat.START);

            }
            return super.onOptionsItemSelected(item);
        }


    }