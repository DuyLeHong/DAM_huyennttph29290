package com.example.dam_huyennttph29290.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dam_huyennttph29290.DAO.ThuThuDAO;
import com.example.dam_huyennttph29290.Model.ThuThu;
import com.example.dam_huyennttph29290.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;


public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ThuThuViewHolder> {
    Context context;
    ArrayList<ThuThu> list;
    ThuThuDAO lsDao;
    public ThuThuAdapter(Context context, ArrayList<ThuThu> list) {
        this.context = context;
        this.list = list;
        lsDao = new ThuThuDAO(context);
    }
    @NonNull
    @Override
    public ThuThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.cv_thuthu, parent, false);
        ThuThuViewHolder viewHolder = new ThuThuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHolder holder, int position) {
        ThuThu pm = list.get(position);
        holder.tvTenAdmin.setText(pm.getHoTen());
        holder.tvUsernameAdm.setText(pm.getUsername());
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                builder.setTitle("Bạn có chắc muốn xóa?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(pm.getQuyen().equals("Admin")){
                            lsDao.Delete(pm.getMaTT());
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(lsDao.getAdmin());
                            notifyDataSetChanged();
                            dialogInterface.dismiss();
                        }else if(pm.getQuyen().equals("Thủ thư")){
                            lsDao.Delete(pm.getMaTT());
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(lsDao.getThuThu());
                            notifyDataSetChanged();
                            dialogInterface.dismiss();
                        }else{
                            Toast.makeText(context, "Xóa Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThuThuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenAdmin, tvUsernameAdm;
        ImageView imgDel;
        public ThuThuViewHolder(@NonNull View view) {
            super(view);
            tvTenAdmin = view.findViewById(R.id.tv_ten_thuthu_TT);
            tvUsernameAdm = view.findViewById(R.id.tv_user_thuthu_TT);
            imgDel = view.findViewById(R.id.img_del_tt);
        }
    }
}
