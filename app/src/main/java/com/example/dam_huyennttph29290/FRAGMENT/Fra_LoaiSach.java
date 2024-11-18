package com.example.dam_huyennttph29290.FRAGMENT;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam_huyennttph29290.Adapter.LoaiSachAdapter;
import com.example.dam_huyennttph29290.DAO.LoaiSachDAO;
import com.example.dam_huyennttph29290.Model.LoaiSach;
import com.example.dam_huyennttph29290.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Fra_LoaiSach extends Fragment {
    RecyclerView rcvLoaiSach;
    LoaiSachDAO dao;
    ArrayList<LoaiSach> list =new ArrayList<>();
    LoaiSachAdapter adapter;
    Button btnAddLS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fra__loai_sach, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvLoaiSach = view.findViewById(R.id.rcv_loaisach);
        btnAddLS = view.findViewById(R.id.btn_addLS);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvLoaiSach.setLayoutManager(layoutManager);
        dao = new LoaiSachDAO(getContext());
        list = dao.getAll();
        adapter = new LoaiSachAdapter(getContext(), list);
        rcvLoaiSach.setAdapter(adapter);

        btnAddLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.loaisach_add_edit, null);
                builder.setView(view);
                TextView tvLSaddEdit = view.findViewById(R.id.tv_LS_add_edit);
                TextInputLayout inputTenLS = view.findViewById(R.id.input_TenLoaiSach);

                TextInputLayout inputTenVietTat = view.findViewById(R.id.input_TenVietTat);

                Button btnHuyAddLS = view.findViewById(R.id.btn_exit_add_edit_LS);
                Button btnAddLS = view.findViewById(R.id.btn_add_edit_LS);
                tvLSaddEdit.setText("Thêm loại sách mới");

                Dialog dialog = builder.create();
                dialog.show();

                btnAddLS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tenLS = inputTenLS.getEditText().getText().toString();

                        String tenVietTat = inputTenVietTat.getEditText().getText().toString();
                        if(tenLS.isEmpty() || tenVietTat.isEmpty()){
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                        }else {
                            if (dao.Insert(tenLS, tenVietTat)) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                list.clear();
                                list.addAll(dao.getAll());
                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                btnHuyAddLS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
    }
}

