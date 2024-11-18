package com.example.dam_huyennttph29290;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_huyennttph29290.DAO.ThuThuDAO;
import com.example.dam_huyennttph29290.Model.ThuThu;
import com.google.android.material.textfield.TextInputLayout;

public class Ac_DangNhap extends AppCompatActivity {
    TextInputLayout edUsename, edPassword;
    Button btnLogin, btnExit;
    CheckBox chkMk;
    ThuThuDAO dao;
    String strUser, strPass, strQuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ac_dang_nhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUsename = findViewById(R.id.ip_lg_user);
        edPassword = findViewById(R.id.ip_lg_pass);
        btnLogin = findViewById(R.id.btn_lg_dangnhap);
        btnExit = findViewById(R.id.btn_lg_exit);
        chkMk = findViewById(R.id.chk_lg_luumk);
        dao = new ThuThuDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean luuMk = pref.getBoolean("REMEMBER", false);

        edUsename.getEditText().setText(user);
        edPassword.getEditText().setText(pass);
        chkMk.setChecked(luuMk);

        //Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
        //Hủy

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mDialog = new AlertDialog.Builder(view.getContext());
                mDialog.setTitle("Question");
                mDialog.setMessage("Are you sure You want to Exit?");
                mDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), Ac_ManChao.class);
                        startActivity(intent);
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startActivity(startMain);
                        finish();
                    }
                });
                mDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mDialog.create().show();
            }
        });
    }
        //nhớ Mk
        public void rememberUser(String u, String p, boolean status){
            SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            if(!status){
                edit.clear();
            }else{
                edit.putString("USERNAME", u);
                edit.putString("PASSWORD", p);
                edit.putBoolean("REMEMBER", status);
            }
            edit.commit();
        }
        public void checkLogin(){
            strUser = edUsename.getEditText().getText().toString();
            strPass = edPassword.getEditText().getText().toString();

            if(strUser.isEmpty()||strPass.isEmpty()){
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }else {
                if(dao.checkLogin(strUser,strPass)>0){
                    ThuThu tt = dao.getID(strUser);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("quyen", tt);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(strUser, strPass, chkMk.isChecked());
                    Intent intent = new Intent(getApplicationContext(), Ac_ManChinh.class);
                    intent.putExtra("user", strUser);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        }
}