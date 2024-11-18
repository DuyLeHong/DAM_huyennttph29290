package com.example.dam_huyennttph29290.Model;

public class LoaiSach {
    private int maLoai;
    private String tenLoai;
    private String tenVietTat;

    public LoaiSach() {
    }

    public LoaiSach(int maLoai, String tenLoai, String tenVietTat) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.tenVietTat = tenVietTat;
    }

    @Override
    public String toString() {
        return tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTenVietTat() {
        return tenVietTat;
    }

    public void setTenVietTat(String tenVietTat) {
        this.tenVietTat = tenVietTat;
    }
}
