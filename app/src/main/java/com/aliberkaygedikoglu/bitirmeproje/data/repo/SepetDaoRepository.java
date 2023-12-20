package com.aliberkaygedikoglu.bitirmeproje.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.aliberkaygedikoglu.bitirmeproje.data.entity.CRUDCevap;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetir;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetirCevap;
import com.aliberkaygedikoglu.bitirmeproje.retrofit.YemekDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SepetDaoRepository {



    private YemekDao yDao;

    public SepetDaoRepository(YemekDao yDao) {
        this.yDao = yDao;
    }





}
