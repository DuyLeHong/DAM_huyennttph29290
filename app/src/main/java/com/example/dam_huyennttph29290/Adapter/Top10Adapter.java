package com.example.dam_huyennttph29290.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dam_huyennttph29290.DAO.PhieuMuonDAO;
import com.example.dam_huyennttph29290.Model.Top10;
import com.example.dam_huyennttph29290.R;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.Top10ViewHolder> {
    Context context;
    ArrayList<Top10> list;
    PhieuMuonDAO pmDao;

    public Top10Adapter(@NonNull Context context, ArrayList<Top10> list) {
        this.context = context;
        this.list = list;
        pmDao = new PhieuMuonDAO(context);
    }

    @NonNull
    @Override
    public Top10ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.cv_top10, parent, false);
        Top10ViewHolder viewHolder = new Top10ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Top10ViewHolder holder, int position) {
        Top10 pm = list.get(position);
        holder.tvTenSach.setText(pm.getTenSach());
        holder.tvSoLuong.setText(pm.getSoLuong()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Top10ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSach, tvSoLuong;
        public Top10ViewHolder(@NonNull View view) {
            super(view);
            tvTenSach = view.findViewById(R.id.tv_ten_sach_Top);
            tvSoLuong = view.findViewById(R.id.tv_soLuong);
        }
    }
}
