package com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel;



import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetir;
import com.aliberkaygedikoglu.bitirmeproje.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {

    public YemeklerDaoRepository yRepo;
    public MutableLiveData<List<SepeteGetir>> sepetListesi ;


    @Inject
    public SepetViewModel(YemeklerDaoRepository yRepo){
        this.yRepo=yRepo;
        sepetGetir();
        sepetListesi = yRepo.sepetListesi;

    }

    public void sepetGetir(){
        yRepo.sepeteGetir();
    }

    public void sepettenSil(int sepet_yemek_id){
        yRepo.sepettenSil(sepet_yemek_id);
    }



}
