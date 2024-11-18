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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dam_huyennttph29290.Adapter.SachAdapter;
import com.example.dam_huyennttph29290.DAO.LoaiSachDAO;
import com.example.dam_huyennttph29290.DAO.SachDAO;
import com.example.dam_huyennttph29290.Model.LoaiSach;
import com.example.dam_huyennttph29290.Model.Sach;
import com.example.dam_huyennttph29290.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Fra_Sach extends Fragment {
    RecyclerView rcvSach;
    SachDAO dao;
    ArrayList<Sach> list =new ArrayList<>();
    SachAdapter adapter;
    Button btnAddS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fra__sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvSach = view.findViewById(R.id.rcv_sach);
        btnAddS = view.findViewById(R.id.btn_addS);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(layoutManager);
        dao = new SachDAO(getContext());
        list = dao.getAll();
        adapter = new SachAdapter(getContext(), list);
        rcvSach.setAdapter(adapter);
        btnAddS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiaLogAddSach();
            }
        });

    }

    private void OpenDiaLogAddSach() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.sach_add, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edGiaThue = view.findViewById(R.id.ed_gia_thue);
        TextInputLayout inputTenSach = view.findViewById(R.id.intput_add_sach);
        Spinner spnAddLoaiSach = view.findViewById(R.id.spn_add_loai_sach);
        Button btnHuyAdd_S = view.findViewById(R.id.btn_exit_add_sach);
        Button btnAdd_S = view.findViewById(R.id.btn_add_sach);

        LoaiSachDAO lsDao =new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> listLS = lsDao.getAll();
        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item, listLS);
        spnAddLoaiSach.setAdapter(adapter);

        btnAdd_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int giaThue = Integer.parseInt(edGiaThue.getText().toString());
                String tenSach = inputTenSach.getEditText().getText().toString();
                LoaiSach ls = (LoaiSach) spnAddLoaiSach.getSelectedItem();
                int maLoai = ls.getMaLoai();
                Sach s = new Sach(tenSach, giaThue, maLoai);
                if(tenSach.isEmpty()||giaThue==0){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    if (dao.Insert(s)) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dao.getAll());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        btnHuyAdd_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}

