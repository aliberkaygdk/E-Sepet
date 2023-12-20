package com.aliberkaygedikoglu.bitirmeproje.data.entity;

import java.io.Serializable;

public class Yemekler implements Serializable{
    private String yemek_id;
    private String yemek_adi;
    private String yemek_resim_adi;
    private String yemek_fiyat;

    public Yemekler(String yemek_id, String yemek_adi, String yemek_resim_adi, String yemek_fiyat) {
        this.yemek_id = yemek_id;
        this.yemek_adi = yemek_adi;
        this.yemek_resim_adi = yemek_resim_adi;
        this.yemek_fiyat = yemek_fiyat;
    }

    public String getYemek_id() {
        return yemek_id;
    }

    public void setYemek_id(String yemek_id) {
        this.yemek_id = yemek_id;
    }

    public String getYemek_adi() {
        return yemek_adi;
    }

    public void setYemek_adi(String yemek_adi) {
        this.yemek_adi = yemek_adi;
    }

    public String getYemek_resim_adi() {
        return yemek_resim_adi;
    }

    public void setYemek_resim_adi(String yemek_resim_adi) {
        this.yemek_resim_adi = yemek_resim_adi;
    }

    public String getYemek_fiyat() {
        return yemek_fiyat;
    }

    public void setYemek_fiyat(String yemek_fiyat) {
        this.yemek_fiyat = yemek_fiyat;
    }
}
