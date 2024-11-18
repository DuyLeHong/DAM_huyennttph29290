package com.example.dam_huyennttph29290.FRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dam_huyennttph29290.DAO.ThuThuDAO;
import com.example.dam_huyennttph29290.Model.ThuThu;
import com.example.dam_huyennttph29290.R;
import com.google.android.material.textfield.TextInputLayout;


public class Fra_DoiMK extends Fragment {
    TextInputLayout edPassCu, edPassMoi, edPass2;
    Button btnSave, btnHuy;
    ThuThuDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fra__doi_m_k, container, false);
        edPassCu = v.findViewById(R.id.input_edt_pass_cu);
        edPassMoi = v.findViewById(R.id.input_edit_pass_moi);
        edPass2 = v.findViewById(R.id.input_edit_pass_moi2);
        btnSave = v.findViewById(R.id.btn_luu_doiMK);
        btnHuy = v.findViewById(R.id.btn_Huy_doiMK);
        dao = new ThuThuDAO(getActivity());
        ThuThu thuThu = null;

        ThuThu finalThuThu = thuThu; // Biến cần final để sử dụng trong OnClickListener

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = finalThuThu.getUsername();
                if (validate() > 0) {
                    ThuThu thuThuToUpdate = dao.getID(user);
                    if (thuThuToUpdate != null) {
                        thuThuToUpdate.setMatKhau(edPassMoi.getEditText().getText().toString());
                        if (dao.UpdatePass(thuThuToUpdate)) {
                            Toast.makeText(getContext(), "Thay đổi hoàn tất!", Toast.LENGTH_SHORT).show();
                            clearFields();
                        } else {
                            Toast.makeText(getContext(), "Thay đổi thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Không tìm thấy tài khoản để cập nhật!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        return v;
    }
    private void clearFields() {
        edPassCu.getEditText().setText("");
        edPassMoi.getEditText().setText("");
        edPass2.getEditText().setText("");
    }
    public int validate(){
        int check = 1;

        if (edPassCu.getEditText().getText().length() == 0 || edPassMoi.getEditText().getText().length() == 0 || edPass2.getEditText().getText().length() == 0) {
            Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            ThuThu thuThu = null;
            if (getArguments() != null && getArguments().containsKey("doiPass")) {
                thuThu = (ThuThu) getArguments().get("doiPass");
            }

            if (thuThu != null) {
                String passCu = thuThu.getMatKhau();
                String passMoi = edPassMoi.getEditText().getText().toString();
                String passMoi2 = edPass2.getEditText().getText().toString();

                if (!passCu.equals(edPassCu.getEditText().getText().toString())) {
                    Toast.makeText(getContext(), "Mật khẩu cũ chưa đúng!", Toast.LENGTH_SHORT).show();
                    check = -1;
                }
                if (!passMoi.equals(passMoi2)) {
                    Toast.makeText(getContext(), "Mật khẩu mới không trùng khớp!", Toast.LENGTH_SHORT).show();
                    check = -1;
                }
                if (passCu.equals(passMoi)) {
                    Toast.makeText(getContext(), "Mật khẩu mới không được trùng mật khẩu cũ!", Toast.LENGTH_SHORT).show();
                    check = -1;
                }
            } else {
                Toast.makeText(getContext(), "Không thể kiểm tra thông tin mật khẩu!", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }
}
