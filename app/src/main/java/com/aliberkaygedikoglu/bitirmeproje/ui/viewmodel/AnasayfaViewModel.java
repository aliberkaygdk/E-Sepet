package com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aliberkaygedikoglu.bitirmeproje.data.entity.Yemekler;
import com.aliberkaygedikoglu.bitirmeproje.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    public YemeklerDaoRepository yRepo;
    public MutableLiveData<List<Yemekler>> yemeklerListesi;

    @Inject
    public AnasayfaViewModel(YemeklerDaoRepository yRepo){
        this.yRepo = yRepo;
        yemekleriYukle();
        yemeklerListesi = yRepo.yemeklerListesi;

    }
    public void yemekleriYukle(){
        yRepo.yemekleriYukle();
    }
}
