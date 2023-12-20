package com.aliberkaygedikoglu.bitirmeproje.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.aliberkaygedikoglu.bitirmeproje.data.entity.CRUDCevap;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteEkle;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetir;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetirCevap;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.Yemekler;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.YemeklerCevap;
import com.aliberkaygedikoglu.bitirmeproje.retrofit.YemekDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    public MutableLiveData<List<SepeteGetir>> sepetListesi = new MutableLiveData<>();


    private String kullanici_adi = "aliberkaygdk";

    private YemekDao yDao;

    public YemeklerDaoRepository(YemekDao yDao) {
        this.yDao = yDao;
    }
    public void yemekleriYukle(){
      yDao.yemekleriYukle().enqueue(new Callback<YemeklerCevap>() {
          @Override
          public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
              if (response.isSuccessful()){
                  List<Yemekler> liste = response.body().getYemekler();
                  yemeklerListesi.setValue(liste);
              }

          }

          @Override
          public void onFailure(Call<YemeklerCevap> call, Throwable t) {

          }
      });
    }

    public void sepeteEkle(String yemek_adi,String yemek_resim_adi,int yemek_fiyat,int yemek_siparis_adet ){
        yDao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });

    }

    public void sepeteGetir(){

        yDao.sepeteGetir(kullanici_adi).enqueue(new Callback<SepeteGetirCevap>() {
            @Override
            public void onResponse(Call<SepeteGetirCevap> call, Response<SepeteGetirCevap> response) {
                List<SepeteGetir> liste = response.body().getSepet_yemekler();
                sepetListesi.setValue(liste);

            }

            @Override
            public void onFailure(Call<SepeteGetirCevap> call, Throwable t) {

            }
        });
    }

    public void sepettenSil(int sepet_yemek_id){
        yDao.sepettenSil(sepet_yemek_id,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepeteGetir();
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });
    }



}
