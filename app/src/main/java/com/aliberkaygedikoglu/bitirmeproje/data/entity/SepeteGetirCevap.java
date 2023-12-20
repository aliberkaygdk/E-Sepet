package com.aliberkaygedikoglu.bitirmeproje.data.entity;

import androidx.annotation.Keep;

import java.util.List;


public class SepeteGetirCevap {
    private List<SepeteGetir> sepet_yemekler;
    private int success;

    public SepeteGetirCevap(List<SepeteGetir> sepet_yemekler, int success) {
        this.sepet_yemekler = sepet_yemekler;
        this.success = success;
    }

    public List<SepeteGetir> getSepet_yemekler() {
        return sepet_yemekler;
    }

    public void setSepet_yemekler(List<SepeteGetir> sepet_yemekler) {
        this.sepet_yemekler = sepet_yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
