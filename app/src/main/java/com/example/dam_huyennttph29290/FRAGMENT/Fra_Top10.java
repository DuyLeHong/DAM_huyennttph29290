package com.example.dam_huyennttph29290.FRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam_huyennttph29290.Adapter.Top10Adapter;
import com.example.dam_huyennttph29290.DAO.PhieuMuonDAO;
import com.example.dam_huyennttph29290.Model.Top10;
import com.example.dam_huyennttph29290.R;

import java.util.ArrayList;


public class Fra_Top10 extends Fragment {

    RecyclerView rcvTop10;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<Top10> list = new ArrayList<>();
    Top10Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fra__top10, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvTop10 = view.findViewById(R.id.rcv_top10);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvTop10.setLayoutManager(layoutManager);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getTop10();
        adapter = new Top10Adapter(getContext(), list);
        rcvTop10.setAdapter(adapter);
    }
}