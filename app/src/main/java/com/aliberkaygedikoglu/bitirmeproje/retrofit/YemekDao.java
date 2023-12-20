package com.aliberkaygedikoglu.bitirmeproje.retrofit;

import com.aliberkaygedikoglu.bitirmeproje.data.entity.CRUDCevap;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteEkle;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetir;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetirCevap;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YemekDao {
    @GET("yemekler/tumYemekleriGetir.php")
    Call<YemeklerCevap> yemekleriYukle();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap>sepeteEkle(@Field("yemek_adi")String yemek_adi,
                              @Field("yemek_resim_adi")String yemek_resim_adi,
                              @Field("yemek_fiyat") int yemek_fiyat,
                              @Field("yemek_siparis_adet") int yemek_siparis_adet,
                              @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepeteGetirCevap>sepeteGetir(@Field("kullanici_adi")String kullanici_adi);


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap>sepettenSil(@Field("sepet_yemek_id")int sepet_yemek_id,
                                      @Field("kullanici_adi")String kullanici_adi);

}
