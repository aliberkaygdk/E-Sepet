package com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.aliberkaygedikoglu.bitirmeproje.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    public YemeklerDaoRepository yRepo;

    @Inject
    public DetayViewModel(YemeklerDaoRepository yRepo){
        this.yRepo=yRepo;
    }
    public void sepeteEkle(String yemek_adi,String yemek_resim_adi,int yemek_fiyat,int yemek_siparis_adet){
        yRepo.sepeteEkle( yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet);
    }
}
