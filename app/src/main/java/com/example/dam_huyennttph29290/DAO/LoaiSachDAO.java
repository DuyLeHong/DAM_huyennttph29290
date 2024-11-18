package com.example.dam_huyennttph29290.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dam_huyennttph29290.Database.DbHelper;
import com.example.dam_huyennttph29290.Model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<LoaiSach> getAll(){
        db = dbHelper.getReadableDatabase();
        ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT maLoai, tenLoai, tenVietTat FROM tb_loaisach ORDER BY maLoai ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _maLoai = cs.getInt(0);
            String _tenLoai = cs.getString(1);
            String tenVietTat = cs.getString(2);
            LoaiSach ls = new LoaiSach(_maLoai, _tenLoai, tenVietTat);
            list.add(ls);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public boolean Insert(String tenLoaiSach, String tenVietTat){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", tenLoaiSach);
        values.put("tenVietTat", tenVietTat);
        long row = db.insert("tb_loaisach", null, values);
        return row>0;
    }
    public boolean Update(LoaiSach obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        values.put("tenVietTat", obj.getTenVietTat());
        long row = db.update("tb_loaisach", values, "maLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
        return row>0;
    }
    public boolean Delete(int id){
        db = dbHelper.getWritableDatabase();
        long row = db.delete("tb_loaisach", "maLoai=?", new String[]{id+""});
        return row>0;
    }

}
