package com.example.dam_huyennttph29290.FRAGMENT;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dam_huyennttph29290.DAO.PhieuMuonDAO;
import com.example.dam_huyennttph29290.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Fra_DoanhThu extends Fragment {
    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edtTuNgay, edtDenNgay, edtDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mY, mM, mD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fra__doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnTuNgay = view.findViewById(R.id.btn_tuNgay);
        btnDenNgay = view.findViewById(R.id.btn_denNgay);
        btnDoanhThu = view.findViewById(R.id.btn_doanhThu);
        edtTuNgay = view.findViewById(R.id.edt_tuNgay);
        edtDenNgay = view.findViewById(R.id.edt_denNgay);
        edtDoanhThu = view.findViewById(R.id.edt_doanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, mTuNgay, mY, mM, mD);
                d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, mDenNgay, mY, mM, mD);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edtTuNgay.getText().toString();
                String denNgay = edtDenNgay.getText().toString();
                PhieuMuonDAO dao = new PhieuMuonDAO(getActivity());
                edtDoanhThu.setText(dao.DoanhThu(tuNgay, denNgay) + " VNĐ");
            }
        });

    }
    DatePickerDialog.OnDateSetListener mTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            mY = y;
            mM = m;
            mD = d;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            edtTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            mY = y;
            mM = m;
            mD = d;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            edtDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}
